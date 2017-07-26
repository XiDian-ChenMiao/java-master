package cn.xidian.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

class CreateContainer {
	/**
	 * 创建一个Map容器
	 * @return
	 */
	public static <K,V> Map<K,V> map(){
		return new HashMap<K, V>();
	}
	/**
	 * 创建一个ArrayList容器
	 * @return
	 */
	public static <T> List<T> list(){
		return new ArrayList<T>();
	}
	/**
	 * 创建一个LinkedList容器
	 * @return
	 */
	public static <T> LinkedList<T> linkedList(){
		return new LinkedList<T>();
	}
	/**
	 * 创建一个Set容器
	 * @return
	 */
	public static <T> Set<T> set(){
		return new HashSet<T>();
	}
	/**
	 * 创建一个Vector容器
	 * @return
	 */
	public static <T> Vector<T> vector(){
		return new Vector<T>();
	}
	/**
	 * 通过多参数构造ArrayList容器
	 * @param args
	 * @return
	 */
	public static <T> List<T> makeList(T ... args){
		List<T> result = new ArrayList<T>();
		for(T item : args)
			result.add(item);
		return result;
	}
}
/**
 * 泛型测试
 * @ClassName: GeneralizationTest 
 * @author 陈苗 
 * @date 2016年3月25日 下午8:55:06
 */
public class GeneralizationTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Map<String,List<String>> sls = CreateContainer.map();
		List<String> sl = CreateContainer.list();
		LinkedList<String> lsl = CreateContainer.linkedList();
		Set<String> ss = CreateContainer.set();
		Vector<String> vs = CreateContainer.vector();
		List<String> result = CreateContainer.makeList("陈苗","西安电子科技大学");
		System.out.println(result.toString());
	}
}
