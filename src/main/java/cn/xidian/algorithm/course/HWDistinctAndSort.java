package cn.xidian.algorithm.course;

import java.util.Arrays;

/**
 * 文件描述：华为机试题
 * 创建作者：陈苗
 * 创建时间：2016/7/21 15:33
 */
public class HWDistinctAndSort {
    /**
     * 去重和排序
     * @param length
     * @param number
     * @return
     */
    public static int[] distinctAndSort(int length,int[] number) {
        Arrays.sort(number);
        int j,sum = 0;
        for (int i = 0; i < length - 1;) {
            for (j = i + 1; j < length; ) {
                if (number[j] == number[i]) {
                    number[j] = Integer.MIN_VALUE;
                    sum++;
                    j++;
                    continue;
                }
                break;
            }
            i = j;
        }
        int[] result = new int[length - sum];
        j = 0;
        for (int i = 0; i < length; i++) {
            if (number[i] != Integer.MIN_VALUE)
                result[j++] = number[i];
        }
        return result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] result = HWDistinctAndSort.distinctAndSort(7,new int[]{3,5,9,5,7,8,9});
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
