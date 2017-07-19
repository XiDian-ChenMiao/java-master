package cn.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：MyCallable
 * 类描述：实现Callable返回结果的自定义调用类
 * 创建时间：2015年11月30日 下午3:43:55
 * 创建人： 陈苗
 */
class MyCallable implements Callable<Object>{

	private String taskNum;
	
	public MyCallable(String taskNum) {
		this.taskNum = taskNum;
	}

	@Override
	public Object call() throws Exception {
		System.out.println(">>>" + taskNum + "任务启动");
		Date start = new Date();
		Thread.sleep(2000);
		Date end = new Date();
		long time = end.getTime() - start.getTime();
		System.out.println(">>>" + taskNum + "任务终止");
		return taskNum + "任务返回运行结果，当前任务时间为" + time + "毫秒.";
	}
	
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Ticket
 * 类描述：卖票类
 * 创建时间：2015年11月30日 下午4:23:25
 * 创建人： 陈苗
 */
class Ticket implements Runnable {

	private int ticket = 200;//票数
	@Override
	public void run() {
		while(true){
			//同步代码块，同步函数锁是this，静态同步函数锁是类变量
			synchronized (this) {
				try{
					Thread.sleep(500);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(ticket < 1)
				break;
			System.out.println(Thread.currentThread().getName() + "卖出第" + (ticket--) + "张票.");
		}
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ThreadTest
 * 类描述：带返回值的线程测试类
 * 创建时间：2015年11月30日 下午3:39:06
 * 创建人： 陈苗
 */
public class ThreadTest {
	
	//线程池和带返回值的线程测试
	public static void threadPoolTest() throws InterruptedException, ExecutionException {
		System.out.println("--------------程序开始执行------------");
		Date start = new Date();
		int taskSize = 5;//初始化任务数量
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);//初始化一个线程池
		List<Future<Object>> list = new ArrayList<Future<Object>>();//创建多个有返回值的任务
		for(int i = 0;i < taskSize;++i){
			Callable<Object> c = new MyCallable(i + " ");
			Future<Object> f = pool.submit(c);//执行任务并返回Future对象
			list.add(f);
		}
		pool.shutdown();//关闭线程池
		for(Future<Object> f : list){
			//如果Executor后台线程池还没有完成Callable的计算，这调用返回Future对象的get()方法，会阻塞直到计算完成。
			System.out.println(">>>" + f.get().toString());
		}
		Date end = new Date();
		System.out.println("--------------程序结束执行------------");
		System.out.println("程序运行时间为：" + (end.getTime() - start.getTime() + "毫秒."));
	}
	
	//同步代码块线程测试
	public static void syncCodeBlockThreadTest(){
		Ticket ticket = new Ticket();
		Thread t1 = new Thread(ticket, "线程-1");
		Thread t2 = new Thread(ticket, "线程-2");
		Thread t3 = new Thread(ticket, "线程-3");
		Thread t4 = new Thread(ticket, "线程-4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	public static void main(String[] args) {
		syncCodeBlockThreadTest();
	}
}
