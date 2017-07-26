package cn.xidian.designpattern;

import cn.xidian.other.MD5;
import sun.misc.BASE64Encoder;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Command
 * 类描述：命令接口
 * 创建时间：2015年11月14日 下午1:48:05
 * 创建人： 陈苗
 */
interface Command {
	void process(String string);
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Base64Authority
 * 类描述：Base64加密类
 * 创建时间：2015年11月14日 下午1:58:00
 * 创建人： 陈苗
 */
class Base64Authority implements Command {
	@Override
	public void process(String string) {
		BASE64Encoder encoder = new BASE64Encoder();
		System.out.println("Base64加密：" + encoder.encode(string.getBytes()));
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：MD5Authority
 * 类描述：MD5加密类
 * 创建时间：2015年11月14日 下午1:58:32
 * 创建人： 陈苗
 */
class MD5Authority implements Command {

	@Override
	public void process(String string) {
		MD5 encoder = new MD5();
		System.out.println("MD5加密：" + encoder.getMD5ofStr(string));
	}
	
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：CommandPattern
 * 类描述：命令模式
 * 创建时间：2015年11月14日 下午1:46:55
 * 创建人： 陈苗
 */
public class CommandPatternTest {

	/**
	 * 加密函数
	 * @param string
	 * @param callback
	 */
	public void authority(String string,Command callback) {
		callback.process(string);
	}
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		CommandPatternTest pattern = new CommandPatternTest();
		pattern.authority("786078509", new Base64Authority());
		pattern.authority("786078509", new MD5Authority());
	}
}
