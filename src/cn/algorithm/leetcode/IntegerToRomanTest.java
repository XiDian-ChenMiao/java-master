package cn.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件描述：将数字转为罗马数字
 * 创建作者：陈苗
 * 创建时间：2016/10/17 10:14
 */
public class IntegerToRomanTest {
    /**
     * 将数字转为罗马字符串的外部调用函数
     * @param num 给定数字
     * @return 其对应的罗马数字
     */
    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        for (int i = 0; i < value.length;) {
            if (num >= value[i]) {
                stringBuilder.append(roman[i]);
                num -= value[i];
            } else {
                i++;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 罗马数字转阿拉伯数字
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
        int i, total, pre, cur;
        total = map.get(s.charAt(0));
        for (i = 1; i < s.length(); i++) {
            pre = map.get(s.charAt(i - 1));
            cur = map.get(s.charAt(i));
            if (cur <= pre) {
                total += cur;
            } else {
                total = total - pre * 2 + cur;
            }
        }
        return total;
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        IntegerToRomanTest test = new IntegerToRomanTest();
        System.out.printf("%d对应的罗马文为：%s\n", 3999, test.intToRoman(3999));
        System.out.printf("%s对应的阿拉伯数字为：%d\n", "MMMCMXCIX", test.romanToInt("MMMCMXCIX"));
    }
}
