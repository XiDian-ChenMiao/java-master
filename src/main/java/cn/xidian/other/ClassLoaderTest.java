package cn.xidian.other;

import cn.xidian.classtype.Student;
import org.junit.Test;

class Parent {
	public static int parent = 1;
	static {
		System.out.println("这是Parent类的静态代码段。");
	}
}
class Child extends Parent{
	public static int child = 1;
	static {
		System.out.println("这是Parent类的静态代码段。");
	}
}
/**
 * 测试Java虚拟机中类加载的过程
 * @ClassName: ClassLoaderTest 
 * @author 陈苗 
 * @date 2016年4月14日 上午10:46:18
 */
public class ClassLoaderTest {
	public static int classLoader = 1;
	static {
		System.out.println("ClassLoaderTest类中的静态字段classLoader等于" + classLoader);
		System.out.println("这是ClassLoaderTest的静态代码段。");
	}
	/**
	 * 当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化
	 */
	@Test
	public void testExtends() {
		System.out.println("Parent类中的静态字段parent等于" + Child.parent);
	}
	/**
	 * 获取类加载器
	 */
	@Test
	public void testClassLoader() {
		@SuppressWarnings("rawtypes")
		Class clazz = new Student().getClass();
		ClassLoader classLoader = clazz.getClassLoader();
		System.out.println(classLoader);
		System.out.println(classLoader.getParent());
		System.out.println(classLoader.getParent().getParent());
	}
}
