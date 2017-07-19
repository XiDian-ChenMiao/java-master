package cn.webservice;

import javax.jws.WebService;

/**
 * 文件描述：WebService服务接口
 * 创建作者：陈苗
 * 创建时间：2017/3/13 15:42
 */
@WebService
public interface HelloService {
    String say(String name);
}
