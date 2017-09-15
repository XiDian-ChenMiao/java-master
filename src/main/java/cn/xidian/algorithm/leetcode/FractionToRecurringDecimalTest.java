package cn.xidian.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件描述：分子分母问题
 * 创建作者：陈苗
 * 创建时间：2017/9/12 22:35
 */
public class FractionToRecurringDecimalTest {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";
        StringBuilder result = new StringBuilder("");
        if (numerator < 0 && denominator > 0 || (numerator > 0 && denominator < 0))
            result.append("-");
        long n = Math.abs(Long.parseLong("" + numerator)), d = Math.abs(Long.parseLong("" + denominator));
        result.append(n / d);
        long rest = n % d;
        if (rest == 0)
            return result.toString();
        else
            result.append(".");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        while (rest != 0) {
            if (map.containsKey(rest)) {
                result.insert(map.get(rest), "(");
                result.append(")");
                break;
            }
            map.put(rest, result.length());
            rest *= 10;
            result.append(rest / d);
            rest = rest % d;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new FractionToRecurringDecimalTest().fractionToDecimal(1, 21));
    }
}
