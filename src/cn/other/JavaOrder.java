package cn.other;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：JavaOrder
 * 类描述：父子类构造函数的执行顺序测试
 * 创建时间：2015年8月27日 下午4:52:41
 * 创建人： 陈苗
 */
public class JavaOrder extends JavaOrderFather{
	static{
		System.out.println("JavaOrder类的静态代码段执行且只执行一次.");
	}
	{
		System.out.println("在JavaOrder类的构造方法之前.");
	}
	public JavaOrder(){
		System.out.println("JavaOrder的构造器方法执行.");
	}
	public void doSomething(){
		System.out.println("JavaOrder的内部函数方法执行.");
	}
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		JavaOrder son = new JavaOrder();
		son.doSomething();
		System.out.println("-----------------------------------");
		JavaOrderFather father = new JavaOrderFather();
		father.doSomething();
	}
}
