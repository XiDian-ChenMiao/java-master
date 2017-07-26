package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：用数学方法计算除法
 * 创建作者：陈苗
 * 创建时间：2016/10/21 11:31
 */
public class DivideTwoIntegerTest {
    /**
     * 除法计算
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 商
     */
    public int divide(int dividend, int divisor) {
        long a, b, flag = 0, sum = 0;
        long[] map = new long[33], times = new long[33];
        if (dividend < 0 && divisor < 0) flag = 1;
        else if (dividend > 0 && divisor > 0) flag = 1;
        a = Math.abs((long) dividend);
        b = Math.abs((long) divisor);
        int i = 0;
        map[0] = b;
        times[0] = 1;
        while (map[i] <= a) {
            i++;
            map[i] = map[i - 1] + map[i - 1];
            times[i] = times[i - 1] + times[i - 1];
        }
        for (int j = i - 1; j >= 0; j--) {
            while (a >= map[j]) {
                a -= map[j];
                sum += times[j];
            }
        }
        sum = flag == 1 ? sum : -sum;
        if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int) sum;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int result = new DivideTwoIntegerTest().divide(Integer.MIN_VALUE, 2);
        System.out.println(result);
    }
}
