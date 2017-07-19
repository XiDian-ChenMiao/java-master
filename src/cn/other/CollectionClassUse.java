package cn.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 集合类的札记：
 * Java contains two containers:
 * 		1.Collection
 * 			I.List: stores the sorted and repeatable elements.
 * 				a.ArrayList 适合于类似数组的操作，比如查找
 * 				b.LinkedList 适用于类似于链表的操作，如快速删除
 * 				c.Vector 线程安全
 * 			II.Set: stores the unsorted and unrepeatable elements.
 * 				a.HashSet	
 * 		2.Map
 * 			a.HashMap 线程不安全
 * 			b.HashTable 线程安全
 */
public class CollectionClassUse {
	/**
	 * List的使用测试，内容可重复
	 */
	public static void listUse(){
		System.out.println("-----------------使用ArrayList的List类测试--------------------");
		List<String> arrList = new ArrayList<String>();
		arrList.add("CM");
		arrList.add("AH");
		arrList.add("陈思琪");
		//占用第1个位置，后面的自动向后移动
		arrList.add(0, "陈苗");
		arrList.add("AH");
		for(int i = 0;i < arrList.size();i++)
			System.out.println(arrList.get(i));
		System.out.println("-----------------使用LinkedList的List类测试--------------------");
		List<String> linkList = new LinkedList<String>();
		linkList.add("AH");
		linkList.add("CM");
		linkList.add("CM");
		for (String string : linkList) {
			System.out.println(string);
		}
	}
	/**
	 * Set的使用测试，不会将所存储的元素进行排序并且内容不会重复
	 */
	public static void setUse(){
		Set<String> set = new HashSet<String>();
		set.add("琪琪");
		set.add("陈苗");
		set.add("琪琪");
		set.add("陈苗");
		Iterator<String> itor = set.iterator();
		while(itor.hasNext()){
			System.out.println(itor.next());
		}
	}
	/**
	 * Map的使用测试
	 */
	public static void mapUse(){
		Map<Integer,String> map = new HashMap<Integer, String>();
		map.put(1, "中国");
		map.put(1, "韩国");//如果键相同，那么新插入的数据将会替换原来的数据
		map.put(2, "美国");
		map.put(3, "俄国");
		map.put(4, "日本");
		System.out.println("HashMap中的EntrySet个数为：" + map.size());
		Iterator<?> keys = map.keySet().iterator();
		Iterator<?> itor = map.entrySet().iterator();
		Integer temp;
		//第一种遍历方法
		while(keys.hasNext()){
			temp = (Integer)keys.next();
			System.out.println("键:" + temp.intValue() + ",值:" + map.get(temp));
		}
		//第二种遍历方法
		while(itor.hasNext()){
			@SuppressWarnings("unchecked")
			Map.Entry<Integer, String> entry = (Map.Entry<Integer,String>)itor.next();
			int key = entry.getKey().intValue();
			String value = entry.getValue();
			System.out.println("键:" + key + ",值:" + value);
		}
	}
	public static void main(String[] args) {
		System.out.println("----------------List使用测试------------------");
		CollectionClassUse.listUse();
		System.out.println("----------------Set使用测试------------------");
		CollectionClassUse.setUse();
		System.out.println("----------------Map使用测试------------------");
		CollectionClassUse.mapUse();
	}
}
