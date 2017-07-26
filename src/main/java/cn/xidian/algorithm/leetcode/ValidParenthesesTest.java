package cn.xidian.algorithm.leetcode;

import java.util.Stack;

/**
 * 文件描述：验证给定字符串中的括号是否成对出现
 * 创建作者：陈苗
 * 创建时间：2016/10/18 16:24
 */
public class ValidParenthesesTest {
    /**
     * 验证函数
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 1)
            return false;
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            if (c == ']' || c == '}' || c == ')') {
                stack.push(c);
                char a = '&', b = '&';
                if (!stack.isEmpty())
                    a = stack.pop();
                if (!stack.isEmpty())
                    b = stack.pop();
                if (a == ']' && b != '[')
                    return false;
                if (a == '}' && b != '{')
                    return false;
                if (a == ')' && b != '(')
                    return false;
            }
        }
        if (!stack.isEmpty())
            return false;
        return true;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String data = "([])()";
        System.out.println(new ValidParenthesesTest().isValid(data));
    }
}
