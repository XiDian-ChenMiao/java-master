package cn.thread;

import java.util.Random;

/**
 * 文件描述：ThreadLocal类的使用
 * 创建作者：陈苗
 * 创建时间：2016/10/7 21:16
 */
public class ThreadLocalTest {
    /**
     * 静态内部类封装线程数据
     */
    static class ThreadData {
        private final static ThreadLocal<ThreadData> map = new ThreadLocal<ThreadData>();
        private ThreadData() {}
        private String threadName;
        private Object value;

        public String getThreadName() {
            return threadName;
        }

        public void setThreadName(String threadName) {
            this.threadName = threadName;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        /**
         * 获取实例
         * @return
         */
        public static ThreadData getInstance() {
            ThreadData threadData = map.get();
            if (threadData == null) {
                threadData = new ThreadData();
                map.set(threadData);
            }
            return threadData;
        }
    }
    static class A {
        public void get() {
            ThreadData data = ThreadData.getInstance();
            System.out.println("A从线程" + data.getThreadName() + "中获取到值：" + data.getValue());
        }
    }
    static class B {
        public void get() {
            ThreadData data = ThreadData.getInstance();
            System.out.println("B从线程" + data.getThreadName() + "中获取到值：" + data.getValue());
        }
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ThreadData.getInstance().setThreadName(Thread.currentThread().getName());
                    ThreadData.getInstance().setValue(new Random().nextInt());
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }
}
