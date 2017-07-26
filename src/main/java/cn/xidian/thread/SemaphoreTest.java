package cn.xidian.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 文件描述：Java中信号量的测试-同步工具
 * 创建作者：陈苗
 * 创建时间：2016/10/12 10:13
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("线程" + Thread.currentThread().getName() + "占用资源，当前可用资源为：" + semaphore.availablePermits());
                        Thread.sleep((long)(Math.random() * 10000));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }
            });
        }
    }
}
