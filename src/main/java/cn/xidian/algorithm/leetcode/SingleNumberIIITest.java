package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: SingleNumberIIITest
 * @description: Single Number III (from leetcode No.260)
 * @date 2019-08-18 15:36
 */
public class SingleNumberIIITest {
    /**
     * find the two number
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int diffResult = 0;/*the xor value about all elements*/
        for (int i = 0; i < nums.length; i++) {
             diffResult ^= nums[i];
        }
        int middle = 1;/*the first 1 occured in binary xor value*/
        while ((diffResult & 1) == 0) {
            diffResult = diffResult >> 1;
            middle *= 2;
        }
        int and_1 = 0, and_0 = 0;
        for (int i = 0; i < nums.length; i++) {/*divided elements into two parts*/
            if ((nums[i] & middle) == middle) {
                and_1 ^= nums[i];
            } else {
                and_0 ^= nums[i];
            }
        }
        return new int[]{and_0, and_1};
    }

    public static void main(String[] args) {
        int[] result = new SingleNumberIIITest().singleNumber(new int[]{1, 1, 2, 2, 3, 5});
        System.out.println(String.format("%d-%d", result[0], result[1]));
    }
}
