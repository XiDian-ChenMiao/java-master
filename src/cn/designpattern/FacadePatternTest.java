package cn.designpattern;
class A {
	public void doSomethingA(){
		System.out.println("类A的业务方法.");
	}
}
class B {
	public void doSomethingB(){
		System.out.println("类B的业务方法.");
	}
}
/*
 * 门面模式的优点：
 * 	a.减少系统的相互依赖：如果不使用门面模式，外界访问直接深入到子系统内部，相互之间是一种强耦合关系。
 * 	b.提高了灵活性：依赖减少，灵活性当然提高。不管子系统内部如何变化，只要不影响到门面对象，子系统可自由活动。
 * 	c.提高安全性：只有在门面上开通的逻辑，才能被客户端访问到。
 * 门面模式的缺点：不符合开闭原则，对修改关闭，对拓展开发。
 */
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Facade
 * 类描述：A类和B类的访问门面
 * 创建时间：2016年1月28日 上午10:45:20
 * 创建人： 陈苗
 */
class Facade {
	private A a = new A();
	private B b = new B();
	public void doSomethingA(){
		this.a.doSomethingA();
	}
	public void doSomethingB(){
		this.b.doSomethingB();
	}
}
/*
 * Facade门面角色：
 * 	客户端可以调用这个角色的方法。此角色知晓子系统的所有功能和责任。一般情况下，本角色会将所有从客户端发来的请求委派到
 * 响应的子系统中，也就是说该角色没有实际的业务逻辑，只是一个委托类。
 * 
 * SubSystem子系统角色
 * 	可以同时有一个或者多个子系统。每个子系统都不是一个单独的类，而是一个类的集合。子系统并不知道门面的存在。对于子系统而言，
 * 门面仅仅是另外一个客户端而已。
 */
/**
 * 门面模式定义：要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行。门面模式提供一个高层次的接口，使得子系统更易于使用。
 * 项目名称：DailyJavaTest
 * 类名称：FacadePatternTest
 * 类描述：门面模式（装饰模式）客户端测试类
 * 创建时间：2016年1月28日 上午10:23:14
 * 创建人： 陈苗
 */
public class FacadePatternTest {

	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.doSomethingA();
		facade.doSomethingB();
	}
}
