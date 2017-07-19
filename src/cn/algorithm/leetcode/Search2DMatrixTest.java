package cn.algorithm.leetcode;

/**
 * 文件描述：在有序矩阵中查找
 * 创建作者：陈苗
 * 创建时间：2016/12/29 21:52
 */
public class Search2DMatrixTest {
    /**
     * 外部调用函数
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null)
            return false;
        int rows = matrix.length, cols = matrix[0].length;
        if (rows == 0 || cols == 0)
            return false;
        int start = 0, end = rows - 1, middle, row_index = 0;
        while (start <= end) {
            middle = (start + end) / 2;
            if (matrix[middle][cols - 1] == target)
                return true;
            else if (matrix[middle][cols - 1] > target) {
                if (matrix[middle][0] < target) {
                    row_index = middle;
                    break;
                }
                end = middle - 1;
            } else {
                if (middle + 1 < rows && matrix[middle + 1][cols - 1] > target) {
                    row_index = middle + 1;
                    break;
                }
                start = middle + 1;
            }
        }
        start = 0;
        end = cols - 1;
        while (start <= end) {
            middle = (start + end) / 2;
            if (matrix[row_index][middle] == target)
                return true;
            else if (matrix[row_index][middle] > target)
                end = middle - 1;
            else
                start = middle + 1;
        }
        return false;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[][] data = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        System.out.println(new Search2DMatrixTest().searchMatrix(data, 2));
    }
}
