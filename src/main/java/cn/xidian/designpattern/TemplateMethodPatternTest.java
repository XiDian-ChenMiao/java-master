package cn.xidian.designpattern;

import org.junit.Test;

/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：TemplateMethod
 * 类描述：模板方法模式类
 * 创建时间：2015年8月26日 下午3:57:27
 * 创建人： 陈苗
 */
abstract class TemplateMethod{
	/**
	 * 不能被子类更改的业务操作方法
	 */
	protected final void service(){
		firstServiceOperation();
		secondServiceOpertaion();
		thirdServiceOperation();
	}
	/**
	 * 模板方法，第一步业务操作
	 */
	private void firstServiceOperation(){
		System.out.println("这是步骤一，模板方法。");
	}
	/**
	 * 第二步抽象模板方法，用于让子类实现与自己相关的操作
	 */
	protected abstract void secondServiceOpertaion();
	/**
	 * 模板方法，第三步业务操作
	 */
	private void thirdServiceOperation(){
		System.out.println("这是步骤三，模板方法。");
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ConcreteTemplateMethod
 * 类描述：具体实现模板方法的子类
 * 创建时间：2015年8月26日 下午4:05:09
 * 创建人： 陈苗
 */
class ConcreteTemplateMethod extends TemplateMethod{
	/**
	 * 覆写抽象模板方法二
	 */
	@Override
	protected void secondServiceOpertaion() {
		System.out.println("这是在子类中实现的模板方法二，抽象模板方法实现。");
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：TemplateMethodTest
 * 类描述：模板方法测试类
 * 创建时间：2015年8月26日 下午3:56:46
 * 创建人： 陈苗
 */
public class TemplateMethodPatternTest {
	@Test
	public void templateMethodTest(){
		ConcreteTemplateMethod tempMethod = new ConcreteTemplateMethod();
		tempMethod.service();
	}
}
