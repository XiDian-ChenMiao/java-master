package cn.xidian.thread.example;

/**
 * 文件描述：写线程
 * 创建作者：陈苗
 * 创建时间：2016/12/2 15:43
 */
public class WriteThread extends Thread {
    private final Data data;
    private final String str;
    private int index = 0;
    public WriteThread(Data data, String str) {
        this.data = data;
        this.str = str;
    }
    @Override
    public void run() {
        while (true) {
            try {
                data.write(next());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private char next() {
        char c = str.charAt(index);
        index++;
        if (index >= str.length()) {
            index = 0;
        }
        return c;
    }
}
