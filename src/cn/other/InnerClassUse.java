package cn.other;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：InnerClassUse
 * 类描述：内部类的使用测试
 * 创建时间：2015年8月27日 下午4:50:12
 * 创建人： 陈苗
 */
public class InnerClassUse {
	private String name;
	/**
	 * 
	 * 项目名称：DailyJavaTest
	 * 类名称：InnerClass
	 * 类描述：自定义内部类
	 * 创建时间：2015年8月27日 下午4:51:31
	 * 创建人： 陈苗
	 */
	class InnerClass{
		public String identity;
		//内部类的构造器
		public InnerClass(){
			System.out.println("内部类构造器.");
			//内部类可对外部类的私有属性赋值，但是外部类不能直接访问内部类的属性
			name = "陈苗";
		}
	}
	//外部类的构造器
	public InnerClassUse(){
		@SuppressWarnings("unused")
		InnerClass inner = new InnerClass();
		System.out.println("外部类构造器.");
		System.out.println("内部类对外部类的属性赋值之后，name属性为：" + name);
	}
	//主函数
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		InnerClassUse use = new InnerClassUse();
	}
}
