package cn.other;
/**
 * 测试Java的内存回收机制
 * @ClassName: FinalizeEscapeGC 
 * @author 陈苗 
 * @date 2016年4月24日 下午8:33:06
 */
public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK = null;
	public void isAlive() {
		System.out.println("对象仍然存活，:)");
	}
	/**
	 * 任何一个对象的finalize方法只会被系统自动调用一次
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("对象的finalize方法被调用。");
		SAVE_HOOK = this;
	}
	/**
	 * 主函数调用
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscapeGC();
		//对象第一次成功拯救自己
		SAVE_HOOK = null;
		System.gc();
		//因为finalize方法优先级很低，所以暂停0.5秒等待它
		Thread.sleep(500);
		if(SAVE_HOOK != null)
			SAVE_HOOK.isAlive();
		else
			System.out.println("对象已经被清除，:(");
		
		SAVE_HOOK = null;
		System.gc();
		
		Thread.sleep(500);
		if(SAVE_HOOK != null)
			SAVE_HOOK.isAlive();
		else
			System.out.println("对象已经被清除，:(");
	}
}
