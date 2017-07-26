package cn.xidian.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 文件描述：定义一个RMI接口
 * 创建作者：陈苗
 * 创建时间：2016/12/14 21:42
 */
public interface IHelloService extends Remote {
    String sayHello(String name) throws RemoteException;
}
