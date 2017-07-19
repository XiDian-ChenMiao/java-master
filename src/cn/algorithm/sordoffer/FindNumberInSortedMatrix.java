package cn.algorithm.sordoffer;

import java.text.MessageFormat;

/**
 * 文件描述：从相对有序的矩阵中查找元素（行递增，列递增）
 * 创建作者：陈苗
 * 创建时间：2016年5月31日 11:36
 */
public class FindNumberInSortedMatrix {
    private int[][] matrix;
    private int rows;
    private int columns;

    /**
     * 构造函数
     *
     * @param matrix  矩阵信息
     * @param rows    矩阵的行数
     * @param columns 矩阵的列数
     */
    public FindNumberInSortedMatrix(int[][] matrix, int rows, int columns) {
        this.matrix = matrix;
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * 查找数字的算法
     *
     * @param number 待查找数字
     * @return 查找结果，若找到则返回true；否则，返回false
     */
    public boolean findNumber(int number) {
        boolean isFound = false;
        if (matrix.length != 0 && rows > 0 && columns > 0) {
            int row = 0;//起始查找的行数
            int column = columns - 1;//起始查找的列数
            while (row < rows && column >= 0) {
                if (matrix[row][column] == number) {
                    isFound = true;
                    System.out.println(MessageFormat.format("number位置在：第{0}行，第{1}列。", row + 1, column + 1));
                    break;
                } else if (matrix[row][column] > number) {
                    --column;
                } else {
                    ++row;
                }
            }
        }
        return isFound;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        FindNumberInSortedMatrix findNumberInSortedMatrix = new FindNumberInSortedMatrix(matrix, 4, 4);
        boolean result = findNumberInSortedMatrix.findNumber(7);
        System.out.println((result == true) ? "找到待查找元素" : "未找到元素");
    }
}
