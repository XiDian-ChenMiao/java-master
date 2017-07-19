package cn.thread.example;

/**
 * 文件描述：读写数据的封装模拟类
 * 创建作者：陈苗
 * 创建时间：2016/12/2 15:37
 */
public class Data {

    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public Data(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = '*';
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String read() throws InterruptedException {
        lock.readLock();
        try {
            return doLoad();
        } finally {
            lock.readUnlock();
        }
    }

    private String doLoad() {
        StringBuilder result = new StringBuilder();
        for (char c : buffer)
            result.append(c);
        sleep(100);
        return result.toString();
    }

    public void write(char c) throws InterruptedException {
        lock.writeLock();
        try {
            doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            sleep(100);
        }
    }
}
