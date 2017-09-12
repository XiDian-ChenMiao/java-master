package cn.xidian.algorithm.leetcode;

import java.util.Arrays;

/**
 * 文件描述：一个数组中挑出三个数使其乘积最大
 * 创建作者：陈苗
 * 创建时间：2017/9/12 16:25
 */
public class MaximumProductofThreeNumbersTest {
    /**
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        if (nums == null)
            return 0;
        if (nums.length < 3) {
            int result = 1;
            for (int i = 0; i < nums.length; i++) {
                result *= nums[i];
            }
            return result;
        }
        Arrays.sort(nums);
        int n = nums.length;
        int max = nums[n - 1] * nums[n - 2] * nums[n - 3];
        max = Math.max(max, nums[n - 1] * nums[n - 2] * nums[0]);
        max = Math.max(max, nums[n - 1] * nums[1] * nums[0]);
        max = Math.max(max, nums[2] * nums[1] * nums[0]);
        return max;
    }
}
