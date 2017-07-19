package cn.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 文件描述：倒计时工具类测试
 * NOTE：
 *  好比倒计时计算器，调用CountDownLatch对象的countDown方法就将计数器减1，当计数到达0时，则所有等待着或者单个等待者可以执行。
 * 创建作者：陈苗
 * 创建时间：2016/10/12 10:52
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);
        for (int i = 0; i < 3; ++i) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() + "正准备接受命令");
                        cdOrder.await();
                        System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");
                        Thread.sleep((long)(Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结果");
                        cdAnswer.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(task);
        }

        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("线程" + Thread.currentThread().getName() + "正准备发送命令");
            cdOrder.countDown();/*准备发送命令*/
            System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正准备接受结果");
            cdAnswer.await();
            System.out.println("线程" + Thread.currentThread().getName() + "已收到响应结果");
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
