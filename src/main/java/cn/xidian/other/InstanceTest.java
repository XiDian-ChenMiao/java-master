package cn.xidian.other;
class Base {}
class Derived extends Base {}
/**
 * 测试instanceof与Class的等价性
 * @ClassName: InstanceTest 
 * @author 陈苗 
 * @date 2016年3月24日 下午9:24:42
 */
public class InstanceTest {
	private static void test(Object obj){
		System.out.println("对象的类型为：" + obj.getClass());
		System.out.println("对象是否为Base类的实例：" + (obj instanceof Base));
		System.out.println("对象是否为Derived类的实例：" + (obj instanceof Derived));
		System.out.println("Base.isInstance(obj):" + Base.class.isInstance(obj));
		System.out.println("Derived.isInstance(obj):" + Derived.class.isInstance(obj));
		System.out.println("obj.getClass() == Base.class:" + (obj.getClass() == Base.class));
		System.out.println("obj.getClass() == Derived.class:" + (obj.getClass() == Derived.class));
		System.out.println("obj.getClass().equals(Base.class):" + obj.getClass().equals(Base.class));
		System.out.println("obj.getClass().equals(Derived.class):" + obj.getClass().equals(Derived.class));
	}
	public static void main(String[] args) {
		InstanceTest.test(new Base());
		InstanceTest.test(new Derived());
	}
}
