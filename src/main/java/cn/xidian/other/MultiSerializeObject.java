package cn.xidian.other;

import cn.xidian.classtype.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MultiSerializeObject extends ObjectOutputStream{
	/**
	 * 将一个对象串行化进文件中
	 * @param obj 要串行化的对象
	 * @param filePath 保存的文件地址
	 */
	public static void newObjectWriter(Object obj,String filePath){
		File file = new File(filePath);
		ObjectOutputStream out = null;
		FileOutputStream fout = null;
		try{
			fout = new FileOutputStream(file, true);
			if(file.length() < 1)
				out = new ObjectOutputStream(fout);
			else
				out = new MultiSerializeObject(out);
			out.writeObject(obj);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(fout != null)
					fout.close();
				if(out != null)
					out.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * 从指定的文件地址中读出一个对象
	 * @param filePath 文件地址
	 * @return 读出的对象
	 * @throws Exception
	 */
	public static Object newObjectReader(String filePath) throws Exception{
		FileInputStream fin = null;
		ObjectInputStream in = null;
		Object obj = null;
		try{
			fin = new FileInputStream(filePath);
			in = new ObjectInputStream(fin);
			obj = in.readObject();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(fin != null)
					fin.close();
				if(in != null)
					in.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return obj;
	}
	/**
	 * 已知文件中存在的对象的数量，读出对象的列表
	 */
	public static List<Object> newObjectReaderAll(String filePath,int size) throws Exception{
		FileInputStream fin = null;
		ObjectInputStream in = null;
		List<Object> list = null;
		try{
			fin = new FileInputStream(filePath);
			in = new ObjectInputStream(fin);
			list = new ArrayList<Object>();
			for(int i = 0;i < size;i++){
				list.add(in.readObject());
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(fin != null)
					fin.close();
				if(in != null)
					in.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}
	/**
	 * 向指定文件目录中串行化一个对象数组
	 * @param obj
	 * @param filePath
	 */
	public static void newObjectWriterAll(Object[] obj,String filePath){
		File file = new File(filePath);
		ObjectOutputStream out = null;
		FileOutputStream fout = null;
		try{
			fout = new FileOutputStream(file, true);
			if(file.length() < 1)
				out = new ObjectOutputStream(fout);
			else
				out = new MultiSerializeObject(out);
			for(int i = 0;i < obj.length;i++)
				out.writeObject(obj[i]);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(fout != null)
					fout.close();
				if(out != null)
					out.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * 无参的构造函数
	 * @throws IOException
	 * @throws SecurityException
	 */
	protected MultiSerializeObject() throws IOException, SecurityException {
		super();
	}
	/**
	 * 以输出流为参数的有参构造函数
	 * @param out
	 * @throws IOException
	 * @throws SecurityException
	 */
	protected MultiSerializeObject(OutputStream out) throws IOException, SecurityException {
		super(out);
	}
	protected void writeStreamHeader() throws IOException {
		return;
	}
	/**
	 * 主函数
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		String filePath = "C:/SerializeTest.txt";
		File file = new File(filePath);
		if(file.exists())
			file.delete();
		Object[] obj = {new Student(13111270, "Chen Miao", 'M'),new Student(13111271,"Chen Jianghui",'M')};
		MultiSerializeObject.newObjectWriterAll(obj, filePath);
		List<Object> students = MultiSerializeObject.newObjectReaderAll(filePath, 2);
		for (Object object : students) {
			System.out.println((Student)object);
		}
	}
}
