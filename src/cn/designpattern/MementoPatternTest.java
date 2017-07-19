package cn.designpattern;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Originator
 * 类描述：发起人角色
 * 创建时间：2016年1月28日 上午11:25:55
 * 创建人： 陈苗
 */
class Originator {
	private String state = "";

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 创建一个备忘录
	 * @return
	 */
	public Memento createMemento(){
		return new Memento(this.state);
	}
	/**
	 * 恢复一个备忘录
	 * @param memento 备忘录对象
	 */
	public void restoreMemento(Memento memento){
		this.setState(memento.getState());
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Memento
 * 类描述：备忘录
 * 创建时间：2016年1月28日 上午11:28:38
 * 创建人： 陈苗
 */
class Memento {
	private String state;
	public Memento(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：MementoCareTaker
 * 类描述：备忘录的管理者
 * 创建时间：2016年1月28日 上午11:30:50
 * 创建人： 陈苗
 */
class MementoCareTaker {
	private Memento memento;

	public Memento getMemento() {
		return memento;
	}

	public void setMemento(Memento memento) {
		this.memento = memento;
	}
}
/*
 * 备忘录模式的使用场景：
 * 	a.需要保存和恢复数据的相关状态场景；
 * 	b.提供一个可回滚的操作；
 * 	c.需要监控的副本场景中；
 * 	d.数据库连接的事务管理就是用的备忘录模式；
 * 备忘录模式的注意事项：
 * 	a.备忘录的生命期；
 * 	b.备忘录的性能；
 */
/**
 * 备忘录模式定义：在不破坏封装性的前提下，捕获一个对象的内部状态，并在改对象之外保存这个状态。这样以后就可将该对象恢复到原先保存的状态。
 * 项目名称：DailyJavaTest
 * 类名称：MementoPatternTest
 * 类描述：备忘录模式客户端测试类
 * 创建时间：2016年1月28日 上午11:24:03
 * 创建人： 陈苗
 */
public class MementoPatternTest {
	public static void main(String[] args) {
		Originator originator = new Originator();
		originator.setState("现在心情很好。");
		System.out.println("恢复备忘录前，状态为：" + originator.getState());
		MementoCareTaker mementoCareTaker = new MementoCareTaker();
		//将此刻状态的备忘录添加到备忘录管理器对象中
		mementoCareTaker.setMemento(originator.createMemento());
		originator.setState("现在心情很糟。");
		System.out.println(originator.getState());
		//从备忘录管理器中恢复之前的状态备忘录
		originator.restoreMemento(mementoCareTaker.getMemento());
		System.out.println("恢复备忘录后，状态为：" + originator.getState());
	}
}
