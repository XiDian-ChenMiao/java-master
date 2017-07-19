package cn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：生成括号对
 * 创建作者：陈苗
 * 创建时间：2016/10/18 20:33
 */
public class GenerateParenthesesTest {
    /**
     * 生成括号对的外部调用函数
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if (n == 0) {
            result.add("");
            return result;
        }
        if (n == 1) {
            result.add("()");
            return result;
        }
        generate(n, n, "", result);
        return result;
    }

    /**
     * 生成函数
     * @param left
     * @param right
     * @param s
     * @param result
     */
    private void generate(int left, int right, String s, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(s);
            return;
        }
        if (left > 0)
            generate(left - 1, right, s + "(", result);
        if (right > 0 && left < right)
            generate(left, right - 1, s + ")", result);
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        List<String> result = new GenerateParenthesesTest().generateParenthesis(4);
        for (String str : result)
            System.out.println(str);
    }
}
