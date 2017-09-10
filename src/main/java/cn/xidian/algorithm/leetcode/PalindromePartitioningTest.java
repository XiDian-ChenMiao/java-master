package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 文件描述：给定字符串，获取所有满足为回文的子串
 * 创建作者：陈苗
 * 创建时间：2017/9/10 21:13
 */
public class PalindromePartitioningTest {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0)
            return result;
        List<String> temp = new ArrayList<>();
        int length = s.length();
        int[][] pp = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                int k = 0;
                for (; k < (j - i + 1) / 2; k++) {
                    if (s.charAt(i + k) != s.charAt(j - k))
                        break;
                }
                if (k == (j - i + 1) / 2)
                    pp[i][j] = 1;
            }
        }
        dfs(0, s, pp, temp, result);
        return result;
    }

    /**
     * 通过深搜思想来判断子串的回文性质
     * @param start
     * @param s
     * @param pp
     * @param temp
     * @param result
     */
    private void dfs(int start, String s, int[][] pp, List<String> temp, List<List<String>> result) {
        if (start == s.length()) {
            List<String> t = new ArrayList<>(temp);
            Collections.reverse(t);
            result.add(t);
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (pp[start][i] == 1) {
                temp.add(0, s.substring(start, i + 1));
                dfs(i + 1, s, pp, temp, result);
                temp.remove(0);
            }
        }
    }
}
