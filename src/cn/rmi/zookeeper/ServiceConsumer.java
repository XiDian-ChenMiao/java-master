package cn.rmi.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ConnectException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 文件描述：服务消费者
 * 创建作者：陈苗
 * 创建时间：2016/12/14 22:33
 */
public class ServiceConsumer {
    /*用于等待SyncConnected事件触发后继续执行当前线程*/
    private CountDownLatch latch = new CountDownLatch(1);
    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceConsumer.class);
    /*定义一个volatile变量，用于保存最新的RMI地址*/
    private volatile List<String> urlList = new ArrayList<String>();

    public ServiceConsumer() {
        ZooKeeper zk = connectServer();
        if (zk != null)
            watchNode(zk);/*观察服务注册目录下的所有子节点并更新urlList成员变量*/
    }

    /**
     * 观察服务注册目录下所有节点是否有变化
     * @param zk
     */
    private void watchNode(final ZooKeeper zk) {
        try {
            List<String> nodeList = zk.getChildren(Constant.ZK_REGISTER_PATH, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) 
                        watchNode(zk);
                }
            });
            List<String> dataList = new ArrayList<String>();/*用于存放服务注册目录下所有子节点的数据*/
            for (String node : nodeList) {
                byte[] data = zk.getData(Constant.ZK_REGISTER_PATH + "/" + node, false, null);
                dataList.add(new String(data));
            }
            LOGGER.debug("node data: {}", dataList);
            urlList = dataList;/*更新地址*/
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    /**
     * 连接ZooKeeper服务器
     * @return
     */
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(Constant.ZK_CONNECTION_STRING, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected)
                        latch.countDown();/*唤醒当前正在执行的线程*/
                }
            });
            latch.await();/*使当前线程处于等待状态*/
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return zk;
    }

    /**
     * 查找RMI服务
     * @param <T>
     * @return
     */
    public <T extends Remote> T loopUp() {
        T service = null;
        int size = urlList.size();
        if (size > 0) {
            String url;
            if (size == 1) {
                url = urlList.get(0);
                LOGGER.debug("using only url : {}", url);
            } else {
                url = urlList.get(ThreadLocalRandom.current().nextInt(size));
                LOGGER.debug("using random url : {}", url);
            } 
            service = loopupService(url);/*从JNDI中查找RMI服务*/
        }
        return service;
    }

    /**
     * 在JNDI中查找RMI远程服务对象
     * @param url
     * @param <T>
     * @return
     */
    private <T extends Remote> T loopupService(String url) {
        T remote = null;
        try {
            remote = (T) Naming.lookup(url);
        } catch (Exception e) {
            if (e instanceof ConnectException) {
                LOGGER.error("ConnectException -> url: {}", url);
                if (urlList.size() != 0) {
                    url = urlList.get(0);
                    return loopupService(url);
                }
            }
            LOGGER.error("", e);
        }
        return remote;
    }
}
