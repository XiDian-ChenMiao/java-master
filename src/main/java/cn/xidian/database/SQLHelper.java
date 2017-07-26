package cn.xidian.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：SQLHelper
 * 类描述：数据库访问工具类
 * 创建时间：2015年8月31日 上午10:38:08
 * 创建人： 陈苗
 */
public class SQLHelper {
	private static ResultSet rs = null;//返回的结果集对象
	private static Connection conn = null;//返回的数据库连接对象
	private static Statement stmt = null;//返回的会话对象
	/**
	 * 执行数据库查询的函数
	 * @param sql 需要查询的SQL语言
	 * @return 返回查找到的数据集
	 * @throws Exception
	 */
	public static ResultSet executeQuery(String sql) throws Exception{
		try{
			conn = ConnectionFactory.getConnectionByMysql();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		}catch(SQLException e){
			throw new Exception("执行SQL语句出错：" + sql);
		}
		return rs;
	}
	/**
	 * 执行数据库更新的函数
	 * @param sql 需要更新的SQL语句
	 * @throws Exception
	 */
	public static void executeUpdate(String sql) throws Exception{
		try{
			conn = ConnectionFactory.getConnectionByMysql();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}catch(SQLException e){
			throw new Exception("执行SQL语句出错：" + sql);
		}finally{
			DbClose.close(stmt, conn);
		}
	}
}
