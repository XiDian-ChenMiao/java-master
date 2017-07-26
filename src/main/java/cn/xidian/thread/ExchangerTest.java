package cn.xidian.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 文件描述：Exchanger测试
 * NOTE：
 *  用于实现双方的数据交换，每个人在完成一定的事务后想与对方交换数据，第一个先拿出数据的人将一直等待第二个人拿着数据到来时，才能彼此交换数据。
 * 创建作者：陈苗
 * 创建时间：2016/10/12 11:20
 */
public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Exchanger exchanger = new Exchanger();
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String one = "大秦之帝";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据-" + one + "-交换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    String two = (String) exchanger.exchange(one);
                    System.out.println("线程" + Thread.currentThread().getName() + "换回数据-" + two);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String one = "陈苗";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据-" + one + "-交换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    String two = (String) exchanger.exchange(one);
                    System.out.println("线程" + Thread.currentThread().getName() + "换回数据-" + two);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        service.shutdown();
    }
}
