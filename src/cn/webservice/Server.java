package cn.webservice;

import javax.xml.ws.Endpoint;

/**
 * 文件描述：WebService服务类
 * 创建作者：陈苗
 * 创建时间：2017/3/13 15:49
 */
public class Server {
    public static void main(String[] args) {
        String address = "http://localhost:9000/ws/soap/hello";
        HelloService helloService = new HelloServiceImpl();
        Endpoint.publish(address, helloService);
        System.out.println("发布服务");
    }
}
