package cn.xidian.designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 项目名称：DailyJavaTest
 * 类名称：Human
 * 类描述：人类接口
 * 创建时间：2015年8月28日 下午5:18:19
 * 创建人： 陈苗
 */
interface Human {
	public void useTools();
	public void useTools(String toolName);
}
/**
 * 项目名称：DailyJavaTest
 * 类名称：SuperHuman
 * 类描述：高级人员类
 * 创建时间：2015年8月28日 下午5:21:09
 * 创建人： 陈苗
 */
class SuperHuman implements Human{
	@Override
	public void useTools() {
		System.out.println("高等人类能够使用高级的工具为自己提供服务.");
	}

	@Override
	public void useTools(String toolName) {
		System.out.println("高等人类能够使用高级的工具" + toolName + "为自己提供服务.");
	}
}
/**
 * 项目名称：DailyJavaTest
 * 类名称：EatingHandler
 * 类描述：用膳的调用类
 * 创建时间：2015年8月28日 下午5:22:39
 * 创建人： 陈苗
 */
class EatingHandler implements InvocationHandler {
	private Object target;
	@Override
	public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
		System.out.println("在使用工具之前，吃饱饭是重要的前提.");
		//在这一处的方法名称不确定，需要根据主程序中的方法调用以及传入的参数自行判断调用函数
		method.invoke(target,args);
		return null;
	}
	//有以被代理对象为参数的构造器
	public EatingHandler(Object target) {
		this.target = target;
	}
}
/**
 * 项目名称：DailyJavaTest
 * 类名称：ProxyPatternTest
 * 类描述：代理模式测试类，其意义为其他对象提供一种代理以控制对这个对象的访问。
 * 创建时间：2015年8月28日 下午5:16:49
 * 创建人： 陈苗
 */
public class ProxyPatternTest {
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		SuperHuman human = new SuperHuman();
		InvocationHandler handler = new EatingHandler(human);
		Human h = (Human)Proxy.newProxyInstance(human.getClass().getClassLoader(), human.getClass().getInterfaces(), handler);
		h.useTools();
		h.useTools("计算机");
	}
}
