package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: RemoveDuplicateInSortedArrayTest
 * @description: 80. Remove Duplicates from Sorted Array II
 * @date 2019-11-09 18:45
 */
public class RemoveDuplicateInSortedArrayTest {

    /**
     * remove duplicates from sorted array
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int cnt = 0;
        for (int num : nums) {
            if (cnt < 2 || num > nums[cnt - 2]) {
                nums[cnt++] = num;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicateInSortedArrayTest().removeDuplicates(new int[]{1, 1, 1, 1}));
    }
}
