package cn.xidian.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Component
 * 类描述：抽象构件
 * 创建时间：2016年1月27日 上午11:15:29
 * 创建人： 陈苗
 */
abstract class AbsComponent {
	public void service() {
		System.out.println("这是抽象构件的业务方法");
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Composite
 * 类描述：树枝构件
 * 创建时间：2016年1月27日 上午11:16:48
 * 创建人： 陈苗
 */
class Composite extends AbsComponent {
	//构件容器
	private List<AbsComponent> components = new ArrayList<AbsComponent>();
	/**
	 * 增加构件的方法
	 * @param component
	 */
	public void add(AbsComponent component){
		this.components.add(component);
	}
	/**
	 * 移除构件的方法
	 * @param component
	 */
	public void remove(AbsComponent component){
		this.components.remove(component);
	}
	/**
	 * 获取分支下的所有叶子构件和树枝构件
	 * @return
	 */
	public List<AbsComponent> getChildren(){
		return this.components;
	}
}
/**
 * 项目名称：DailyJavaTest
 * 类名称：Leaf
 * 类描述：树叶构件
 * 创建时间：2016年1月27日 上午11:24:29
 * 创建人： 陈苗
 */
class Leaf extends AbsComponent {
	@Override
	public void service() {
		System.out.println("这是叶子节点的业务方法");
	}
}
/*
 * 组合模式的优点：
 * 	a.高层模块调用简单：一棵树形机构中的所有节点都是Component，局部和整体对调用者来说没有任何区别，也即是说，高层模块不必关心
 * 自己处理的是单个对象还是整个组合结构，简化了高层模块的代码；
 * 	b.节点自由增加：使用了组合模式后，如果想增加一个树枝节点和树叶节点来说很容易，只要找到它的父节点就成，非常容易扩展，符合开闭原则，维护有利。
 * 
 * 组合模式的缺点：树枝和树叶都是实现类，这在面向接口编程上是很不恰当的，与依赖倒置原则冲突，限制了接口的影响范围。
 */
/**
 * 项目名称：DailyJavaTest
 * 类名称：CompositePatternTest
 * 类描述：组合模式客户端测试类（安全模式）
 * 创建时间：2016年1月27日 上午11:14:24
 * 创建人： 陈苗
 */
public class CompositePatternTest {
	/**
	 * 建立递归遍历树
	 * @param root
	 */
	public static void display(Composite root){
		for (AbsComponent absComponent : root.getChildren()) {
			if(absComponent instanceof Leaf)
				absComponent.service();
			else
				display((Composite)absComponent);
		}
	}
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		//创建一个根节点
		Composite root = new Composite();
		root.service();
		
		//创建一个分支节点
		Composite branch = new Composite();
		//创建一个叶子节点
		Leaf leaf = new Leaf();
		
		root.add(branch);
		branch.add(leaf);
		
		display(root);
	}
}
