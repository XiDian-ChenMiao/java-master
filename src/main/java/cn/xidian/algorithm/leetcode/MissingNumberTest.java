package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: MissingNumberTest
 * @description: 268. Missing Number
 * @date 2019-11-09 11:00
 */
public class MissingNumberTest {
    /**
     * find the missing number
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i] ^ i;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MissingNumberTest().missingNumber(new int[]{3, 1, 0}));
    }
}
