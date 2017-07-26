package cn.xidian.algorithm.leetcode;

import java.util.Arrays;

/**
 * 文件描述：
 * 创建作者：陈苗
 * 创建时间：2016/10/24 11:12
 */
public class SearchRangeTest {
    /**
     * 二分查找
     * @param nums
     * @param start
     * @param end
     * @param target
     * @return
     */
    public int binarySearchSmaller(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] < target) {
                if (mid + 1 < nums.length && nums[mid + 1] == target)
                    return mid + 1;
                else
                    start = mid + 1;
            } else {
                if (nums[mid] == target && mid == 0)
                    return 0;
                end = mid - 1;
            }
        }
        return -1;
    }

    /**
     *
     * @param nums
     * @param start
     * @param end
     * @param target
     * @return
     */
    public int binarySearchBigger(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] > target) {
                if (mid - 1 >= 0 && nums[mid - 1] == target)
                    return mid - 1;
                else
                    end = mid - 1;
            }
            else {
                if (nums[mid] == target && mid == nums.length - 1)
                    return mid;
                start = mid + 1;
            }
        }
        return -1;
    }
    /**
     * 寻找范围的外部调用函数
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};
        int start = binarySearchSmaller(nums, 0, nums.length - 1, target);
        int end = binarySearchBigger(nums, 0, nums.length - 1, target);
        return new int[]{start, end};
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {5, 8};
        SearchRangeTest test = new SearchRangeTest();
        System.out.println(test.binarySearchSmaller(data, 0, data.length - 1, 8));
        System.out.println(test.binarySearchBigger(data, 0, data.length - 1, 8));
        System.out.println(Arrays.toString(test.searchRange(data, 8)));
    }
}
