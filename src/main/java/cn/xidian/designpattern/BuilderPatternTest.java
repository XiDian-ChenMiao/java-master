package cn.xidian.designpattern;

import java.util.Date;

/**
 * 项目名称：DailyJavaTest
 * 类名称：Product
 * 类描述：产品类
 * 创建时间：2016年2月5日 上午9:39:59
 * 创建人： 陈苗
 */
class Product {
	@SuppressWarnings("unused")
	private String proName;//产品名称
	@SuppressWarnings("unused")
	private String proModel;//产品型号
	@SuppressWarnings("unused")
	private Date proDate;//产品生产日期
	@SuppressWarnings("unused")
	private String producer;//生产者
	@SuppressWarnings("unused")
	private int proPrice;//产品定价
	/**
	 * 私有化构造器
	 * @param proName 必须参数产品名称
	 * @param proModel 必须参数产品型号
	 */
	private Product(String proName, String proModel) {
		this.proName = proName;
		this.proModel = proModel;
	}
	/**
	 * 通过产品建造者构造产品
	 * @param builder
	 */
	private Product(ProductBuilder builder){
		proName = builder.proName;
		proModel = builder.proModel;
		proDate = builder.proDate;
		producer = builder.producer;
		proPrice = builder.proPrice;
	}
	/**
	 * 项目名称：DailyJavaTest
	 * 类名称：ProductBuilder
	 * 类描述：产品建造者类
	 * 创建时间：2016年2月5日 上午9:48:59
	 * 创建人： 陈苗
	 */
	public static class ProductBuilder {
		//必选参数
		private String proName = "产品-";//产品名称
		private String proModel = "型号-";//产品型号
		//可选参数
		private Date proDate = new Date();//产品生产日期
		private String producer = "生产者-";//生产者
		private int proPrice = 1000;//产品定价
		/**
		 * 建造者构造器
		 * @param proName 必须参数产品名称
		 * @param proModel 必须参数产品型号
		 */
		public ProductBuilder(String proName,String proModel){
			this.proName = proName;
			this.proModel = proModel;
		}
		public ProductBuilder proDate(Date proDate){
			this.proDate = proDate;
			return this;
		}
		public ProductBuilder producer(String producer){
			this.producer = producer;
			return this;
		}
		public ProductBuilder proPrice(int proPrice){
			this.proPrice = proPrice;
			return this;
		}
		/**
		 * 建造方法
		 * @return 产品对象
		 */
		public Product build(){
			return new Product(this);
		}
	}
}
/**
 * 项目名称：DailyJavaTest
 * 类名称：BuilderPatternTest
 * 类描述：建造者模式客户端测试类
 * 创建时间：2016年2月5日 上午10:07:45
 * 创建人： 陈苗
 */
public class BuilderPatternTest {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Product product = new Product.ProductBuilder("生命一号", "SX-001").proDate(new Date()).proPrice(1200).build();
	}
}