package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：获取编码方式
 * 创建作者：陈苗
 * 创建时间：2017/5/1 10:47
 */
 public class DecodeWaysTest {
    /**
     *
     * @param s
     * @return
     */
     public int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;
        if (s.charAt(0) == '0')
            return 0;
        int[] number = new int[s.length() + 1];
        number[0] = 1;
        number[1] = 1;
        int temp;
        for (int i = 2; i <= s.length(); ++i) {
            temp = Integer.parseInt(s.substring(i - 1, i));
            if (temp != 0)
                number[i] = number[i - 1];
            if (s.charAt(i - 2) != '0') {
                temp = Integer.parseInt(s.substring(i - 2, i));
                if (temp > 0 && temp <= 26)
                    number[i] += number[i - 2];
            }
        }
        return number[s.length()];
     }
 } 