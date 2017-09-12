package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：连续子数组乘积最大
 * 创建作者：陈苗
 * 创建时间：2017/9/12 11:36
 */
public class MaximumProductSubarrayTest {
    public int maxProduct(int[] nums) {
        if (nums == null)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int max_local = nums[0], min_local = nums[0], global = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int max_copy = max_local;
            max_local = Math.max(Math.max(nums[i] * max_local, nums[i]), nums[i] * min_local);
            min_local = Math.min(Math.min(nums[i] * max_copy, nums[i]), nums[i] * min_local);
            global = Math.max(global, max_local);
        }
        return global;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumProductSubarrayTest().maxProduct(new int[]{-2, 3, -1, 3, -4, 5, 5}));
    }
}
