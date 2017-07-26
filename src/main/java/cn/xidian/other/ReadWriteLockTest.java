package cn.xidian.other;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 文件描述：读写锁的测试
 * 创建作者：陈苗
 * 创建时间：2016/8/31 16:07
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readLock().lock();
                try {
                    System.out.println(new Date() + "线程1占用了读锁");
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date() + "线程1结束");
                } finally {
                    readWriteLock.readLock().unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readLock().lock();
                try {
                    System.out.println(new Date() + "线程2占用了读锁");
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date() + "线程2结束");
                } finally {
                    readWriteLock.readLock().unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Lock writeLock = readWriteLock.writeLock();
                writeLock.lock();
                try {
                    System.out.println(new Date() + "线程3占用了写锁");
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date() + "线程3结束");
                } finally {
                    writeLock.unlock();
                }
            }
        }).start();
    }
}
