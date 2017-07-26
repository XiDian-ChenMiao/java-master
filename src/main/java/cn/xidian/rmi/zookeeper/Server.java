package cn.xidian.rmi.zookeeper;

import cn.xidian.rmi.HelloServiceImpl;
import cn.xidian.rmi.IHelloService;

import java.rmi.RemoteException;

/**
 * 文件描述：发布服务
 * 创建作者：陈苗
 * 创建时间：2016/12/14 22:52
 */
public class Server {
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) throws RemoteException, InterruptedException {
        if (args.length != 2) {
            System.err.println("please using command: java Server <rmi_host> <rmi_port>");
            System.exit(-1);
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        ServiceProvider provider = new ServiceProvider();
        IHelloService helloService = new HelloServiceImpl();
        provider.publish(helloService, host, port);
        Thread.sleep(Long.MAX_VALUE);
    }
}
