package cn.xidian.algorithm;

/**
 * 文件描述：八皇后问题
 * 创建作者：陈苗
 * 创建时间：2016年6月10日 16:33
 */
public class EightQueenTest {
    private static int count = 1;
    public final static int queens = 8;
    /**
     * 交换函数
     * @param data
     * @param m
     * @param n
     */
    private void swap(int[] data,int m,int n) {
        int temp = data[m];
        data[m] = data[n];
        data[n] = temp;
    }

    /**
     * 打印函数
     * @param data
     */
    private void print(int[] data) {
        System.out.print("第" + count++ + "种结果：");
        for (int c : data)
            System.out.print(c);
        System.out.println();
    }
    /**
     * 求排列的函数
     * @param data 皇后的编号数组，初始化为0-7，其中数组下标索引表示行号，所存储的值表示为当前行所对应皇后排布的列号
     * @param start 表示从当前选到第几个数
     * @param length 表示共有多少个数
     */
    private void permutation(int[] data,int start,int length) {
        if (start == length) {
            if(check(data,length)) {
                //由初始化可知八位皇后已经在不同行，不同列，现在只要求判断所得的全排列中皇后的位置是否存在位于对角线的可能
                print(data);
            }
        } else {
            for (int i = start;i <= length;i++) {
                swap(data,i,start);
                permutation(data,start + 1,length);
                swap(data,i,start);
            }
        }
    }
    /**
     * 判断八位皇后是否在存在有排列在对角线的可能
     * @param data
     * @param length
     * @return
     */
    private boolean check(int[] data, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if ((i - j == data[i] - data[j]) || (j - i == data[i] - data[j]))
                    return false;
            }
        }
        return true;
    }

    /**
     * 获取八皇后的排列结果接口函数
     */
    public void getPermutation() {
        int[] columnIndex = new int[queens];
        //初始化皇后的分布，使分别位于不同行和不同列，其中i表示第i行，columnIndex[i]表示第i行所对应的皇后排布的列索引位置
        for (int i = 0; i < queens; i++) {
            columnIndex[i] = i;
        }
        permutation(columnIndex,0,7);
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        EightQueenTest test = new EightQueenTest();
        test.getPermutation();
    }
}
