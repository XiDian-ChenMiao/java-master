package cn.xidian.aio;

/**
 * 文件描述：采用AIO形式实现的TimeServer
 * 创建作者：陈苗
 * 创建时间：2017/8/16 18:12
 */
public class AIOTimeServer {

    public static void main(String[] args) {
        int port = 8000;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {

            }
        }
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-TIME-SERVER").start();
    }
}
