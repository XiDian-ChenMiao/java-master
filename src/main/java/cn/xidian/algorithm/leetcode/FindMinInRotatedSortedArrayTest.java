package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：在旋转有序数组中查找最小数
 * 创建作者：陈苗
 * 创建时间：2017/9/12 16:58
 */
public class FindMinInRotatedSortedArrayTest {
    /**
     * 查找最小数
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int start = 0, end = nums.length - 1;
        int mid = start;
        while (nums[start] >= nums[end]) {
            if (end - start == 1) {
                mid = end;
                break;
            }
            mid = (start + end) / 2;
            if (nums[mid] == nums[start] && nums[mid] == nums[end]) {
                return min(nums, start, end);
            }
            if (nums[mid] >= nums[start])
                start = mid;
            else if (nums[mid] <= nums[end])
                end = mid;
        }
        return nums[mid];
    }

    private int min(int[] data, int start, int end) {
        int min = data[start];
        for (int i = start + 1; i <= end; i++) {
            if (min > data[i])
                min = data[i];
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new FindMinInRotatedSortedArrayTest().findMin(new int[] {4, 5, 6, 1, 2, 3}));
    }
}
