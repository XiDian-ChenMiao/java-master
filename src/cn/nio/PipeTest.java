package cn.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Random;

/**
 * 文件描述：管道测试类
 * 创建作者：陈苗
 * 创建时间：2016/12/13 21:04
 */
public class PipeTest {
    /**
     * 工作线程封装类
     */
    private static class Worker extends Thread {
        private WritableByteChannel channel;
        private int reps;
        private String[] products = {
                "No good deed goes unpunished",
                "To be, or what?",
                "No matter where you go, there you are",
                "Just say"
        };
        private Random rand = new Random();
        Worker(WritableByteChannel channel, int reps) {
            this.channel =channel;
            this.reps = reps;
        }

        @Override
        public void run() {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            try {
                for (int i = 0; i < this.reps; i++) {
                    doSomeWork(buffer);
                    while (channel.write(buffer) > 0) {

                    }
                }
                this.channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void doSomeWork(ByteBuffer buffer) {
            int product = rand.nextInt(products.length);
            buffer.clear();
            buffer.put(products[product].getBytes());
            buffer.put("\r\n".getBytes());
            buffer.flip();
        }
    }

    /**
     * 主函数
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        WritableByteChannel out = Channels.newChannel(System.out);
        ReadableByteChannel workerChannel = startWorker(10);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        try {
            while (workerChannel.read(buffer) >= 0) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动工作线程方法
     * @param i
     * @return
     * @throws IOException
     */
    private static ReadableByteChannel startWorker(int i) throws IOException {
        Pipe pipe = Pipe.open();
        Worker worker = new Worker(pipe.sink(), i);
        worker.start();
        return pipe.source();
    }
}
