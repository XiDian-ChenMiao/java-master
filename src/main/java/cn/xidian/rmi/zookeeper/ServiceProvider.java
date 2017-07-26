package cn.xidian.rmi.zookeeper;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;
interface Constant {
    String ZK_CONNECTION_STRING = "localhost:2181";
    int ZK_SESSION_TIMEOUT = 5000;
    String ZK_REGISTER_PATH = "/registry";
    String ZK_PROVIDER_PATH = ZK_REGISTER_PATH + "/provider";
}
/**
 * 文件描述：服务提供者
 * 创建作者：陈苗
 * 创建时间：2016/12/14 22:02
 */
public class ServiceProvider {
    /*用于等待SyncConnected事件触发后继续执行当前线程*/
    private CountDownLatch latch = new CountDownLatch(1);
    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceProvider.class);
    /**
     * 发布RMI服务并注册RMI地址到ZooKeeper中
     * @param remote
     * @param host
     * @param port
     */
    public void publish(Remote remote, String host, int port) {
        String url = publishService(remote, host, port);/*发布RMI服务并返回RMI地址*/
        if (url != null) {
            ZooKeeper zk = connectServer();/*连接ZooKeeper服务器并获取ZooKeeper对象*/
            if (zk != null)
                createNode(zk, url);
        }
    }

    /**
     * 创建ZNode
     * @param zk
     * @param url
     */
    private void createNode(ZooKeeper zk, String url) {
        try {
            byte[] data = url.getBytes();
            String path = zk.create(Constant.ZK_PROVIDER_PATH, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);/*创建一个临时性且有序的ZNode*/
            LOGGER.debug("create zookeeper node ({} => {})", path, url);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    /**
     * 发布RMI服务
     * @param remote
     * @param host
     * @param port
     * @return
     */
    private String publishService(Remote remote, String host, int port) {
        String url = null;
        try {
            url = String.format("rmi://%s:%d/%s", host, port, remote.getClass().getName());
            LocateRegistry.createRegistry(port);
            Naming.rebind(url, remote);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return url;
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
}
