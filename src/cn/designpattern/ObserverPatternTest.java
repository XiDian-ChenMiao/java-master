package cn.designpattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 项目名称：JavaSE
 * 类名称：IObserver
 * 类描述：观察者接口
 * 创建时间：2015年8月24日 下午5:03:13
 * 创建人： 陈苗
 */
interface IObserver {
    /**
     * 当目标对象发送消息时，供目标对象调用实现观察者信息更新的方法
     * @param subject 目标对象的引用
     */
    public void update(AbstractSubject subject);
    /**
     * 设置提示信息接口方法
     * @param notifyMsg 提示信息
     */
    public void setNotifyMsg(Object notifyMsg);
    /**
     * 得到提示信息接口方法
     * @return 返回提示信息
     */
    public Object getNotifyMsg();
}
/**
 * 类名称：AbstractSubject
 * 类描述：观察者模式中定义的抽象目标类
 * 创建时间：2015年8月24日 下午4:59:46
 * 创建人： 陈苗
 */
abstract class AbstractSubject {
    //抽象目标的订阅者列表
    public List<IObserver> observers = new ArrayList<IObserver>();
    /**
     * 添加订阅者的方法
     * @param observer 观察者对象
     */
    public void attach(IObserver observer){
        observers.add(observer);
    }
    /**
     * 删除订阅者的方法
     * @param observer 观察者对象
     */
    public void detach(IObserver observer){
        observers.remove(observer);
    }
    /**
     * 通知观察者的抽象方法，需要子类根据自己情况设置通知
     */
    protected abstract void notifyObservers();
}
/**
 *
 * 项目名称：JavaSE
 * 类名称：ConcreteObserver
 * 类描述：具体的观察者接口的实现类
 * 创建时间：2015年8月24日 下午5:28:11
 * 创建人： 陈苗
 */
class ConcreteObserver implements IObserver {
    private Object notifyMsg;
    @Override
    public void update(AbstractSubject subject) {
        //将传递过来的抽象目标对象强制转为具体的目标的对象
        ConcreteSubject sub = (ConcreteSubject) subject;
        System.out.println("订阅者消息：" + this.getNotifyMsg().toString() + ",目标发送信息为：" + sub.getSendMsg().toString());
    }

    @Override
    public void setNotifyMsg(Object notifyMsg) {
        this.notifyMsg = notifyMsg;
    }

    @Override
    public Object getNotifyMsg() {
        return notifyMsg;
    }
}
/**
 * 类名称：ConcreteSubject
 * 类描述：具体的目标类
 * 创建时间：2015年8月24日 下午5:16:42
 * 创建人： 陈苗
 */
class ConcreteSubject extends AbstractSubject {
    //目标向观察者提供发送的订阅信息
    private Object sendMsg;
    public Object getSendMsg() {
        return sendMsg;
    }
    public void setSendMsg(Object sendMsg) {
        this.sendMsg = sendMsg;
    }
    //重写抽象父类的给观察者通知的信息，子类可以根据自己需要来设置是否将订阅信息全部推送给所有的观察者
    @Override
    protected void notifyObservers() {
        for (IObserver observer : observers) {
            //此处添加对订阅者的筛选条件
            observer.update(this);
        }
    }
}
/**
 * 类名称：ClientMain
 * 类描述：设计模式的客户端测试类
 * 创建时间：2015年8月24日 下午5:33:56
 * 创建人： 陈苗
 */
public class ObserverPatternTest {
	//观察者模式的测试方法
	@Test 
	public void observerPatternTest(){
		//定义一个目标对象
		ConcreteSubject subject = new ConcreteSubject();
		//目标对象设置要发送的数据信息
		subject.setSendMsg("热烈庆祝中国人民抗日战争暨反法西斯战争胜利70周年！");
		
		//定义多个观察者
		ConcreteObserver observer_1 = new ConcreteObserver();
		ConcreteObserver observer_2 = new ConcreteObserver();
		//设置多个观察者自己需要的提示消息
		observer_1.setNotifyMsg("我是学生代表");
		observer_2.setNotifyMsg("我是工人阶级代表");
		
		//将观察者添加到目标的订阅者列表中
		subject.attach(observer_1);
		subject.attach(observer_2);
		
		//目标将推送消息发送给相关的订阅者
		subject.notifyObservers();
	}
}
