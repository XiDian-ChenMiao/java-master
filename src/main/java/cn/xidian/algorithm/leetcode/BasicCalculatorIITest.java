package cn.xidian.algorithm.leetcode;

import java.util.Stack;

/**
 * 文件描述：简单计算器模拟
 * 创建作者：陈苗
 * 创建时间：2017/9/21 17:13
 */
public class BasicCalculatorIITest {
    public int calculate(String s) {
        int len;
        if (s == null || (len = s.length()) == 0)
            return 0;
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i)))
                num = num * 10 + (s.charAt(i) - '0');
            if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
                if (sign == '-')
                    stack.push(-num);
                if (sign == '+')
                    stack.push(num);
                if (sign == '*')
                    stack.push(stack.pop() * num);
                if (sign == '/')
                    stack.push(stack.pop() / num);
                sign = s.charAt(i);
                num = 0;
            }
        }
        int re = 0;
        for (int i : stack)
            re += i;
        return re;
    }

    public static void main(String[] args) {
        System.out.println(new BasicCalculatorIITest().calculate("7-5/2"));
    }
}
