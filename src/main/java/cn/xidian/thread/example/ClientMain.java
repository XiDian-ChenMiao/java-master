package cn.xidian.thread.example;

/**
 * 文件描述：读写线程客户端测试类
 * 创建作者：陈苗
 * 创建时间：2016/12/2 15:47
 */
public class ClientMain {

    public static void main(String[] args) {
        Data data = new Data(10);
        for (int i = 0; i < 5; i++) {
            new ReadThread(data).start();
        }

        for (int i = 0; i < 2; i++) {
            new WriteThread(data, "ABCDEFGHI").start();
        }
    }
}
