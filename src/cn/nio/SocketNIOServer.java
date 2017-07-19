package cn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 文件描述：使用select方式编写Socket的服务器端程序
 * 创建作者：陈苗
 * 创建时间：2016/12/13 22:27
 */
public class SocketNIOServer {
    public static int PORT_NUMBER = 8888;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    /**
     * 启动函数
     * @param argv
     */
    public void go(String[] argv) throws IOException {
        int port = PORT_NUMBER;
        if (argv.length > 0)
            port = Integer.parseInt(argv[0]);
        System.out.println("listening on port " + port);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        Selector selector = Selector.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int accept_number = selector.select();/*可能会被无限期的阻塞*/
            if (accept_number == 0)
                continue;
            Iterator iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                if (key.isAcceptable()) {/*如果是新连接，则注册*/
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    sayHello(channel);
                }
                if (key.isReadable()) {
                    readDataFromSocket(key);
                }
                iterator.remove();
            }
        }
    }

    /**
     * 从Socket中读取数据方法
     * @param key
     */
    public void readDataFromSocket(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        buffer.clear();
        while ((count = socketChannel.read(buffer)) > 0) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);/*发送数据*/
            }
            buffer.clear();
        }
        if (count < 0)
            socketChannel.close();/*如果读到EOF，则关闭通道，使选择键失效*/
    }

    /**
     * 服务器端响应
     * @param channel
     * @throws IOException
     */
    private void sayHello(SocketChannel channel) throws IOException {
        buffer.clear();
        buffer.put("Hi, there is server!\r\n".getBytes());
        buffer.flip();
        channel.write(buffer);
    }

    /**
     * 给指定的选择器上面注册指定的通道
     * @param selector
     * @param channel
     * @param opRead
     */
    private void registerChannel(Selector selector, SocketChannel channel, int opRead) throws IOException {
        if (channel == null)
            return;
        channel.configureBlocking(false);
        channel.register(selector, opRead);
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) throws IOException {
        new SocketNIOServer().go(args);
    }
}
