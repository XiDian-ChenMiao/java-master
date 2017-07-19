package cn.designpattern;
import org.junit.Test;
/**
 *
 * 项目名称：DailyJavaTest
 * 类名称：Stragedy
 * 类描述：策略接口，子类实现接口的中的策略方法
 * 创建时间：2015年8月26日 下午4:17:12
 * 创建人： 陈苗
 */
interface Stragedy{
	/**
	 * 接口中的策略服务方法
	 */
    void stragedyService();
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：FirstStragedy
 * 类描述：具体实现策略接口的策略
 * 创建时间：2015年8月26日 下午4:27:38
 * 创建人： 陈苗
 */
class FirstStragedy implements Stragedy{

	@Override
	public void stragedyService() {
		System.out.println("这是第一种具体的策略服务方法......");
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：SecondStragedy
 * 类描述：具体实现策略接口的策略
 * 创建时间：2015年8月26日 下午4:41:29
 * 创建人： 陈苗
 */
class SecondStragedy implements Stragedy{
	@Override
	public void stragedyService() {
		System.out.println("这是第二种具体的策略服务方法......");
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：StragedyPattern
 * 类描述：抽象的策略模式类
 * 创建时间：2015年8月26日 下午4:13:27
 * 创建人： 陈苗
 */
abstract class StragedyPattern {
	//抽象类持有策略的接口引用，回避具体的实现
	private Stragedy stragedy;
	/**
	 * 提供注入策略的接口
	 * @param stragedy 具体的策略对象
	 */
	public void setStragedy(Stragedy stragedy) {
		this.stragedy = stragedy;
	}
	/**
	 * 抽象类中的基本方法，允许子类重写此方法
	 */
	protected void basicMethod(){
		System.out.println("这是基本方法，");
	}
	/**
	 * 抽象的业务方法
	 */
	protected abstract void service();
	/**
	 * 策略服务方法调用此类对象所持有的策略引用，而具体根据策略引用的策略服务方法
	 */
	public void stragedyService(){
		System.out.println("策略开始执行......");
		stragedy.stragedyService();
		System.out.println("策略执行完成......");
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ConcreteStragedyPattern
 * 类描述：具体策略模式
 * 创建时间：2015年8月26日 下午4:29:39
 * 创建人： 陈苗
 */
class StragedyPatternCall extends StragedyPattern {

	@Override
	protected void service() {
		System.out.println("这是具体策略模式中的服务方法......");
	}
}
/**
 * 计算器加减法应用策略模式的例子
 * 项目名称：DailyJavaTest
 * 类名称：CalculatorEnum
 * 类描述：计算器策略枚举
 * 创建时间：2016年1月27日 上午11:01:03
 * 创建人： 陈苗
 */
enum CalculatorEnum {
	ADD("+"){
		@Override
		public int execute(int a, int b) {
			return a + b;
		}
	},
	SUB("-"){
		@Override
		public int execute(int a, int b) {
			return a - b;
		}
	};
	private String value = "";
	/**
	 * 定义成员值类型
	 * @param _value
	 */
	CalculatorEnum(String _value){
		this.value = _value;
	}
	/**
	 * 获取枚举成员的值
	 * @return
	 */
	public String getValue(){
		return this.value;
	}
	/**
	 * 声明一个抽象函数
	 * @param a
	 * @param b
	 * @return
	 */
	public abstract int execute(int a,int b);
}
/**
 * Note：
 *  一个具体策略只被使用一次时，通常使用匿名类来声明和实例化这个具体策略类。当一个具体策略设计是用来重复使用的时候，它的类通常就要被实现为
 *  私有的静态成员类，并通过公有的静态final成员域被导出，其类型为该策略接口。
 * 项目名称：DailyJavaTest
 * 类名称：StragedyPatternTest
 * 类描述：策略模式测试类
 * 创建时间：2015年8月26日 下午4:11:11
 * 创建人： 陈苗
 */
public class StragedyPatternTest {
	@Test
	public void stragedyPatternTest(){
		StragedyPatternCall spc = new StragedyPatternCall();
		Stragedy firstStragedy = new FirstStragedy();
		Stragedy secondStragedy = new SecondStragedy();
		
		spc.setStragedy(firstStragedy);
		spc.stragedyService();
		System.out.println("--------------------------------");
		spc.setStragedy(secondStragedy);
		spc.stragedyService();
		
		System.out.println("执行加法计算：" + CalculatorEnum.ADD.execute(10, 15));
	}
}
