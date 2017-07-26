package cn.xidian.algorithm;

import java.util.Arrays;

/**
 * 文件描述：数位重组算法
 * NOTE：给定两个数组表示的整数，找出第一个整数的重组后最接近第二个整数并且大于第二个整数的排列。
 * 创建作者：陈苗
 * 创建时间：2017/6/19 20:57
 */
public class ReCombinationTest {
    /**
     * 外部调用函数
     * @param x
     * @param y
     * @return
     */
    public int[] getCloseBigger(int[] x, int[] y) {
        int len = x.length;
        int[] res = new int[len];
        Arrays.sort(x);
        int i, k = 0;
        boolean[] used = new boolean[len];
        for (int j = 0; j < len; j++) {
            i = 0;
            while (i < len && (used[i] || x[i] < y[j]))
                i++;
            res[k++] = x[i];
            used[i] = true;

            if (x[i] > y[j]) {
                for (i = 0; i < len; i++) {
                    if (!used[i])
                        res[k++] = x[i];
                }
                break;
            }
        }
        return res;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] x = new int[] {1, 2, 3, 4};
        int[] y = new int[] {2, 4, 1, 0};
        System.out.println(Arrays.toString(new ReCombinationTest().getCloseBigger(x, y)));
    }
}
