package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：获取插入位置
 * 创建作者：陈苗
 * 创建时间：2016/10/24 16:07
 */
public class SearchInsertPositionTest {
    /**
     * 寻找插入位置的外部调用函数
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1)
            return nums[0] >= target ? 0 : 1;
        int length = nums.length, start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target) {
                if (mid == 0)
                    return 0;
                if (mid - 1 >= 0 && nums[mid - 1] < target)
                    return mid;
                end = mid - 1;
            } else {
                if (mid == length - 1)
                    return length;
                if (mid + 1 < length && nums[mid + 1] > target)
                    return mid + 1;
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {1, 3, 5};
        System.out.println(new SearchInsertPositionTest().searchInsert(data, 6));
    }
}
