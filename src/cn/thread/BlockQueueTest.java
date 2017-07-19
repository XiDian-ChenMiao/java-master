package cn.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 文件描述：阻塞队列类测试
 * 创建作者：陈苗
 * 创建时间：2016/10/12 15:39
 */
public class BlockQueueTest {
    public static void main(String[] args) {
        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((long) (Math.random() * 10000));
                            System.out.println("线程" + Thread.currentThread().getName() + "准备放入数据");
                            queue.put(1);
                            System.out.println("线程" + Thread.currentThread().getName() + "已经放入数据，队列中目前有" + queue.size() + "个数据，还能放入" + (3 - queue.size()) + "个数据");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("线程" + Thread.currentThread().getName() + "准备获取数据");
                        queue.take();
                        System.out.println("线程" + Thread.currentThread().getName() + "已经取走数据，队列中目前有" + queue.size() + "个数据，还能放入" + (3 - queue.size()) + "个数据");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
