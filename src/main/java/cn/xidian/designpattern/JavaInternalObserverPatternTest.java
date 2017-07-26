package cn.xidian.designpattern;

import java.util.Observable;
import java.util.Observer;

/**
 * 文件描述：Java内置观察者模式测试
 * 创建作者：陈苗
 * 创建时间：2017/3/3 10:14
 */
public class JavaInternalObserverPatternTest extends Observable {
    private static class OneObserver implements Observer {
        /**
         * This method is called whenever the observed object is changed. An
         * application calls an <tt>Observable</tt> object's
         * <code>notifyObservers</code> method to have all the object's
         * observers notified of the change.
         *
         * @param o   the observable object.
         * @param arg an argument passed to the <code>notifyObservers</code>
         */
        @Override
        public void update(Observable o, Object arg) {
            System.out.println(this.getClass().getName() + "接收到通知：" + o.getClass().getName() + "倒计时时间为" + arg);
        }
    }

    public static class TwoObserver implements Observer {
        /**
         * This method is called whenever the observed object is changed. An
         * application calls an <tt>Observable</tt> object's
         * <code>notifyObservers</code> method to have all the object's
         * observers notified of the change.
         *
         * @param o   the observable object.
         * @param arg an argument passed to the <code>notifyObservers</code>
         */
        @Override
        public void update(Observable o, Object arg) {
            if (((Integer) arg).intValue() <= 5) {
                System.out.println(this.getClass().getName() + "接收到通知：" + o.getClass().getName() + "倒计时时间为" + arg);
            }
        }
    }

    /**
     * 倒计时方法
     * @param seconds
     * @throws InterruptedException
     */
    public void countdown(int seconds) throws InterruptedException {
        for (; seconds >= 0; seconds--) {
            setChanged();
            Thread.sleep(1000);
            notifyObservers(seconds);
        }
    }

    /**
     * 主程序方法
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        JavaInternalObserverPatternTest subject = new JavaInternalObserverPatternTest();
        subject.addObserver(new OneObserver());
        subject.addObserver(new TwoObserver());
        subject.countdown(10);
    }
}

