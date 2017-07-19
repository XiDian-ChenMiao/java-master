package cn.algorithm.leetcode;

/**
 * 文件描述：在旋转有序数组中查找元素
 * 创建作者：陈苗
 * 创建时间：2016/10/23 22:36
 */
public class SearchInRotatedSortedArrayTest {
    /**
     * 查找函数
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;
        int length = nums.length;
        if (nums[0] < nums[length - 1])
            return binarySearch(nums, 0, length - 1, target);
        else {
            int provt = getProvt(nums);
            if (target >= nums[0]) {
                return binarySearch(nums, 0, provt - 1, target);
            } else {
                return binarySearch(nums, provt, length - 1, target);
            }
        }
    }

    /**
     * 获取哨兵位置
     * @param nums
     * @return
     */
    public int getProvt(int[] nums) {
        int location = nums.length - 1;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid + 1] < nums[mid])
                return mid + 1;
            if (nums[mid] > nums[0])
                start = mid;
            else
                end = mid;
        }
        return location;
    }

    /**
     * 二分查找
     * @param nums
     * @param start
     * @param end
     * @param target
     * @return
     */
    private int binarySearch(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                end = mid - 1;
            else
                start = mid + 1;
        }
        return -1;
    }
     /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {1, 3};
        SearchInRotatedSortedArrayTest test = new SearchInRotatedSortedArrayTest();
        System.out.println(test.search(data, 3));
    }
}
