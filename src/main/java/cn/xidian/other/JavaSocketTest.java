package cn.xidian.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
/**
 * Socket通信之TCP服务器端
 * @ClassName: Server 
 * @author 陈苗 
 * @date 2016年4月15日 下午10:16:26
 */
class TcpServer {
	/**
	 * Server类的内部处理线程类
	 * @ClassName: ServerThread 
	 * @author 陈苗 
	 * @date 2016年4月15日 下午10:52:11
	 */
	private class ServerThread extends Thread {
		Socket socket = null;
		public ServerThread(Socket socket) {
			this.socket = socket;
		}
		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			String data = null;
			BufferedReader reader = null;
			PrintWriter printWriter = null;
			try{
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while((data = reader.readLine()) != null)
					System.out.println("我是服务器，客户端发送信息为：" + data);
				socket.shutdownInput();
				printWriter = new PrintWriter(socket.getOutputStream());
				printWriter.write("欢迎您访问服务器，访问时间为：" + new Date().toLocaleString());
				printWriter.flush();
				
			}catch(Exception exception){
				exception.printStackTrace();
			}finally{
				try{
					if(printWriter != null)
						printWriter.close();
					if(reader != null)
						reader.close();
					if(socket != null)
						socket.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * 服务器端监听端口函数
	 * @throws IOException 
	 */
	public void startListen() throws IOException {
		ServerSocket serverSocket = null;
		int clientCounter = 0;//客户端连接计数器
		try{
			serverSocket = new ServerSocket(8080);//创建指定端口的服务器端Socket
			System.out.println("服务器即将启动，等待服务器端的连接……");
			Socket socket = null;
			while(true){
				socket = serverSocket.accept();//调用accept方法开始循环监听，等待客户端的连接，其为阻塞式
				if(socket != null){
					ServerThread serverThread = this.new ServerThread(socket);
					serverThread.start();
					System.out.println("客户端的数量为：" + (++clientCounter));
					System.out.println("客户端的地址信息为：" + socket.getInetAddress());
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			serverSocket.close();
		}
	}
}
/**
 * Socket通信之TCP客户端
 * @ClassName: Server 
 * @author 陈苗 
 * @date 2016年4月15日 下午10:16:28
 */
class TcpClient {
	public void startConnect() {
		try{
			//创建客户端Socket，指定服务器的地址和端口
			Socket socket = new Socket("localhost", 8080);
			//获取输出流，向服务器端发送信息
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outputStream);
			writer.write("我是用户名" + Math.floor(Math.random() * 100));
			writer.flush();
			socket.shutdownOutput();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String data;
			while((data = bufferedReader.readLine()) != null)
				System.out.println("我是客户端，服务器发送信息为：" + data);
			writer.close();
			outputStream.close();
			socket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
/**
 * Socket通信之UDP服务器端
 */
class UdpServer {
	public void startListen() throws IOException {
		//创建服务器端指定端口的DatagramSocket
		DatagramSocket socket = new DatagramSocket(8080);
		//创建数据报用于接收客户端发送的数据
		byte[] buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		System.out.println("服务器端已经启动，等待客户端发送数据。");
		//接收客户端发送的数据
		socket.receive(packet);//此方法在接受到数据报之前会处于阻塞状态
		//读取数据
		System.out.println("接收数据：" + new String(buf,0,packet.getLength(),"UTF-8"));
		
		//向客户端发送响应信息
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		byte[] response = "欢迎您".getBytes("UTF-8");
		//创建数据报，包含响应的数据信息
		DatagramPacket datagramPacket = new DatagramPacket(response, response.length, address, port);
		//响应客户端
		socket.send(datagramPacket);
		socket.close();
	}
}
/**
 * Socket通信之UDP客户端
 * @ClassName: UdpClient 
 * @author 陈苗 
 * @date 2016年4月15日 下午11:26:03
 */
class UdpClient {
	public void startConnect() {
		try{
			//定义服务器的地址、端口号和数据
			InetAddress address = InetAddress.getByName("localhost");
			byte[] data = "用户名：陈苗，密码：786078509".getBytes("UTF-8");
			//创建数据报，包含发送的数据信息
			DatagramPacket datagramPacket = new DatagramPacket(data, data.length, address, 8080);
			//创建DatagramSocket对象来发送数据
			DatagramSocket socket = new DatagramSocket();
			socket.send(datagramPacket);
			
			//接收来自服务器端的响应
			byte[] buf = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			System.out.println("客户端接收信息为：" + new String(buf,0,packet.getLength()));
			socket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
/**
 * Java Socket编程测试
 * @ClassName: InetAddressTest 
 * @author 陈苗 
 * @date 2016年4月15日 下午9:32:17
 */
public class JavaSocketTest {
	/**
	 * 测试InetAddress类的使用
	 * @throws UnknownHostException
	 */
	@Test
	public void testInetAddress() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		System.out.println("当前计算机名称：" + address.getHostName());
		System.out.println("当前主机IP地址：" + address.getHostAddress());
		System.out.println("字节数组形式的IP：" + Arrays.toString(address.getAddress()));
		System.out.println("InetAddress对象：" + address);
		
		InetAddress inetAddress = InetAddress.getByName("192.168.3.138");
		System.out.println("192.168.3.138对应的计算机名称：" + inetAddress.getHostName());
	}
	/**
	 * 测试URL类的使用
	 * @throws MalformedURLException
	 */
	@Test
	public void testURL() throws MalformedURLException {
		URL immoc = new URL("http://www.immoc.com");
		URL url = new URL(immoc,"index.html?username=陈苗#home");
		System.out.println("协议名称：" + url.getProtocol());
		System.out.println("主机名称：" + url.getHost());
		System.out.println("端口名称：" + url.getPort());	//如果未指定端口号，则使用默认的端口号，此时getPort方法的返回值为-1
		System.out.println("文件名称：" + url.getFile());
		System.out.println("相对路径：" + url.getRef());
		System.out.println("查询字符串：" + url.getQuery());
	}
	/**
	 * 通过URL来获取来自网络的信息
	 * @throws IOException
	 */
	@Test
	public void testCatchInetConentByUrl() throws IOException {
		URL url = new URL("http://www.baidu.com");
		InputStream inputStream = url.openStream();//获取URL对象所表示的资源输入字节流
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));//将字节输入流转化为带缓冲的字符输入流
		String data;
		while((data = reader.readLine()) != null) 
			System.out.println(data);
		reader.close();
		inputStream.close();
	}
	/**
	 * NOTE:
	 * 	（1）基于TCP协议实现网络通信的类：客户端为Socket类，服务器端为ServerSocket类；
	 * 	（2）基于UDP协议实现网络通信的类：DatagramPacket表示数据报包，DatagramSocket进行端到端通信的类；
	 */
	@Test
	public void testTcpSocketServer() throws IOException {
		new TcpServer().startListen();
	}
	@Test
	public void testTcpSocketClient() {
		new TcpClient().startConnect();
	}
	@Test
	public void testUdpSocketServer() throws IOException {
		new UdpServer().startListen();
	}
	@Test
	public void testUdpSocketClient() {
		new UdpClient().startConnect();
	}
}
