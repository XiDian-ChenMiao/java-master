package cn.xidian.aio;

/**
 * 文件描述：AIO时间服务器客户端
 * 创建作者：陈苗
 * 创建时间：2017/8/16 20:41
 */
public class AIOTimeClient {
    public static void main(String[] args) {
        int port = 8000;
        try {
            if (args != null && args.length > 0)
                port = Integer.valueOf(args[0]);
        } catch (NumberFormatException e) {

        }
        new Thread(new AsyncTimeClientHandler("localhost", port));
    }
}
