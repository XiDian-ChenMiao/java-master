package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：求由01字符构成的二维矩阵中1所表示的正方形最大面积
 * 创建作者：陈苗
 * 创建时间：2017/9/20 15:41
 */
public class MaximalSquareTest {
    public int maximalSquare(char[][] a) {
        if (a.length == 0)
            return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1][j - 1] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result);
                }
            }
        }
        return result * result;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        System.out.println(new MaximalSquareTest().maximalSquare(matrix));
    }
}
