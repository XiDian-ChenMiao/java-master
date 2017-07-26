package cn.xidian.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 文件描述：NIO服务器端
 * 创建作者：陈苗
 * 创建时间：2016/12/14 19:18
 */
public class NIOServer {
    private Selector selector;

    /**
     * 服务器初始化函数
     * @param port
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        /*获取一个ServerSocket通道*/
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        /*设置通道为非阻塞*/
        serverSocketChannel.configureBlocking(false);
        /*将通道对应的ServerSocket绑定到port端口*/
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        /*获得一个通道管理器*/
        this.selector = Selector.open();
        /*将通道管理器与该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，注册该事件后，当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞*/
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听选择器上是否有需要处理的事件，如果有，则进行处理
     * @throws IOException
     */
    public void listen() throws IOException {
        System.out.println("Start the server：");
        while (true) {
            /*当注册的事件到达时，方法返回；否则，该方法会一直阻塞*/
            selector.select();
            Iterator iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                /*删除已选的选择键，防止重复处理*/
                iterator.remove();
                /*客户端的请求事件*/
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    /*获得和客户端连接的通道*/
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);/*设置成非阻塞*/
                    socketChannel.write(ByteBuffer.wrap(new String("Hello, this is server!\r\n").getBytes()));
                    /*在和客户端连接成功之后，为了可以接受客户端的消息，需要给通道设置读的权限*/
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    readFromSocket(key);
                }
            }
        }
    }

    /**
     * 从通道中读取来自客户端发送的信息
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
        System.out.println("Server Received：" + mesg);
        /*服务器将消息回送给客户端*/
        channel.write(ByteBuffer.wrap(mesg.getBytes()));
    }

    /**
     * 主函数
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(9000);
        server.listen();
    }
}
