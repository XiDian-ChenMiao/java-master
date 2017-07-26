package cn.xidian.designpattern;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Peppery
 * 类描述：辣椒接口
 * 创建时间：2015年11月14日 下午2:32:35
 * 创建人： 陈苗
 */
interface Peppery {
	void style();
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：PepperyStyle
 * 类描述：重辣风格
 * 创建时间：2015年11月14日 下午2:33:30
 * 创建人： 陈苗
 */
class PepperyStyle implements Peppery{

	@Override
	public void style() {
		System.out.println("这酸爽.");
	}
	
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：PlainPeppery
 * 类描述：无辣风格
 * 创建时间：2015年11月14日 下午2:34:12
 * 创建人： 陈苗
 */
class PlainPeppery implements Peppery {

	@Override
	public void style() {
		System.out.println("真养胃.");
	}
	
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：AbstractNoddles
 * 类描述：桥接吃辣风格的抽象面类
 * 创建时间：2015年11月14日 下午2:38:31
 * 创建人： 陈苗
 */
abstract class AbstractNoddles {
	protected Peppery style;
	public AbstractNoddles(Peppery style){
		this.style = style;
	}
	public abstract void eat(); 
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：PorkNoddles
 * 类描述：清淡面
 * 创建时间：2015年11月14日 下午2:43:11
 * 创建人： 陈苗
 */
class PorkNoddles extends AbstractNoddles {

	public PorkNoddles(Peppery style) {
		super(style);
	}

	@Override
	public void eat() {
		System.out.print("清淡的面条：");
		super.style.style();
	}
	
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：BeefNoddles
 * 类描述：牛肉面
 * 创建时间：2015年11月14日 下午2:44:17
 * 创建人： 陈苗
 */
class BeefNoddles extends AbstractNoddles {

	public BeefNoddles(Peppery style) {
		super(style);
	}

	@Override
	public void eat() {
		System.out.print("美味的牛肉面：");
		super.style.style();
	}
	
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：BridgePatternTest
 * 类描述：桥接模式
 * 创建时间：2015年11月14日 下午2:31:32
 * 创建人： 陈苗
 */
public class BridgePatternTest {
	public static void main(String[] args) {
		AbstractNoddles noddles  = new BeefNoddles(new PepperyStyle());
		noddles.eat();
        AbstractNoddles plainNoodles = new PorkNoddles(new PlainPeppery());
        plainNoodles.eat();
	}
}
