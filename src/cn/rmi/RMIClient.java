package cn.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 文件描述：RMI服务的调用客户端
 * 创建作者：陈苗
 * 创建时间：2016/12/14 21:47
 */
public class RMIClient {
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String url = "rmi://localhost:1099/cn.rmi.HelloServiceImpl";
        IHelloService helloService = (IHelloService) Naming.lookup(url);
        String result = helloService.sayHello("Jack");
        System.out.println(result);
    }
}
