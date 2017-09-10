package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：给定字符串，获取所有满足为回文的子串的最小切分数
 * 创建作者：陈苗
 * 创建时间：2017/9/10 21:59
 */
public class PalindromePartitioningIITest {
    public int minCut(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int[] count = new int[s.length() + 1];

        for (int i = s.length() - 1; i >= 0; i--) {
            count[i] = Integer.MAX_VALUE;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1] == 1)) {
                    dp[i][j] = 1;
                    count[i] = Math.min(1 + count[j + 1], count[i]);
                }
            }
        }

        return count[0] - 1;
    }
}
