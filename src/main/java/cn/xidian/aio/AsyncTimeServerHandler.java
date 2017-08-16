package cn.xidian.aio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * 文件描述：异步时间服务处理器
 * 创建作者：陈苗
 * 创建时间：2017/8/16 18:16
 */
public class AsyncTimeServerHandler implements Runnable {
    private final static Logger logger = LoggerFactory.getLogger(AsyncTimeServerHandler.class);
    private int port;
    CountDownLatch countDownLatch;
    AsynchronousServerSocketChannel serverSocketChannel;

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));/*绑定具体端口*/
            logger.info("The time server is started in port " + port);
        } catch (IOException e) {
            logger.error("The time server is not started.");
        }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        countDownLatch = new CountDownLatch(1);
        accept();
        try {
            countDownLatch.await();/*作用是在完成一组正在执行的操作之前，允许当前的线程一直阻塞。现在让线程在此阻塞，防止服务端执行完成退出。*/
        } catch (InterruptedException e) {
            logger.error("countdownlatch error");
        }
    }

    private void accept() {
        serverSocketChannel.accept(this, new AcceptCompletionHandler());
    }
}
