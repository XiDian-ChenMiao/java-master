package cn.algorithm.leetcode;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 文件描述：生成螺旋矩阵
 * 创建作者：陈苗
 * 创建时间：2016/11/29 19:19
 */
public class GenerateSpiralMatrixTest {
    /**
     * 生成螺旋矩阵的外部调用
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int startRow = 0, endRow = n - 1;
        int startCol = 0, endCol = n - 1;
        int[][] result = new int[n][n];
        int start = 1;
        while (true) {
            for (int i = startCol; i <= endCol; i++) {
                result[startRow][i] = start++;
            }
            if (++startRow > endRow)
                break;
            for (int i = startRow; i <= endRow; i++) {
                result[i][endCol] = start++;
            }
            if (--endCol < startCol)
                break;
            for (int i = endCol; i >= startCol; i--) {
                result[endRow][i] = start++;
            }
            if (--endRow < startRow)
                break;
            for (int i = endRow; i >= startRow; i--) {
                result[i][startCol] = start++;
            }
            if (++startCol > endCol)
                break;
        }
        return result;
    }

    /**
     * 打印矩阵函数
     * @param matrix
     */
    public void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GenerateSpiralMatrixTest test = new GenerateSpiralMatrixTest();
        while (true) {
            System.out.print("请输入旋转矩阵的尺寸：");
            int size = scanner.nextInt();
            test.print(test.generateMatrix(size));
            System.out.println();
        }
    }
}
