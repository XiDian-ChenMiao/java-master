package cn.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 文件描述：文件通道测试类
 * 创建作者：陈苗
 * 创建时间：2016/10/14 16:22
 */
public class FileChannelTest {
    final static String PROJECT_PATH = "E:\\JAVA\\Java代码学习\\JavaDailyDemoTest\\src\\cn\\nio\\";

    /**
     * 主函数
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        RandomAccessFile from_file = new RandomAccessFile(PROJECT_PATH + "buffer.ini", "rw");
        FileChannel from_channel = from_file.getChannel();

        RandomAccessFile to_file = new RandomAccessFile(PROJECT_PATH + "buffer-copy.ini", "rw");
        FileChannel to_channel = to_file.getChannel();
        long position = 0, size = from_channel.size();
        to_channel.transferTo(position, size, from_channel);
        to_channel.close();
        from_channel.close();
    }
}
