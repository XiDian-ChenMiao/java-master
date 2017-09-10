package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：给定一个数组，求出这个数组中唯一没有出现两次的那个数字
 * 创建作者：陈苗
 * 创建时间：2017/9/10 22:20
 */
public class SingleNumberTest {

    public int singleNumber(int[] nums) {
        if (nums == null || (nums.length & 0x01) != 1)
            return 0;
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SingleNumberTest().singleNumber(new int[]{1, 1, 2, 2, 5}));
    }
}
