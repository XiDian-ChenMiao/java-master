package cn.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 文件描述：路障类测试
 * NOTE：
 *  表示大家彼此等待，大家集合好后才开始出发，分散活动后又在指定地点集合碰面。
 * 创建作者：陈苗
 * 创建时间：2016/10/12 10:44
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CyclicBarrier barrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long)(Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点一，当前已有" + (barrier.getNumberWaiting() + 1 + "等待"));
                        barrier.await();

                        Thread.sleep((long)(Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点二，当前已有" + (barrier.getNumberWaiting() + 1 + "等待"));
                        barrier.await();

                        Thread.sleep((long)(Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点三，当前已有" + (barrier.getNumberWaiting() + 1 + "等待"));
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(task);
        }
    }
}
