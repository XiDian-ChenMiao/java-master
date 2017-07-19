package cn.algorithm.sordoffer;

/**
 * 文件描述：顺时钟的打印矩阵
 * 创建作者：陈苗
 * 创建时间：2016年6月5日 10:43
 */
public class PrintMatrixClockTest {
    private int[][] data;
    private int rows;
    private int columns;

    /**
     * 构造函数
     *
     * @param data    矩阵信息
     * @param rows    行数
     * @param columns 列数
     */
    public PrintMatrixClockTest(int[][] data, int rows, int columns) {
        this.data = data;
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * 按照顺时钟打印矩阵
     */
    public void printMatrix() {
        if (data == null || data.length <= 0 || rows <= 0 || columns <= 0)
            return;
        int start = 0;//起始位置
        while (rows > start * 2 && columns > start * 2) {
            printCycle(start);
            ++start;
        }
    }

    /**
     * 打印一圈的函数
     *
     * @param start 起始位置
     */
    private void printCycle(int start) {
        int endX = columns - 1 - start;
        int endY = rows - 1 - start;
        //打印第一步：即第一行，这是必须步骤 →
        for (int i = start; i <= endX; ++i)
            System.out.print(data[start][i] + "\t");
        //打印第二步：如果还有一列，则纵向打印一列 ↓
        if (start < endY) {
            for (int i = start + 1; i <= endY; ++i)
                System.out.print(data[i][endY] + "\t");
        }
        //打印第三步：如果圈内至少两行两列，则从右向左继续打印 ←
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; --i)
                System.out.print(data[endY][i] + "\t");
        }
        //打印第四步：如果还存在至少三行两列，则从下向上打印一列 ↑
        if (start < endX && start < endY - 1) {
            for (int i = endY - 1; i >= start + 1; --i)
                System.out.print(data[i][start] + "\t");
        }
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
        PrintMatrixClockTest test = new PrintMatrixClockTest(matrix, 4, 4);
        test.printMatrix();
    }
}
