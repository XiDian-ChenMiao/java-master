package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: Search2DMatrixTest
 * @description: 240.在有序矩阵中搜索目标值
 * @date 2019-05-19 22:19
 */
public class Search2DMatrixTest {

    /**
     * 从有序二维矩阵中查找目标值
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int curRow = row - 1, curCol = 0;
        while (curRow >= 0 && curCol < col) {
            if (matrix[curRow][curCol] == target) {
                return true;
            } else if (matrix[curRow][curCol] > target) {
                curRow -= 1;
            } else {
                curCol += 1;
            }
        }
        return false;
    }
}
