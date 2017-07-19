package cn.algorithm.leetcode;

/**
 * 文件描述：给定m*n的矩阵，在设置路障的情况下，计算从左上角到右下角总共有多少种不同的情况路径
 * 创建作者：陈苗
 * 创建时间：2016/12/2 20:51
 */
public class UniquePathsIITest {
    /**
     * 外部调用函数
     * @param obstacleGrid 路障矩阵
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0)
            return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        return uniquePaths(m, n, obstacleGrid);
    }

    /**
     * 获取路径函数
     * @param m
     * @param n
     * @param obstacleGrid
     * @return
     */
    public int uniquePaths(int m, int n, int[][] obstacleGrid) {
        if (m <= 0 || n <= 0 || obstacleGrid[0][0] == 1)
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
                else {
                    if (obstacleGrid[i - 1][j - 1] == 1)
                        matrix[i][j] = 0;
                    else
                        matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
                }
            }
        }
        return matrix[m][n];
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[][] obstacles = {
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}
        };

        System.out.println(new UniquePathsIITest().uniquePathsWithObstacles(obstacles));
    }
}
