package cn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：螺旋矩阵输出
 * 创建作者：陈苗
 * 创建时间：2016/11/27 20:19
 */
public class SpiralMatrixTest {
    /**
     * 螺旋遍历外部调用函数
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null)
            return null;
        List<Integer> result = new ArrayList<Integer>();
        if (matrix.length == 0)
            return result;
        int rows = matrix.length, colums = matrix[0].length;
        int begin_row = 0, end_row = rows - 1;
        int begin_col = 0, end_col = colums - 1;
        while (true) {
            for (int i = begin_col; i <= end_col; i++) {
                result.add(matrix[begin_row][i]);
            }
            if (++begin_row > end_row)
                break;/*如果只剩下一行，走到最后一个元素就不需要向下走*/
            for (int i = begin_row; i <= end_row; i++) {
                result.add(matrix[i][end_col]);
            }
            if (--end_col < begin_col)
                break;/*入股只剩下一列，走到最后一个元素就不需要向左走*/
            for (int i = end_col; i >= begin_col; i--) {
                result.add(matrix[end_row][i]);
            }
            if (--end_row < begin_row)
                break;/*由下自上走到起始行，则不需要再走*/
            for (int i = end_row; i >= begin_row; i--) {
                result.add(matrix[i][begin_col]);
            }
            if (++begin_col > end_col)
                break;/*由左向右走到起始列，则不需要再走*/
        }
        return result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[][] data = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println(Arrays.toString(new SpiralMatrixTest().spiralOrder(data).toArray()));
    }
}
