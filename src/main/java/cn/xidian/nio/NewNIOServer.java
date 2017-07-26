package cn.xidian.nio;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.nio.channels.SelectionKey;
import java.text.SimpleDateFormat;
import java.util.*;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.Socket;

/**
 * NIO下的服务器端
 */
public class NewNIOServer {

    private Selector selector;/*NIO的分发器*/
    private static Map<Socket, Long> time = new HashMap<Socket, Long>();/*用于记录每个Socket连接处理的时间*/
    private ExecutorService tp = Executors.newCachedThreadPool();/*线程池*/
    public static final Integer DEFAULT_PORT = 8000;/*默认端口号*/
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 启动服务器的方法
     * @param port
     * @throws Exception
     */
    public void startServer(int port) throws Exception {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);/*设置为非阻塞模式*/

        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), port);
        System.out.println("Start Server " + InetAddress.getLocalHost() + ": " + port);
        ssc.socket().bind(isa);/*服务器端通道绑定本地地址及指定端口*/

        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);/*将通道注册进入分发器中，并关注客户端的连接事件*/
        for (; ;) {
            selector.select();/*在此阻塞调用，如果有服务器端感兴趣的事件发生则执行下面的程序*/
            Set<SelectionKey> readyKeys = selector.selectedKeys();/*获取发生事件的通道SelectionKey集合*/
            Iterator i = readyKeys.iterator();
            long e;
            while (i.hasNext()) {
                SelectionKey sk = (SelectionKey) i.next();/*单独处理*/
                i.remove();/*为了避免事件重复，则从集合中移除之*/
                if (sk.isAcceptable()) {/*处理连接事件*/
                    doAccept(sk);
                } else if (sk.isValid() && sk.isReadable()) {/*处理读事件*/
                    if (!time.containsKey(((SocketChannel) sk.channel()).socket()))
                        time.put(((SocketChannel) sk.channel()).socket(), System.currentTimeMillis());
                    doRead(sk);
                } else if (sk.isValid() && sk.isWritable()) {/*处理写事件*/
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = time.remove(((SocketChannel) sk.channel()).socket());/*处理完成之后从映射表中移除相应Socket键值对*/
                    System.out.println(((SocketChannel) sk.channel()).socket() + ", spend:" + (e - b) + " ms.");
                }
            }
        }
    }

    class EchoClient {
        private LinkedList<ByteBuffer> outQueue;

        EchoClient() {
            outQueue = new LinkedList<ByteBuffer>();
        }

        public LinkedList<ByteBuffer> getOutputQueue() {
            return outQueue;
        }

        public void enQueue(ByteBuffer buffer) {
            outQueue.addFirst(buffer);
        }
    }

    /**
     * 服务器端处理连接事件
     * @param sk
     */
    private void doAccept(SelectionKey sk) {
        ServerSocketChannel serverChannel = (ServerSocketChannel) sk.channel();
        SocketChannel clientChannel;
        try {
            clientChannel = serverChannel.accept();/*获取客户端的通道*/
            clientChannel.configureBlocking(false);/*设置为非阻塞模式*/
            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);/*将客户端通道注册进入分发器并关注服务器对其的读事件*/

            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);/*设置附件用于全局处理*/

            InetAddress clientAddress = clientChannel.socket().getInetAddress();
            System.out.println("client address : " + clientAddress.getHostAddress());
        } catch (Exception e) {
            System.out.println("Fail to connect.");
            e.printStackTrace();
        } 
    }

    /**
     * 处理具体消息的线程
     */
    class HandleMsg implements Runnable {
        SelectionKey sk;
        ByteBuffer buffer;

        public HandleMsg(SelectionKey sk, ByteBuffer buffer) {
            this.sk = sk;
            this.buffer = buffer;
        }

        @Override
        public void run() {
            EchoClient echoClient = (EchoClient) sk.attachment();/*从相关的SelectionKey中获取由连接事件中附加的附件对象，用于在此完成消息的添加*/
            echoClient.enQueue(ByteBuffer.wrap(new String(format.format(new Date()) + ", Server Response : " + new String(buffer.array())).getBytes()));

            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);/*重新设置此SelectionKey关注的事件是读和写*/
            selector.wakeup();/*强迫selector立即返回*/
        }
    }

    /**
     * 处理服务器端的读事件
     * @param sk
     */
    private void doRead(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        int length;
        try {
            length = channel.read(buffer);/*阻塞读*/
            if (length < 0) {
                disconnect(sk);
                return;
            }
            System.out.println("读出内容为：" + new String(buffer.array()).trim());
        } catch (Exception e) {
            System.out.println("Fail to read from client.");
            e.printStackTrace();
            disconnect(sk);
            return;
        }

        buffer.flip();
        tp.execute(new HandleMsg(sk, buffer));/*将读到的信息交由线程池来安排处理*/
    }

    /**
     * 处理服务器端的写事件
     * @param sk
     */
    private void doWrite(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        EchoClient echoClient = (EchoClient) sk.attachment();
        LinkedList<ByteBuffer> outQueue = echoClient.getOutputQueue();
        ByteBuffer buffer = outQueue.getLast();
        try {
            int length = channel.write(buffer);
            if (length == -1) {
                disconnect(sk);
                return;
            }
            if (buffer.remaining() == 0) 
                outQueue.removeLast();
        } catch (Exception e) {
            System.out.println("Fail to write to client.");
            e.printStackTrace();
            disconnect(sk);
        }
        if (outQueue.size() == 0)
            sk.interestOps(SelectionKey.OP_READ);/*如果附件中的输出队列现在内容为空，则设置与其相关的SelectionKey所关注的事件为只读*/
    }

    /**
     * 关闭Socket连接
     * @param sk
     */
    private void disconnect(SelectionKey sk) {
        try {
            if (sk.channel() != null)
                sk.channel().close();
        } catch (Exception e) {
            System.out.println("Fail to close socket.");
            e.printStackTrace();
        }
    }

    /**
     * 主程序调用
     * @param args
     */
    public static void main(String[] args) {
        NewNIOServer server = new NewNIOServer();
        try {
            if (args != null && args.length == 1)
                server.startServer(Integer.parseInt(args[0]));
            else 
                server.startServer(NewNIOServer.DEFAULT_PORT);
        } catch (Exception e) {
            System.out.println("Fail to start servlet.");
            e.printStackTrace();
        }
    }
}

