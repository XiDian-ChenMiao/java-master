package cn.xidian.aio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * 文件描述：异步客户端处理器
 * 创建作者：陈苗
 * 创建时间：2017/8/16 20:43
 */
public class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {
    private final static Logger logger = LoggerFactory.getLogger(AsyncTimeClientHandler.class);
    private AsynchronousSocketChannel client;
    private String host;
    private int port;
    private CountDownLatch countDownLatch;

    public AsyncTimeClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            client = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            logger.error(e.getMessage());
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
        client.connect(new InetSocketAddress(host, port), this, this);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Invoked when an operation has completed.
     *
     * @param result     The result of the I/O operation.
     * @param attachment
     */
    @Override
    public void completed(Void result, AsyncTimeClientHandler attachment) {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(req.length);
        buffer.put(req);
        buffer.flip();
        client.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (buffer.hasRemaining()) {
                    client.write(buffer, buffer, this);
                } else {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    client.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            String body;
                            try {
                                body = new String(bytes, "UTF-8");
                                logger.info("Now is " + body);
                                countDownLatch.countDown();
                            } catch (UnsupportedEncodingException e) {
                                logger.error(e.getMessage());
                            }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            try {
                                client.close();
                            } catch (IOException e) {
                                logger.error(e.getMessage());
                            }
                            countDownLatch.countDown();
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    client.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
                countDownLatch.countDown();
            }
        });
    }

    /**
     * Invoked when an operation fails.
     *
     * @param exc        The exception to indicate why the I/O operation failed
     * @param attachment
     */
    @Override
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
        logger.error(exc.getMessage());
        try {
            client.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        countDownLatch.countDown();
    }
}
