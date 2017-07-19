package cn.algorithm.leetcode;

/**
 * 文件描述：给定m*n的矩阵，从左上角到右下角总共有多少种不同的情况路径
 * 创建作者：陈苗
 * 创建时间：2016/12/2 19:59
 */
public class UniquePathsTest {
    /**
     * 求路径总数，要求只能往下走或者往右走
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0)
            return 0;
        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            matrix[0][i] = 0;
        }
        for (int i = 0; i < m + 1; i++) {
            matrix[i][0] = 0;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == 1 && j == 1)
                    matrix[i][j] = 1;
                else
                    matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        return matrix[m][n];
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new UniquePathsTest().uniquePaths(2, 5));
    }
}
