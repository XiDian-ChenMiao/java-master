package cn.algorithm.leetcode;

/**
 * 文件描述：爬楼梯问题，可走一步或者两步
 * 创建作者：陈苗
 * 创建时间：2016/12/5 10:52
 */
public class ClimbingStairsTest {
    /**
     * 外部调用函数
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int a = 1, b = 2, stair = 3, temp;
        while (stair <= n) {
            temp = a;
            a = b;
            b = temp + b;
            stair++;
        }
        return b;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new ClimbingStairsTest().climbStairs(3));
    }
}
