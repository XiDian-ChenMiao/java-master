package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：给一个非负矩阵，求从左上位置到右下位置总和最小的路径
 * 创建作者：陈苗
 * 创建时间：2016/12/2 21:16
 */
public class MinimumPathSumTest {
    /**
     * 求最小路径和的函数
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    result[i][j] = grid[i][j];
                else {
                    if (i == 0)
                        result[i][j] = grid[i][j] + result[0][j - 1];
                    else if (j == 0)
                        result[i][j] = grid[i][j] + result[i - 1][0];
                    else
                        result[i][j] = grid[i][j] + Math.min(result[i - 1][j], result[i][j - 1]);
                }
            }
        }
        return result[m - 1][n - 1];
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[][] data = {
                {1, 2, 5},
                {1, 1, 4},
                {0, 1, 5}
        };
        System.out.println(new MinimumPathSumTest().minPathSum(data));
    }
}
