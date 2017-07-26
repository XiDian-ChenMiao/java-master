package cn.xidian.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * 文件描述：使用多线程处理方式和NIO编写的服务器端Socket程序
 * NOTE:使用线程池来为通道提供服务
 * 创建作者：陈苗
 * 创建时间：2016/12/14 16:07
 */
public class SocketNIOThreadServer extends SocketNIOServer {
    private static final int MAX_THREADS = 5;
    private ThreadPool pool = new ThreadPool(MAX_THREADS);
    /**
     * 线程池内部类
     */
    private class ThreadPool {
        List idle = new LinkedList();

        /**
         * 构造函数
         * @param poolSize 线程池容量
         */
        ThreadPool(int poolSize) {
            for (int i = 0; i < poolSize; i++) {
                WorkerThread thread = new WorkerThread(this);
                thread.setName("工作线程-" + (i + 1));
                thread.start();
                idle.add(thread);
            }
        }

        /**
         * 获取一个工作线程
         * @return
         */
        public WorkerThread getWorker() {
            WorkerThread worker = null;
            synchronized (idle) {
                if (idle.size() > 0)
                    worker = (WorkerThread) idle.remove(0);
            }
            return worker;
        }

        /**
         * 添加一个工作线程
         * @param worker
         */
        public void returnWorker(WorkerThread worker) {
            synchronized (idle) {
                idle.add(worker);
            }
        }

        /**
         *
         * @param key
         * @throws IOException
         */
        public void readDataFromSocket(SelectionKey key) throws IOException {
            WorkerThread worker = pool.getWorker();
            if (worker == null) {
                return;
            }
            worker.serviceChannel(key);
        }
    }

    /**
     * 工作线程内部类
     */
    private class WorkerThread extends Thread {
        private ByteBuffer buffer = ByteBuffer.allocate(1024);
        private ThreadPool pool;
        private SelectionKey selectionKey;
        WorkerThread(ThreadPool pool) {
            this.pool = pool;
        }

        public synchronized void run() {
            System.out.println(this.getName() + "is ready");
            while (true) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (selectionKey == null)
                    continue;
                System.out.println(this.getName() + "is awakened");
                try {
                    drainChannel(selectionKey);
                } catch (Exception e) {
                    System.out.println("Caught " + e + " closing channel");
                    try {
                        selectionKey.channel().close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    selectionKey.selector().wakeup();
                }
                selectionKey = null;
                this.pool.returnWorker(this);
            }
        }

        synchronized void serviceChannel(SelectionKey key) {
            this.selectionKey = key;
            selectionKey.interestOps(selectionKey.interestOps() & (~SelectionKey.OP_READ));
            this.notify();
        }

        void drainChannel(SelectionKey key) throws Exception {
            SocketChannel channel = (SocketChannel) key.channel();
            int count;
            buffer.clear();
            while ((count = channel.read(buffer)) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
                buffer.clear();
            }
            if (count < 0) {
                channel.close();
                return;
            }
            key.interestOps(key.interestOps() | SelectionKey.OP_READ);
            key.selector().wakeup();
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) throws IOException {
        new SocketNIOThreadServer().go(args);
    }
}
