package cn.designpattern;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Component
 * 类描述：抽象构件
 * 创建时间：2016年1月27日 上午9:57:33
 * 创建人： 陈苗
 */
abstract class Component {
	public abstract void operate();
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ConcreteComponent
 * 类描述：具体组件类
 * 创建时间：2016年1月27日 上午9:59:43
 * 创建人： 陈苗
 */
class ConcreteComponent extends Component {
	@Override
	public void operate() {
		System.out.println("这是具体组件类的操作方法.");
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Decorator
 * 类描述：抽象装饰器
 * 创建时间：2016年1月27日 上午10:01:52
 * 创建人： 陈苗
 */
abstract class Decorator extends Component {
	private Component component = null;
	/**
	 * 通过构造函数传递被修饰者
	 * @param component 被修饰的组件
	 */
	public Decorator(Component component) {
		this.component = component;
	}
	/**
	 * 委托给被修饰者去执行
	 */
	@Override
	public void operate() {
		this.component.operate();
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ConcreteDecorator1
 * 类描述：具体装饰器一
 * 创建时间：2016年1月27日 上午10:07:30
 * 创建人： 陈苗
 */
class ConcreteDecorator1 extends Decorator {

	public ConcreteDecorator1(Component component) {
		super(component);
	}
	/**
	 * 定义自己的修饰方法
	 */
	private void method(){
		System.out.println("这是装饰器一的自己修饰方法");
	}
	/**
	 * 重写父类的operate方法
	 */
	@Override
	public void operate() {
		this.method();
		super.operate();
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ConcreteDecorator2
 * 类描述：具体装饰器二
 * 创建时间：2016年1月27日 上午10:07:16
 * 创建人： 陈苗
 */
class ConcreteDecorator2 extends Decorator {

	public ConcreteDecorator2(Component component) {
		super(component);
	}
	/**
	 * 定义自己的修饰方法
	 */
	private void method(){
		System.out.println("这是装饰器二的自己修饰方法");
	}
	/**
	 * 重写父类的operate方法
	 */
	@Override
	public void operate() {
		super.operate();
		this.method();
	}
}
/*
 * Note：
 * 装饰模式的优点：
 * 	a.装饰类和被装饰类可以独立发展，而不会相互耦合。换句话说，Component类无须知道Decorator类，Decorator类是从外部来
 * 拓展Component类的功能，而Decorator也不用知道具体的构件；
 * 	b.装饰模式是继承关系的一个替代方案。我们看装饰类Decorator，不管装饰多少层，返回的对象还是Component，实现的还是is-a的关系；
 * 	c.装饰模式可以动态的扩展一个实现类的功能，装饰模式的定义就是如此。
 * 
 * 装饰模式的缺点：
 * 	对于装饰模式记住一点就够了：多层的装饰是比较复杂的。尽量减少装饰类的数量，以便降低系统的复杂度。
 * 
 * 装饰模式的使用场景：
 * 	a.需要扩展一个类的功能，或给一个类增加附加功能；
 * 	b.需要动态的给一个对象增加功能，这些功能可以再动态的撤销；
 * 	c.需要为一批的兄弟类进行改装或加装功能，当然是首选装饰模式。
 */
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：DecoraterPattern
 * 类描述：装饰器模式客户端测试类
 * 创建时间：2016年1月27日 上午10:07:45
 * 创建人： 陈苗
 */
public class DecoraterPatternTest {
	public static void main(String[] args) {
		Component component = new ConcreteComponent();
		component = new ConcreteDecorator2(new ConcreteDecorator1(component));
		component.operate();
	}
}
