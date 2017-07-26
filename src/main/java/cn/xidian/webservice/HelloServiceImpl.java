package cn.xidian.webservice;

import javax.jws.WebService;

/**
 * 文件描述：HelloServie接口实现类
 * 创建作者：陈苗
 * 创建时间：2017/3/13 15:47
 */
@WebService(
        serviceName = "HelloService",
        portName = "HelloServicePort",
        endpointInterface = "cn.xidian.webservice.HelloService"
)
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String name) {
        return "Hello " + name;
    }
}
