package cn.xidian.algorithm.leetcode;

import java.util.Stack;

/**
 * 文件描述：计算逆波兰数
 * 创建作者：陈苗
 * 创建时间：2017/9/12 10:42
 */
public class EvaluateReversePolishNotationTest {
    public int evalRPN(String[] tokens) {
        if (tokens == null)
            return 0;
        if (tokens.length == 1) {
            if ("+".equals(tokens[0]) || "-".equals(tokens[0]) || "*".equals(tokens[0]) || "/".equals(tokens[0]))
                return 0;
            return Integer.valueOf(tokens[0]);
        }

        Stack<Integer> stack = new Stack<>();
        int front, back;
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+":
                    back = stack.pop();
                    front = stack.pop();
                    stack.push(front + back);
                    break;
                case "-":
                    back = stack.pop();
                    front = stack.pop();
                    stack.push(front - back);
                    break;
                case "*":
                    back = stack.pop();
                    front = stack.pop();
                    stack.push(front * back);
                    break;
                case "/":
                    back = stack.pop();
                    front = stack.pop();
                    if (back == 0)
                        throw new ArithmeticException("除数为0");
                    stack.push(front / back);
                    break;
                default:
                    stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(new EvaluateReversePolishNotationTest().evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }
}
