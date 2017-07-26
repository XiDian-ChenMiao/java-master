package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：将给定字符床按照ZigZang方式显示
 * 创建作者：陈苗
 * 创建时间：2016/7/11 22:20
 */
public class ZigZangShowTest {
    /**
     * 外部调用转换接口函数
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (s == null)
            return null;
        else if (numRows == 1)
            return s;
        else if (numRows > s.length())
            return s;
        StringBuilder builder = new StringBuilder();
        int columns = getColumsCount(numRows,s.length());
        char[][] data = fillInArray(numRows,columns,s);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < columns; j++) {
                if (data[i][j] != '#')
                    builder.append(data[i][j]);
            }
        }
        return builder.toString();
    }

    /**
     * 获取需要生成的数组的列数
     * @param numRows
     * @param length
     * @return
     */
    private int getColumsCount(int numRows, int length) {
        int groupLength = 2 * numRows - 2;/*每组需要安排的字符的长度*/
        int groups = length / groupLength;/*总共可以划分的组数目*/
        int rest = length % groupLength;/*剩余的字符数目*/
        int columns,groupColumns = 0;
        if (groupLength > numRows) {
            groupColumns = 1 + groupLength - numRows;
        } else if (groupLength == numRows) {
            groupColumns = 1;
        }
        columns = groups * groupColumns;
        if (rest != 0) {
            columns = columns + 1;
            if (rest / numRows != 0)
                columns = columns + rest - numRows;
            }
        return columns;
    }

    /**
     * 填充数组
     * @param numRows
     * @param colums
     * @param s
     * @return
     */
    private char[][] fillInArray(int numRows,int colums,String s) {
        char[][] data = new char[numRows][colums];
        initize(data,numRows,colums);
        int index = 0,rowIndex = -1,colIndex,direction = 1;
        for (colIndex = 0; colIndex < colums; ) {/*列*/
            if(index >= s.length())
                return data;
            if (direction == 1) {/*方向向下*/
                data[++rowIndex][colIndex] = s.charAt(index++);
                if (rowIndex == numRows - 1)
                    direction = -1;
            } else {/*方向向上*/
                data[--rowIndex][++colIndex] = s.charAt(index++);
                if (rowIndex == 0)
                    direction = 1;
            }
        }
        return data;
    }

    /**
     * 初始化字符数组操作
     * @param data
     * @param rows
     * @param colums
     */
    private void initize(char[][] data,int rows,int colums) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                data[i][j] = '#';
            }
        }
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new ZigZangShowTest().convert("ABC",2));
    }
}
