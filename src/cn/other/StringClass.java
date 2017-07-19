package cn.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.classtype.Student;
import org.junit.Test;

/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：StringClass
 * 类描述：关于字符串类的测试方法
 * 创建时间：2015年8月27日 下午5:45:30
 * 创建人： 陈苗
 */
public class StringClass {
	/**
	 * 简单String类的测试方法
	 */
	@Test
	public void stringTest(){
		//当两个字符串的值相同时，JVM会自动给下一个相同字符串引用以相同的地址
		String str_1 = "ChenMiao";
		String str_2 = "ChenMiao";
		String str_3 = new String("ChenMiao");
		if(str_1 == str_2 && str_1.equals(str_2)){
			System.out.println("str_1 == str_2，且str_1哈希码为：" + str_1.hashCode() + ",str_2哈希码为：" + str_2.hashCode());
			if(str_2 == str_3)
				System.out.println("str_2 == str_3");
		}
		else
			System.out.println("str_1 != str_2");
		
		Student stu = new Student();
		stu.setName("陈苗");
		String username = stu.getName();
		System.out.println(username.hashCode());
		
		username = "陈苗SB";
		System.out.println(username.hashCode());
	}
	/**
	 * StringBuilder类的测试方法，其不是线程安全的
	 */
	@Test
	public void stringBuilderTest(){
		StringBuilder builder = new StringBuilder();
		builder.append("ChenMiao");
		builder.append(" MiaoChen");
		System.out.println("字符串：" + builder);
		System.out.println("容量:" + builder.capacity());
		System.out.println("长度:" + builder.length());
	}
	/**
	 * StringBuffer类的测试方法，其为线程安全的类
	 */
	@Test
	public void stringBufferTest(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ChenMiao");
		buffer.append(" MiaoChen");
		System.out.println("字符串：" + buffer);
		System.out.println("容量:" + buffer.capacity());
		System.out.println("长度:" + buffer.length());
	}
	/**
	 * 从输入设备读取字符流测试方法
	 */
	@Test
	public void scannerTest() {
		System.out.print("请输入：");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String content = reader.readLine();
			System.out.println("输入值为:" + content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试字符串的intern方法
	 * 存在于.class文件中的常量池，在运行期被JVM装载，并且可以扩充。String的intern方法就是扩充常量池的一个方法；
	 * 当一个String实例调用intern方法时，Java检查常量池中是否有仙童的Unicode的字符串常量，如果有，则返回其的引用，
	 * 如果没有，则在常量池中增加一个Unicode等于实例的字符串并返回它的引用。
	 */
	@Test
	public void testInternMethod(){
		String s1 = "ChenMiao";
		String s2 = new String("ChenMiao");
		String s3 = new String("ChenMiao");
		System.out.println(s1 == s2);
		s2.intern();//并没有将返回值赋给s2
		s3 = s3.intern();
		System.out.println(s1 == s2);
		System.out.println(s1 == s2.intern());
		System.out.println(s1 == s3);
	}
	@Test
	public void testHashCode(){
		String[] hellos = "Hello Hello".split(" ");
		System.out.println(hellos[0].hashCode());
		System.out.println(hellos[1].hashCode());
	}
}
