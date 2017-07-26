package cn.xidian.thread.example;

/**
 * 文件描述：读线程
 * 创建作者：陈苗
 * 创建时间：2016/12/2 15:42
 */
public class ReadThread extends Thread {

    private final Data data;

    public ReadThread(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = data.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "=>" + result);
        }
    }
}
