package cn.xidian.other;

import cn.xidian.classtype.Student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * 项目名称：JavaSE
 * Note：在执行序列化操作时，如果父类实现了序列化接口，那么子类也是实现了序列化接口，并且当序列化子类对象时，会自动递归调用
 * 		 构造方法；但是当反序列化时，如果之前的父类没有实现序列化接口而子类实现了序列化接口，那么父类的构造方法会在反序列化
 * 		 操作时被执行。
 * 创建时间：2015年8月23日 下午5:18:55
 * 创建人： 陈苗
 */
@SuppressWarnings("serial")
class GrandFather implements Serializable{
	public String name;
	public int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GrandFather() {
		System.out.println("GrandFather Constructor...");
	}
}
@SuppressWarnings("serial")
class Father extends GrandFather{
	public Father() {
		System.out.println("Father Constructor...");
	}
}
@SuppressWarnings("serial")
class Son extends Father{
	public transient int grilFriends;
	public int getGrilFriends() {
		return grilFriends;
	}
	public void setGrilFriends(int grilFriends) {
		this.grilFriends = grilFriends;
	}
	public Son() {
		System.out.println("Son Constructor...");
	}
	public String toString() {
		return "[" + this.name + " " + this.age + " " + this.grilFriends + "]";
	}
	//对于transient修饰的属性进行自定义序列化操作而不需要JVM默认的序列化方式
    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException{
        s.defaultWriteObject();
        s.writeInt(grilFriends);
    }
    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.grilFriends = s.readInt();
    }
}
//测试序列化实现的类
public class SerializationTest{
	private static String serialPath = "C:\\Users\\Administrator\\Desktop\\SerializeTest.txt";
	//序列化的方法
	public static void serialize(Object obj) throws Exception{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(serialPath));
		out.writeObject(obj);
		out.flush();
		out.close();
	}
	//反序列化的方法
	public static Object deserialize() throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(serialPath));
		Object obj = in.readObject();
		in.close();
		return obj;
	}
	public static void main(String[] args) throws Exception{
		Student stu = new Student(13111270, "ChenMiao", 'M');
		SerializationTest.serialize(stu);
		Student deserialStu = (Student)SerializationTest.deserialize();
		System.out.println(deserialStu);
		
		Son son = new Son();
		son.setAge(23);
		son.setName("ChenMiao");
		son.setGrilFriends(1);
		SerializationTest.serialize(son);
		Son deserialSon = (Son)SerializationTest.deserialize();
		System.out.println(deserialSon);
	}
}
