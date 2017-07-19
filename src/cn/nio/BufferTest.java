package cn.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件描述：java.nio包中的Buffer测试
 * NOTE:
 *  缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存。这块内存被包装成NIO Buffer对象，并提供一组方法，用来方便的访问该块内存。
 * 创建作者：陈苗
 * 创建时间：2016/10/14 15:53
 */
public class BufferTest {
    /**
     * 主函数
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("buffer.ini", "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int byte_read = fileChannel.read(buffer);
        while (byte_read != -1) {
            buffer.flip();/*将写模式改为读模式*/
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            buffer.clear();/*清除缓冲区，允许下次继续写入*/
            byte_read = fileChannel.read(buffer);
        }
        fileChannel.close();
        file.close();
    }
}
