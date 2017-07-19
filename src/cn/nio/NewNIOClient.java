package cn.nio;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.io.IOException;

/**
 * NIO下的客户端
 */
public class NewNIOClient {
    private Selector selector;
    public static final Integer DEFAULT_PORT = 8000;

    /**
     * 初始化操作
     * @param ip
     * @param port
     * @throws IOException
     */
    public void init(String ip, int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        this.selector = SelectorProvider.provider().openSelector();
        channel.connect(new InetSocketAddress(ip, port));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * 客户端具体工作方法
     * @throws IOException
     */
    public void working() throws IOException {
        while (true) {
            if (!selector.isOpen())
                break;
            selector.select();
            Iterator<SelectionKey> iter = this.selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey sk = iter.next();
                iter.remove();
                if (sk.isConnectable())
                    connect(sk);
                else if (sk.isReadable())
                    read(sk);
            }
        }
    }

    /**
     * 处理连接事件
     * @param sk
     * @throws IOException
     */
    private void connect(SelectionKey sk) throws IOException {
        SocketChannel channel = (SocketChannel) sk.channel();
        if (channel.isConnectionPending())
            channel.finishConnect();
        channel.configureBlocking(false);
        channel.write(ByteBuffer.wrap(new String("Hello Server!" + (int) (Math.random() * 100) + "\r\n").getBytes()));
        channel.register(this.selector, SelectionKey.OP_READ);
    }

    /**
     * 处理读事件
     * @param key
     * @throws IOException
     */
    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);
        byte[] data = buffer.array();
        String mesg = new String(data).trim();
        System.out.println("Client Received : " + mesg);
        channel.close();
        key.selector().close();
    }

    /**
     * 主函数调用
     * @param args
     */
    public static void main(String[] args) {
        NewNIOClient client = new NewNIOClient();
        try {
            if (args != null && args.length == 1)
                client.init("192.168.2.201", Integer.parseInt(args[0]));
            else
                client.init("192.168.2.201", NewNIOClient.DEFAULT_PORT);
            client.working();
        } catch (Exception e) {
            System.out.println("Fail to connect servlet.");
            e.printStackTrace();
        }
    }
}