package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：非重复子序列判定
 * 创建作者：陈苗
 * 创建时间：2017/8/27 21:32
 */
public class DistinctSubsequencesTest {
    /**
     * 给定序列S和T，从S中找出子序列等于T的个数
     *
     * @param S
     * @param T
     * @return
     */
    public int numDistinct(String S, String T) {
        int[][] dp = new int[T.length() + 1][S.length() + 1];
        dp[0][0] = 1;/*S和T都是空串*/
        for (int i = 1; i <= T.length(); i++) {
            dp[i][0] = 0;/*S是空串，T不是空串，S没有子序列匹配*/
        }
        for (int j = 1; j <= S.length(); j++) {
            dp[0][j] = 1;/*S不是空串，T是空串，则S只有一种子序列匹配*/
        }
        for (int i = 1; i <= T.length(); i++) {
            for (int j = 1; j <= S.length(); j++) {
                dp[i][j] = dp[i][j - 1];
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[T.length()][S.length()];
    }

    /**
     * 最长公共子序列
     *
     * @param matrix
     * @param s
     * @param t
     */
    private void lcs(int[][] matrix, String s, String t) {
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                else {
                    if (matrix[i - 1][j] >= matrix[i][j - 1])
                        matrix[i][j] = matrix[i - 1][j];
                    else
                        matrix[i][j] = matrix[i][j - 1];
                }
            }
        }
    }

    /**
     * 提取最长公共子序列
     *
     * @param matrix
     * @param s
     * @param t
     * @return
     */
    private String getLCS(int[][] matrix, String s, String t) {

        int a = s.length() - 1, b = t.length() - 1, max = matrix[s.length()][t.length()];
        char[] strs = new char[max];
        while (a >= 0 && b >= 0) {
            if (s.charAt(a) == t.charAt(b)) {
                strs[--max] = s.charAt(a);
                a--;
                b--;
            } else {
                if (a == 0)
                    b--;
                else if (b == 0)
                    a--;
                else {
                    if (matrix[a - 1][b] < matrix[a][b - 1])
                        b--;
                    else
                        a--;
                }
            }
        }
        return new String(strs);
    }
}
