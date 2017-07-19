package cn.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 文件描述：线程高级类测试
 * 创建作者：陈苗
 * 创建时间：2016年6月5日 15:57
 */
public class ThreadPoolTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    /**
     * 测试本地线程类ThreadLocal
     */
    public static void testTheadLocal() {
        class A {
            public void get() {
                System.out.println("从线程" + Thread.currentThread().getName() + "中取出数据为：" + threadLocal.get());
            }
        }
        class B {
            public void get() {
                System.out.println("从线程" + Thread.currentThread().getName() + "中取出数据为：" + threadLocal.get());
            }
        }
        for (int i = 0; i < 10;++i) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int randomData = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + "产生随机数据：" + randomData);
                    threadLocal.set(randomData);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    /**
     * 测试线程池类Executors，固定线程池
     */
    public static void testThreadPoolOfFixed() {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建固定的线程池
        //ExecutorService threadPool = Executors.newCachedThreadPool();//创建缓冲的线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//创建一个但线程池，线程如果死亡会重新创建一个
        /*
        Executors.newScheduledThreadPool(5).schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是大秦之帝");
            }
        },5, TimeUnit.SECONDS);*/

        for (int i = 0; i < 5; i++) {
            final int task = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName() + "正在进行第" + task + "个任务，第" + j + "次循环");
                    }
                }
            });
        }
        threadPool.shutdown();//当所有任务完成之后关闭线程池
    }

    /**
     * 测试Callable和Future
     */
    public static void testCallableAndFuture() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //可以去到调用的返回结果
        Future<String> result = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "大秦之帝";
            }
        });
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等线程运行完成在直接去取结果
     */
    public static void testCompletionService() {
        final ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
        for (int i = 0; i < 5; i++) {
            final int task = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(4000);
                    return task;
                }
            });
        }

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 主线程
     * @param args
     */
    public static void main(String[] args) {
        testThreadPoolOfFixed();
        testTheadLocal();
        testCallableAndFuture();
        testCompletionService();
    }
}
