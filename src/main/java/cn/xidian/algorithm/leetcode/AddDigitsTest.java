package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: AddDigitsTest
 * @description: 258.各位相加
 * @date 2019-05-19 21:32
 */
public class AddDigitsTest {
    public int addDigits(int num) {
        String numStr = String.valueOf(num);
        while (numStr.length() > 1) {
            char[] chars = numStr.toCharArray();
            int sum = 0;
            for (int i = 0; i < chars.length; i++) {
                sum += (chars[i] - '0');
            }
            numStr = String.valueOf(sum);
        }
        return Integer.valueOf(numStr);
    }

    public static void main(String[] args) {
        System.out.println(new AddDigitsTest().addDigits(38));
    }
}
