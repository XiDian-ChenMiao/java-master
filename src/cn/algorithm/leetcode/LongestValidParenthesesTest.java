package cn.algorithm.leetcode;

import java.util.Stack;

/**
 * 文件描述：最长括号匹配
 * 创建作者：陈苗
 * 创建时间：2016/10/23 17:24
 */
public class LongestValidParenthesesTest {
    /**
     * 最长括号匹配外部调用函数
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1)
            return 0;
        char[] data = s.toCharArray();
        boolean[] is_matched = new boolean[data.length];
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '(')
                stack.push(i);
            else if (data[i] == ')' && !stack.empty()) {
                is_matched[i] = true;
                is_matched[stack.pop()] = true;
            }
        }
        int max_len = 0, cur_len = 0;
        for (int i = 0; i < s.length(); i++) {
            if (is_matched[i])
                ++cur_len;
            else
                cur_len = 0;
            max_len = Math.max(max_len, cur_len);
        }
        return max_len;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String data = "((())(()))";
        System.out.println(new LongestValidParenthesesTest().longestValidParentheses(data));
    }
}
