package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：要求在O（n）的时间复杂度之内使用有限制的空间求一个数组中第一个丢失的正整数的数值
 * 创建作者：陈苗
 * 创建时间：2016/11/22 16:56
 */
public class FirstMissingPositiveTest {
    /**
     * 外部调用函数
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max)
                max = nums[i];
        }
        if (max > 0) {
            int[] temp = new int[max + 2];
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0)
                    temp[nums[i]] = 1;
            }
            for (int i = 1; i < max + 2; i++) {
                if (temp[i] != 1) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        FirstMissingPositiveTest test = new FirstMissingPositiveTest();
        int[] data = {1, 2, 0};
        System.out.printf("The first missing positive is %d.", test.firstMissingPositive(data));
    }
}
