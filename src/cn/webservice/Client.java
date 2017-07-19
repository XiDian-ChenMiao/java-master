package cn.webservice;

/**
 * 文件描述：WebService客户端
 * 创建作者：陈苗
 * 创建时间：2017/3/13 16:00
 */
public class Client {
    public static void main(String[] args) {
        HelloService_Service service = new HelloService_Service();
        HelloService helloService = service.getHelloServicePort();
        String result = helloService.say("DAQINZHIDI");
        System.out.println(result);
    }
}
