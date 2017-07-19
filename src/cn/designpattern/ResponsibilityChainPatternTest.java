package cn.designpattern;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：DiscountChain
 * 类描述：抽象的打折扣链
 * 创建时间：2015年8月28日 下午4:51:20
 * 创建人： 陈苗
 */
abstract class DiscountChain {
	protected DiscountChain discountHandler;
	/**
	 * 提供注入打折扣人员折扣的方法
	 * @param discountHandler 后继打折人员
	 */
	public void setDiscountHandler(DiscountChain discountHandler) {
		this.discountHandler = discountHandler;
	}
	/**
	 * 抽象的打折扣的标准
	 * @param discount 折扣标准
	 */
	protected abstract void discount(float discount); 
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Sales
 * 类描述：销售人员类
 * 创建时间：2015年8月28日 下午4:58:02
 * 创建人： 陈苗
 */
class Sales extends DiscountChain {
	@Override
	protected void discount(float discount) {
		if(discount <= 0.15)
			System.out.println("销售提供了" + discount * 100 + "%的打折额度.");
		else
			discountHandler.discount(discount);//交给后继来处理更大的打折额度
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Director
 * 类描述：主管人员类
 * 创建时间：2015年8月28日 下午5:01:32
 * 创建人： 陈苗
 */
class Director extends DiscountChain {
	@Override
	protected void discount(float discount) {
		if(discount <= 0.30)
			System.out.println("主管提供了" + discount * 100 + "%的打折额度.");
		else
			discountHandler.discount(discount);//交给后继来处理更大的打折额度
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Manager
 * 类描述：经理人员类
 * 创建时间：2015年8月28日 下午5:02:23
 * 创建人： 陈苗
 */
class Manager extends DiscountChain {
	@Override
	protected void discount(float discount) {
		if(discount <= 0.45)
			System.out.println("经理提供了" + discount * 100 + "%的打折额度.");
		else
			System.out.println("经理拒绝了" + discount * 100 + "%的打折额度.");
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Customer
 * 类描述：客户类
 * 创建时间：2015年8月28日 下午5:06:32
 * 创建人： 陈苗
 */
class Customer {
	private DiscountChain handler;
	public void setHandler(DiscountChain handler) {
		this.handler = handler;
	}
	/**
	 * 客户请求的打折额度
	 * @param discount
	 */
	public void requestDiscount(float discount){
		handler.discount(discount);
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ResponsibilityChainPatternTest
 * NOTE:
 *  （1）责任链模式的优点：非常显著的优点就是将请求和处理分开。请求者可以不用知道是谁处理的，处理者可以不用知道请求的全貌（例如在J2EE项目开发中，可以
 *  剥离出无状态的Bean由责任链处理），两者解耦，提高系统的灵活性；
 *  （2）责任链模式的缺点：一是性能问题，每个请求都是从链头遍历到链尾，特别是在链比较长的情况下，性能是一个非常大的问题；二是调试不方便，特别是联调比较长
 *  ，环节比较多的时候，由于采用了类似递归的方式，调试的时候逻辑可能比较复杂；
 *  注意事项：链表中节点数量需要控制，避免出现链表超长的情况，一般的做法是在Handler中设置一个最大的节点数量，在setNext方法中判断是否已经是超过其阈值，
 *  超过则不允许建立该链，避免无意识的破坏系统性能；
 * 类描述：责任链模式测试类
 * 创建时间：2015年8月28日 下午4:48:33
 * 创建人： 陈苗
 */
public class ResponsibilityChainPatternTest {
	/**
	 * 主函数测试
	 * @param args
	 */
	public static void main(String[] args) {
		Sales sale = new Sales();
		Director director = new Director();
		Manager manager = new Manager();
		sale.setDiscountHandler(director);
		director.setDiscountHandler(manager);
		Customer customer = new Customer();
		customer.setHandler(sale);
		customer.requestDiscount(0.25f);
	}
}
