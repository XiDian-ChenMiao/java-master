package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：字符串进行乘法运算的算法
 * 创建作者：陈苗
 * 创建时间：2016/11/22 21:04
 */
public class MultiplyStringsTest {
    /**
     * 利用字符串进行乘法运算
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0)
            return "";
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0')
            return "0";
        StringBuilder sb1 = (new StringBuilder(num1)).reverse();
        StringBuilder sb2 = (new StringBuilder(num2)).reverse();
        int[] digits = new int[num1.length() + num2.length()];
        for(int i = 0; i < num1.length(); i++){
            for(int j = 0; j < num2.length(); j++){
                digits[i + j] += (sb1.charAt(i) - '0') * (sb2.charAt(j) - '0');
            }
        }

        int carry = 0;
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < digits.length; i++){
            int digit = carry + digits[i];
            ret.append(digit % 10);
            carry = digit / 10;
        }
        if(carry > 0){
            ret.append(carry);
        }
        if(ret.charAt(ret.length() - 1) == '0')
            ret.deleteCharAt(ret.length() - 1);
        return ret.reverse().toString();
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new MultiplyStringsTest().multiply("1235", "2"));
    }
}
