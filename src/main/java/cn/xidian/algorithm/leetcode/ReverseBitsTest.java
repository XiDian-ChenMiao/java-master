package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：逆置比特
 * 创建作者：陈苗
 * 创建时间：2017/9/17 20:19
 */
public class ReverseBitsTest {
    /**
     * 其中n为无符号整数
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1;
            if (i < 31)
                result <<= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseBitsTest().reverseBits(1));
    }
}
