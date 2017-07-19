package cn.algorithm.leetcode;

/**
 * 文件描述：将数字反转
 * 创建作者：陈苗
 * 创建时间：2016/7/12 17:20
 */
public class ReverseIntegerTest {
    /**
     * 反转数字的调用接口
     * @param x 待反转的数字
     * @return
     */
    public int reverse(int x) {
        int flag = x >= 0 ? 1 : -1;
        int y = x >= 0 ? x : x * (-1);
        int sum = 0,previous;/*previous用于记录未扩大之前的总和*/
        while (y != 0) {
            previous = sum;
            sum = sum * 10 + y % 10;
            if (sum / 10 != previous)/*如果存在了越界的可能，则此式成立*/
                return 0;
            y = y / 10;
        }
        return sum * flag;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new ReverseIntegerTest().reverse(1534236469));
    }
}
