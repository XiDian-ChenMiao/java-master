package cn.other;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 目录管理器类
 * @ClassName: DirManager 
 * @author 陈苗 
 * @date 2016年4月16日 下午4:59:39
 */
class DirManager {
	private Queue<File> queue = new LinkedList<File>();//存放文件夹的队列
	/**
	 * 构造函数
	 * @param copyFrom 源目录参数
	 */
	public DirManager(String copyFrom){
		queue.add(new File(copyFrom));
	}
	/**
	 * 向队列中添加目录
	 * @param dir
	 */
	synchronized public void add(File dir){
		queue.add(dir);
		System.out.println("队列中容量为：" + queue.size());
	}
	synchronized public File[] getFirstDirectory(){
		if(!queue.isEmpty()){
			File sourceDir = queue.poll();
			File[] getFiles = (new File(sourceDir.getAbsolutePath())).listFiles();
			return getFiles;
		}else{
			return null;
		}
	}
}
class Worker extends Thread {
	private DirManager dirManager;
	private String copyTo = null;
	public Worker(DirManager manager,String copyTo){
		this.dirManager = manager;
		this.copyTo = copyTo;
	}
	@Override
	public void run() {
		int tryCounter = 5;
		while(tryCounter > 0){
			File[] dir = dirManager.getFirstDirectory();
			if(dir != null){
				processDirectory(dir);
				tryCounter = 5;
			}else{
				try{
					Thread.sleep(1000);
				}catch(Exception e){
					e.printStackTrace();
				}
				--tryCounter;
			}
		}
	}
	/**
	 * 处理目录函数
	 * @param dir
	 */
	private void processDirectory(File[] dir){
		for(int i = 0;i < dir.length;++i){
			if(dir[i].isFile()){
				String toFile = copyTo + File.separator + dir[i].getName();
				try{
					copyFile(dir[i], new File(toFile));
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			if(dir[i].isDirectory()){
				dirManager.add(dir[i]);
			}
		}
	}
	/**
	 * 实际拷贝文件函数
	 * @param fromFile
	 * @param toFile
	 * @throws IOException
	 */
	private void copyFile(File fromFile,File toFile) throws IOException{
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fromFile));
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(toFile));
		byte[] buffer = new byte[20 * 1024 * 1024];
		int length;
		while((length = bufferedInputStream.read(buffer)) != -1){
			bufferedOutputStream.write(buffer, 0, length);
		}
		bufferedOutputStream.flush();//刷新缓冲区
		bufferedInputStream.close();
		bufferedOutputStream.close();
	}
}
/**
 * 通过多线程方法拷贝文件
 * @ClassName: CopyDirectoryByMultiThread 
 * @author 陈苗 
 * @date 2016年4月16日 下午4:55:29
 */
public class CopyDirectoryByMultiThread {
	public static void main(String[] args) {
		String copyFrom = "D:" + File.separator + "TEST1"; 
		System.out.println(copyFrom);
		String copyTo = "D:" + File.separator + "TEST2"; 
		System.out.println(copyTo);
		DirManager dirManager = new DirManager(copyFrom);
		for(int i = 0;i < 10;++i){
			Worker worker = new Worker(dirManager, copyTo);
			worker.start();
		}
	}
}
