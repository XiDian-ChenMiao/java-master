package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：对一个数求其算术平方根
 * 创建作者：陈苗
 * 创建时间：2016/12/5 10:41
 */
public class SqrtXTest {
    /**
     * 外部调用方法
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x <= 0)
            return 0;
        for (int i = 0; i <= x; i++) {
            if (i * i <= x && Math.pow(i + 1, 2) > x)
                return i;
        }
        return 0;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new SqrtXTest().mySqrt(15));
    }
}
