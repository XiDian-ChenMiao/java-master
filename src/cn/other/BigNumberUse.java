package cn.other;

import java.math.BigDecimal;

public class BigNumberUse {
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal("0.052514545454");
		BigDecimal b = new BigDecimal("0.245831545745445454");
		System.out.println(a.add(b).toString());
		System.out.println("浮点值：" + a.floatValue());
		System.out.println("绝对值：" + a.abs().toString());
	}
}
