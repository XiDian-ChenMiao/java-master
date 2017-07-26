package cn.xidian.other;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：CompareClass
 * 类描述：实现比较接口的比较类
 * 创建时间：2015年11月16日 下午9:26:33
 * 创建人： 陈苗
 */
class CompareClass implements Comparable<CompareClass> {

	private String name;
	
	public CompareClass(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(CompareClass o) {
		return this.getName().compareTo(o.getName());
	}
	
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ArrayMethodTest
 * 类描述：数组类的静态方法测试
 * 创建时间：2015年11月16日 下午9:05:27
 * 创建人： 陈苗
 */
public class ArrayMethodTest {

	@Test
	public void copy(){
		int[] i = new int[7];
		int[] j = new int[10];
		
		Arrays.fill(i, 8);
		Arrays.fill(j, 99);
		
		System.out.println("i = " + Arrays.toString(i));
		System.out.println("j = " + Arrays.toString(j));
		//如果复制对象数组，那么只是复制了对象的引用，而不是对象本身的拷贝——浅复制
		System.arraycopy(i, 0, j, 0, i.length);
		System.out.println("j = " + Arrays.toString(j));
	}
	@Test
	public void equals(){
		int[] i = new int[7];
		int[] j = new int[7];
		
		Arrays.fill(i, 8);
		Arrays.fill(j, 8);
		//数组相等的条件是元素个数必须相等，并且对应位置的元素也相等，这可以通过对每一个元素使用equals（）作比较
		System.out.println("i = j ? " + Arrays.equals(i, j));
		
		String[] m = new String[4];
		Arrays.fill(m, "DAQINZHIDI");
		System.out.println("m[0] = m[1] ? " + (m[0] == m[1]));
		String[] n = {new String("DAQINZHIDI"),new String("DAQINZHIDI"),new String("DAQINZHIDI"),new String("DAQINZHIDI")};
		System.out.println("m = n ? " + Arrays.equals(m, n));//只比较的是值
		System.out.println("m[0] = n[0] ? " + (m[0] == n[0]));
	}
	@Test
	public void compare(){
		CompareClass m = new CompareClass("DAQINZHIDI");
		CompareClass n = new CompareClass("daqinzhidi");
		System.out.println(m.compareTo(n) > 0 ? "m大于n" : "m小于n");
	}
	@Test
	public void binarySearch(){
		int[] i = {1,2,3,5,6};
		System.out.println("i[2]的位置索引为：" + Arrays.binarySearch(i, 2));
		//如果未找到元素，则此方法返回的是负值，表示若要保持数组的排序状态此目标应该插入的位置，计算公式为：-（插入点）- 1
		System.out.println("4的插入位置应该为：" + Math.abs(Arrays.binarySearch(i, 4) + 1));
	}
}
