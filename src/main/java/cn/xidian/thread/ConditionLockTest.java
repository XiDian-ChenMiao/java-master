package cn.xidian.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * NOTE:
 *  signal()：若有一个或者若干个线程在等待该条件变量，则该方法会唤醒其中随机的一个。调用该方法的前提是当前线程持有该条件变量对应的锁，否则抛出IlegalMonitorStateException；
 *  signalAll():若有一个或若干个线程在等待该条件变量，则该方法会唤醒所有等待。调用该方法的前提是当前线程持有该条件变量对应的锁，否则抛出IlegalMonitorStateException；
 * 文件描述：条件锁测试
 * 创建作者：陈苗
 * 创建时间：2016/8/31 17:40
 */
public class ConditionLockTest {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(new Date() + "\t线程1正在等待");
                    try {
                        long waitTime = condition.awaitNanos(TimeUnit.SECONDS.toNanos(2));
                        System.out.println(new Date() + "\t线程1停留时间为：" + waitTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date() + "\t线程1被唤醒");
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(new Date() + "\t线程2正在运行");
                    try {
                        Thread.sleep(4000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    condition.signal();
                    System.out.println(new Date() + "\t线程2结束");
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
