package cn.algorithm.leetcode;

/**
 * 文件描述：最小编辑距离测试（只能有插入，删除和替换单个字符的可能）
 * 利用动态规划的思想，设D[i,j]表示字符串分别取A串和B串的前i和j个字符时的最小编辑距离，则其计算可分为如下三种情况：
 * （1）D[i,j] = D[i-1,j-1] + （A[i] == B[j] ? 0 : 1）：替换操作
 * （2）D[i,j] = D[i-1,j]，给串A删除A[i]：删除操作
 * （3）D[i,j] = D[i,j-1]，给串A增加B[j]：插入操作
 * 创建作者：陈苗
 * 创建时间：2016/12/26 15:45
 */
public class MinimumEditDistanceTest {
    /**
     * 最小编辑距离外部调用方法
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null)
            return 0;
        int m = word1.length(), n = word2.length();
        int[][] distance = new int[m + 1][n + 1];
        distance[0][0] = 0;
        for (int i = 1; i < m + 1; i++) {
            distance[i][0] = i;
        }
        for (int i = 1; i < n + 1; i++) {
            distance[0][i] = i;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int insertion = distance[i][j - 1] + 1;
                int deletion = distance[i - 1][j] + 1;
                int replace = distance[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
                distance[i][j] = Math.min(replace, Math.min(insertion, deletion));
            }
        }
        return distance[m][n];
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new MinimumEditDistanceTest().minDistance("bcd", "abce"));
    }
}
