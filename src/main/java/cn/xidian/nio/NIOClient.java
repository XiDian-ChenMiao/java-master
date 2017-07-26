package cn.xidian.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 文件描述：NIO客户端
 * 创建作者：陈苗
 * 创建时间：2016/12/14 19:53
 */
public class NIOClient {

    private Selector selector;

    /**
     * 初始化函数
     * @param address
     * @param port
     * @throws IOException
     */
    public void initClient(String address, int port) throws IOException {
        SocketChannel channel = SocketChannel.open();/*获取Socket通道*/
        channel.configureBlocking(false);/*设置为非阻塞*/
        this.selector = Selector.open();/*获取通道选择器*/
        /*客户端连接服务器，其实方法执行并没有实现连接，需要在listen方法中调用通道的finishConnect方法才能完成连接*/
        channel.connect(new InetSocketAddress(address, port));
        /*将通道管理器与该通道绑定，并未该通道注册SelectionKey.OP_CONNECT事件*/
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * 采用轮询的方式监听选择器上是否有需要处理的事件，如果有则进行处理
     * @throws IOException
     */
    public void listen() throws IOException {
        while (true) {
            selector.select();
            Iterator iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                iterator.remove();/*删除已选的选择键，防止重复处理*/
                /*连接事件发生*/
                if (key.isConnectable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    /*如果正在连接，则完成连接*/
                    if (socketChannel.isConnectionPending())
                        socketChannel.finishConnect();
                    socketChannel.configureBlocking(false);
                    socketChannel.write(ByteBuffer.wrap("Hello, this is client\r\n".getBytes()));
                    /*在和客户端连接成功之后，为了可以接受客户端的消息，需要给通道设置读的权限*/
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    readFromSocket(key);
                }
            }
        }
    }

    /**
     * 从通道中读取来自服务器端发送的消息
     * @param key
     */
    private void readFromSocket(SelectionKey key) throws IOException {
        /*服务器可读取消息，得到事件发生的Socket通道*/
        SocketChannel channel = (SocketChannel) key.channel();
        /*创建读取的缓冲区*/
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        byte[] data = buffer.array();
        String mesg = new String(data).trim();
        System.out.println("Client Received：" + mesg);
        /*服务器将消息回送给客户端*/
        channel.write(ByteBuffer.wrap(mesg.getBytes()));
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) throws IOException {
        NIOClient client = new NIOClient();
        client.initClient("localhost", 9000);
        client.listen();
    }
}
