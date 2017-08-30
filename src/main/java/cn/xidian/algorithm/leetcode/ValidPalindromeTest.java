package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：回文字符串判断
 * 创建作者：陈苗
 * 创建时间：2017/8/30 10:37
 */
public class ValidPalindromeTest {
    /**
     * 只考虑文字和数字的回文字符串判断
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s == null)
            return false;
        if ("".equals(s.trim()))
            return true;
        String strs = get(s.toLowerCase());
        if (strs.length() == 0)
            return true;
        int left = 0, right = strs.length() - 1;
        while (strs.charAt(left) == strs.charAt(right)) {
            if (left == right || (right - left == 1))
                return true;
            ++left;
            --right;
        }
        return false;
    }

    private String get(String s) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
                sb.append(c);
        }
        return sb.toString();
    }
}
