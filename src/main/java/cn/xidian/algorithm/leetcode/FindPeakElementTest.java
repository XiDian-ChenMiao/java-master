package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：从一个数组中查找峰顶索引位置
 * 创建作者：陈苗
 * 创建时间：2017/9/12 18:15
 */
public class FindPeakElementTest {
    /**
     * 
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1)
            return 0;
        int pre = Integer.MIN_VALUE, cur, next;
        for (int i = 0; i < nums.length; ++i) {
            cur = nums[i];
            if (i + 1 == nums.length)
                next = Integer.MIN_VALUE;
            else
                next = nums[i + 1];
            if (pre < cur && cur > next) {
                return i;
            }
            pre = cur;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new FindPeakElementTest().findPeakElement(new int[]{1, 2, 3, 1}));
    }
}
