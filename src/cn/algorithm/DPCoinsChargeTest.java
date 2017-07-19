package cn.algorithm;

import java.util.Arrays;

/**
 * 动态规划之硬币找零问题
 * @ClassName: DPCoinsChargeTest 
 * @author 陈苗 
 * @date 2016年5月6日 下午3:49:31
 */
public class DPCoinsChargeTest {

	private int[] coinValues;//保存每一种硬币的币值的数组
	private int valueKinds;//币值不同的硬币种类的数量
	private int money;//需要找零的币值
	private int[] coinsUsed;//保存面值为i的纸币找零所需的最小硬币数
	/**
	 * 构造函数
	 * @param _coinValues
	 * @param _valueKinds
	 */
	public DPCoinsChargeTest(int[] _coinValues,int _valueKinds) {
		coinValues = Arrays.copyOf(_coinValues, _coinValues.length);
		valueKinds = _valueKinds;
	}
	/**
	 * 设置找零金额
	 * @param _money
	 * @return
	 * @throws Exception
	 */
	public DPCoinsChargeTest setMoney(int _money) throws Exception {
		if(_money < 0) throw new Exception("找零金额不能小于0");
		this.money = _money;
		this.coinsUsed = new int[_money + 1];
		return this;
	}
	/**
	 * 根据需要找零的钱数找到需要的最小硬币数
	 * @return
	 */
	public int needMiniumCoins() {
		coinsUsed[0] = 0;
		if(this.money == 0)
			return 0;
		else{
			//对每一块钱都找零，即保存子问题的解以备用
			for(int cents = 1;cents <= this.money;cents++) {
				int minCoins = cents;//当用最小币值的硬币找零时，所需硬币数量最多
				for(int kind = 0;kind < valueKinds;kind++) {
					if(coinValues[kind] <= cents) {
						int temp = coinsUsed[cents - coinValues[kind]] + 1;
						if(temp < minCoins)
							minCoins = temp;
					}
				}
				coinsUsed[cents] = minCoins;
			}
			return coinsUsed[this.money];
		}
	}
	/**
	 * 主函数
	 */
	public static void main(String[] args) {
		int[] _coinValues = {25,21,10,5,1};
		DPCoinsChargeTest coinsCharge = null;
		int money = 50;
		try {
			coinsCharge = new DPCoinsChargeTest(_coinValues, _coinValues.length).setMoney(money);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("找零" + money + "需要硬币" + coinsCharge.needMiniumCoins() + "枚。");
	}
}
