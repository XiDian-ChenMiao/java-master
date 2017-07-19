package cn.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ConnectionFactory
 * 类描述：数据库工厂类
 * 创建时间：2015年8月25日 下午5:25:36
 * 创建人： 陈苗
 */
public class ConnectionFactory {
	private final static String MySQL_PROPERTIES = "cn/database/jdbc-mysql.properties";
	private static String DB_DRIVER;
	private static String DB_URL;
	private static String DB_NAME;
	private static String DB_USERNAME;
	private static String DB_PASSWORD;
	private static Connection conn;
	private static Properties p = new Properties();
	private ConnectionFactory(){}
	
	/**
	 * 通过MySQL数据库得到数据库的连接
	 * @return
	 */
	public static Connection getConnectionByMysql(){
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(MySQL_PROPERTIES);
		return getConnection(input);
	}
	private static Connection getConnection(InputStream input){
		try{
			p.load(input);
			DB_DRIVER = p.getProperty("driver");
			DB_URL = p.getProperty("url");
			DB_NAME = p.getProperty("database");
			DB_USERNAME = p.getProperty("username");
			DB_PASSWORD = p.getProperty("password");
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL + DB_NAME,DB_USERNAME,DB_PASSWORD);
		}catch(Exception e){
			System.out.println("新建数据库的连接出错！");
		}finally{
			try {
				input.close();
			} catch (IOException e) {
				System.out.println("关闭文件输入流出错！");
			}
		}
		return conn;
	}
	public static void main(String[] args) {
		Connection conn = ConnectionFactory.getConnectionByMysql();
		if(conn != null)
			System.out.println("新建MySQL数据库连接成功！");
		else
			System.out.println("新建MySQL数据库连接失败！");
	}
}
