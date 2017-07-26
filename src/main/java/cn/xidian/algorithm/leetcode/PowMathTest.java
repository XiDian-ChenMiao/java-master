package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：编写数学方法中的POW实现
 * 创建作者：陈苗
 * 创建时间：2016/11/26 14:40
 */
public class PowMathTest {
    /**
     * 外部调用函数
     * @param base
     * @param n
     * @return
     */
    public double pow(double base, int n) {
        if (n == 0)
            return 1.0;
        if (n < 0) {
            if (n == Integer.MIN_VALUE)
                return 1.0 / (pow(base, Integer.MAX_VALUE) * base);
            else
                return 1.0 / pow(base, -n);
        }
        double answer = 1.0;
        for (; n > 0; n >>= 1, base *= base) {
            if ((n & 1) > 0)
                answer *= base;
        }
        return answer;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new PowMathTest().pow(3, 4));
    }
}
