package cn.designpattern;

import org.junit.Test;

/**
 * 
 * 项目名称：JavaSE
 * 类名称：HungrySingleTon
 * 类描述：单例模式示例 ----- 饿汉模式，这种方法生成的单例是线程安全的
 * 创建时间：2015年8月25日 下午3:35:03
 * 创建人： 陈苗
 */
class HungrySingleTonTest {
	private final static HungrySingleTonTest instance = new HungrySingleTonTest();
	private HungrySingleTonTest(){}
	public static HungrySingleTonTest getInstance(){
		return instance;
	}
}
/**
 * 
 * 项目名称：JavaSE
 * 类名称：LazySingleTon
 * 类描述：单例模式示例 ----- 懒汉模式，这种方法生成的单例不是线程安全的
 * 创建时间：2015年8月25日 下午3:40:55
 * 创建人： 陈苗
 */
class LazySingleTon {
	private static LazySingleTon instance = null;
	private LazySingleTon(){}
	public static LazySingleTon getInstance(){
		if(null == instance)
			instance = new LazySingleTon();
		return instance;
	}
}
/**
 * 
 * 项目名称：JavaSE
 * 类名称：SyncSingleTon
 * 类描述：将懒汉模式的单例添加同步，改变成为线程安全的单例
 * 创建时间：2015年8月25日 下午3:45:51
 * 创建人： 陈苗
 */
class SyncSingleTon {
	private static SyncSingleTon instance = null;
	private SyncSingleTon(){}
	public static SyncSingleTon getInstance(){
		synchronized (SyncSingleTon.class) {
			if(null == instance)
				instance = new SyncSingleTon();
			return instance;
		}
	}
}
/**
 * 
 * 项目名称：JavaSE
 * 类名称：ClientSingleTonTest
 * 类描述：单例模式的测试类
 * 创建时间：2015年8月25日 下午3:38:21
 * 创建人： 陈苗
 */
public class SingleTonPatternTest{
	@Test
	public void hungrySingleTonTest(){
		HungrySingleTonTest inst_1 = HungrySingleTonTest.getInstance();
		HungrySingleTonTest inst_2 = HungrySingleTonTest.getInstance();
		if(inst_1 == inst_2)
			System.out.println("inst_1 == inst_2");
		else
			System.out.println("inst_1 != inst_2");
	}
	@Test
	public void lazySingleTonTest(){
		LazySingleTon inst_1 = LazySingleTon.getInstance();
		LazySingleTon inst_2 = LazySingleTon.getInstance();
		if(inst_1 == inst_2)
			System.out.println("inst_1 == inst_2");
		else
			System.out.println("inst_1 != inst_2");
	}
	@Test
	public void syncSingleTonTest(){
		SyncSingleTon inst_1 = SyncSingleTon.getInstance();
		SyncSingleTon inst_2 = SyncSingleTon.getInstance();
		if(inst_1 == inst_2)
			System.out.println("inst_1 == inst_2");
		else
			System.out.println("inst_1 != inst_2");
	}
}
