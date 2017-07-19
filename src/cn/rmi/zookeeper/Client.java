package cn.rmi.zookeeper;

import cn.rmi.IHelloService;

import java.rmi.RemoteException;

/**
 * 文件描述：调用服务
 * 创建作者：陈苗
 * 创建时间：2016/12/14 22:55
 */
public class Client {
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, RemoteException {
        ServiceConsumer consumer = new ServiceConsumer();
        while (true) {
            IHelloService helloService = consumer.loopUp();
            String result = helloService.sayHello("Chen Miao");
            System.out.println(result);
            Thread.sleep(3000);
        }
    }
}
