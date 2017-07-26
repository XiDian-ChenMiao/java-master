package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：从给定的一个数字中获取其二进制中的1的个数
 * 创建作者：陈苗
 * 创建时间：2016年6月1日 18:07
 */
public class GetOneNumberTest {
    /**
     * 获取1的个数的函数
     * 把一个整数减去1之后再和原来的整数做位与操作，得到的结果相当于是把整数的二进制表示中的最右边一个1变成0
     * @param n 给定数字
     * @return 二进制中1的个数
     */
    public static int getNumbers(int n) {
        int count = 0;
        while (n != 0) {
            ++count;
            n = (n - 1) & n;
        }
        return count;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("二进制中1的个数为：" + GetOneNumberTest.getNumbers(15));
    }
}
