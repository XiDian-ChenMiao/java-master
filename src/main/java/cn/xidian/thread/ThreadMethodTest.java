package cn.xidian.thread;

import org.junit.Test;
/**
 * 使用notify方法的线程类
 * NOTE:
 * 		当在对象上调用wait()方法时，执行该代码的线程立即放弃它在对象上的锁。
 * 		然而调用notify()时，并不意味着这时线程会放弃其锁。如果线程仍然在完成同步代码，则线程在移出之前不会放弃锁。
 * 		因此，只要调用notify()并不意味着这时该锁变得可用。
 * @ClassName: NotifyThread 
 * @author 陈苗 
 * @date 2016年4月11日 下午8:52:41
 */
class NotifyThread extends Thread {
	private int total;
	public int getTotal(){
		return total;
	}
	@Override
	public void run() {
		synchronized (this) {
			for(int i = 1;i <= 100;++i)
				total += i;
		}
		this.notify();//完成计算，唤醒在此对象监视器上等待的单个线程
	}
}
/**
 * 测试线程中使用notify方法和notifyAll方法
 * @ClassName: ThreadNotifyTest 
 * @author 陈苗 
 * @date 2016年4月11日 下午8:42:46
 */
public class ThreadMethodTest {
    private static class CounterThread extends Thread {
        private static Object lock = new Object();//充当对象锁使用
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "启动：");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println(this.getName() + "被唤醒");
                }
                for (int i = 0; i <= 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "-NO-" + i);
                }
            }
        }
    }
	/**
	 * 测试notify方法
	 */
	@Test
	public void testNotifyMethod() {
		NotifyThread thread = new NotifyThread();
		thread.start();
		synchronized (thread) {
			try{
				System.out.println("等待线程中的计算任务完成！");
				thread.wait();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.println("计算后得到的值为：" + thread.getTotal());
		}
	}
	/**
	 * 测试静态yield方法
	 * 线程的让步含义是使当前运行着的线程让出CPU资源，线程状态回到可运行状态，功能是暂停当前正在运行的线程对象，并执行其他线程。
	 */
	@Test
	public void testYieldMethod() {
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0;i < 3;i++)
					System.out.println("线程A第" + i + "次运行。");
			}
		});
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0;i < 3;i++){
					System.out.println("线程B第" + i + "次运行。");
					Thread.yield();
				}
			}
		});
		threadA.start();
		threadB.start();
	}
	/**
	 * 测试join方法
	 * 线程合并的含义就是将几个并行线程合并为一个单线程执行，应用场景是当一个线程必须等待另一个线程执行完毕时才能执行。
	 */
	@Test
	public void testJoinMethod() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 1;i <= 5;++i)
					System.out.println("线程C第" + i + "次运行。");
			}
		});
		thread.start();
		for(int i = 1;i <= 5;i++){
			System.out.println("主线程第" + i + "次运行。");
			if(i > 2)
			try{
				thread.join();//thread线程合并到主线程中，主线程停止执行过程，转而执行thread线程，知道thread执行完成
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * 测试守护线程
	 * 如果当前虚拟机中运行的线程全是守护线程时，虚拟机退出。即前台线程是指定完成的，而JRE不管后台线程是否仍然在进行。
	 */
	@Test
	public void testDaemonMethod() {
		Thread threadD = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0;i < 3;i++)
					System.out.println("线程D第" + i + "次运行。");
			}
		});
		Thread threadE = new Thread(new Runnable() {
			@Override
			public void run() {
				for(long i = 0;i < 9999999L;i++){
					System.out.println("线程E第" + i + "次运行。");
				}
			}
		});
		threadD.start();
		threadE.setDaemon(true);//将此线程设置为守护线程
		threadE.start();
	}

    /**
     * 测试中断方法
     */
    @Test
    public void testInterruptMethod() {
        CounterThread t1 = new CounterThread();
        CounterThread t2 = new CounterThread();

        t1.start();
        t2.start();
        try {
            Thread.sleep(2000);
            t1.interrupt();//测试线程过2秒之后强制唤醒t1线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取目前线程所属线程群组名
     */
    @Test
    public void getThreadGroup() {
        System.out.println(Thread.currentThread().getThreadGroup().getName());
    }
}
