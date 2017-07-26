package cn.xidian.other;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 文件描述：线程使用类中的Callable和Future类测试
 * 创建作者：陈苗
 * 创建时间：2016/10/8 22:53
 */
public class FutureAndCallableTest {
    public static void main(String[] args) {
        ExecutorService threadLocal = Executors.newSingleThreadExecutor();
        Future<String> resultFuture = threadLocal.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "大秦之帝";
            }
        });
        System.out.println("等待结果");
        try {
            System.out.println("显示结果为：" + resultFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        threadLocal = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadLocal);
        for (int i = 0; i < 10; i++) {
            final int task = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return task;
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("获取任务结果：" + completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadLocal.shutdownNow();
    }
}
