package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：邮件戳组合
 * 创建作者：陈苗
 * 创建时间：2016/10/18 9:27
 */
public class LetterCombinationsTest {
    /**
     * 外部调用方法
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if (digits == null || digits.length() == 0)
            return result;
        String[] strings = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> chosen = new ArrayList<String>();
        char[] ds = digits.toCharArray();
        for (char c : ds) {
            chosen.add(strings[c - '0']);
        }
        return result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String param = "23";
        List<String> result = new LetterCombinationsTest().letterCombinations(param);
        for (String value : result)
            System.out.println(value + "\t");
        System.out.println('2' - '0');
    }
}
