package cn.xidian.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 文件描述：发布RMI服务
 * NOTE（使用RMI的两点局限性）:
 * （1）RMI使用了Java默认的序列化方式，对于性能要求比较高的系统，可能需要使用其他序列化方案解决；
 * （2）RMI服务在运行时难免会发生故障。例如，如果RMI服务无法连接了，就会导致客户端无法响应的现象；
 * 在一般的情况下，使用Java默认的序列化方式确实已经足以满足我们的要求，重点是解决第二点，即使系统具备高可用性（HA），方案就是使用ZooKeeper；
 * 创建作者：陈苗
 * 创建时间：2016/12/14 21:45
 */
public class RMIMain {
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        int port = 1099;
        String url = "rmi://localhost:1099/cn.xidian.rmi.HelloServiceImpl";
        LocateRegistry.createRegistry(port);
        Naming.rebind(url, new HelloServiceImpl());
    }
}
