package cn.algorithm.leetcode;

/**
 * 文件描述：最长回文子串
 * 创建作者：陈苗
 * 创建时间：2016/7/10 22:24
 */
public class LongPalindromicSubStringTest {
    /**
     * 通过Manacher算法获取最长回文子串的调用接口
     * @param s 给定的字符串
     * @return 最长回文子串
     */
    public String longestPalindrome(String s) {
        if (s == null)
            return null;
        if (s.length() == 1)
            return s;
        String newString = change(s);
        int length = newString.length();
        int[] radius = new int[length];/*用户保存新字符串中的每个字符的回文半径*/
        int right = -1,mid = -1;/*right表示已知的回文中，最右的边界的坐标;id表示已知的回文中，拥有最右边界的回文的中点坐标*/
        for (int i = 0; i < length; i++) {
            int r = 1;
            if (i <= right) {
                r = Math.min(radius[mid] - i + mid, radius[2 * mid - i]);
            }
            while (i - r >= 0 && i + r < length && newString.charAt(i - r) == newString.charAt(i + r)) {
                r++;
            }
            if (i + r - 1> right) {
                right = i + r - 1;
                mid = i;
            }
            radius[i] = r;
        }
        int max = 0,index = 0;
        for (int i = 0; i < radius.length; i++) {
            if (radius[i] > max) {
                max = radius[i];
                index = i;
            }
        }
        return newString.substring(index - max + 1,index + max - 1).replaceAll("#","");
    }

    /**
     * 改变原字符串
     * @param s 源字符串
     * @return 插入间隔信息的新字符串
     */
    private String change(String s) {
        StringBuilder builder = new StringBuilder();
        builder.append('#');
        int length = s.length();
        for (int i = 0; i < length; i++) {
            builder.append(s.charAt(i));
            builder.append('#');
        }
        return builder.toString();
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new LongPalindromicSubStringTest().longestPalindrome("a"));
    }
}
