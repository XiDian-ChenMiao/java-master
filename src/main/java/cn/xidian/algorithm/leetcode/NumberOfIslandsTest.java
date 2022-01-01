package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: NumberOfIslandsTest
 * @description: Leetcode.200 Number of Islands
 * @date 8/14/21 10:38 PM
 */
public class NumberOfIslandsTest {

    /**
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    cnt += 1;
                }
            }
        }
        return cnt;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (!inArea(grid, r, c)) {
            /* 如果访问已经越界，则直接返回 */
            return;
        }
        if (grid[r][c] != '1') {
            return;
        }
        grid[r][c] = '2';/*防止重复搜索*/
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    private boolean inArea(char[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }
}
