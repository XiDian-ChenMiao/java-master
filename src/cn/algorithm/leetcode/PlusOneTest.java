package cn.algorithm.leetcode;

import java.util.Arrays;

/**
 * 文件描述：用数组模拟数字加一
 * 创建作者：陈苗
 * 创建时间：2016/12/4 19:26
 */
public class PlusOneTest {
    /**
     * 外部调用函数
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0)
            return new int[0];
        int length = digits.length;
        if (digits[length - 1] != 9) {
            digits[length - 1] += 1;
            return digits;
        } else {
            int count = 1, cur;
            int index = length - 1;
            while (count == 1 && index >= 0) {
                cur = (digits[index] + count) / 10;
                digits[index] = (digits[index] + count) % 10;
                index--;
                count = cur;
            }
            if (count == 1) {
                int[] result = new int[length + 1];
                result[0] = 1;
                return result;
            } else {
                return digits;
            }
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {9};
        System.out.println(Arrays.toString(new PlusOneTest().plusOne(data)));
    }
}
