package cn.algorithm.leetcode;

/**
 * 文件描述：在指定矩阵中，如果出现元素0，则将该元素所在的行列都设置为0
 * 创建作者：陈苗
 * 创建时间：2016/12/29 21:17
 */
public class SetMatrixZeroesTest {
    /**
     * 外部调用函数
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        if (matrix == null)
            return;
        int rows = matrix.length, cols = matrix[0].length;
        boolean first_row_has_zero = false;
        boolean first_col_has_zero = false;
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 0) {
                first_row_has_zero = true;
                break;
            }
        }
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                first_col_has_zero = true;
                break;
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }
        if (first_col_has_zero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
        if (first_row_has_zero) {
            for (int i = 0; i < cols; i++) {
                matrix[0][i] = 0;
            }
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[][] data = {
                {1, 2, 0},
                {1, 2, 3},
                {1, 0, 3}
        };
        new SetMatrixZeroesTest().setZeroes(data);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
