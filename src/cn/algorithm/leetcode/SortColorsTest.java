package cn.algorithm.leetcode;

import java.util.Arrays;

/**
 * 文件描述：颜色排序测试类
 * 创建作者：陈苗
 * 创建时间：2016/12/30 17:00
 */
public class SortColorsTest {
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 颜色排序外部调用函数
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int len = nums.length;
        int i, r = 0, b = 0;
        for (i = 0; i < len - b; i++) {
            if (nums[i] == 0) {
                swap(nums, i, r);
                r++;
            } else if (nums[i] == 2) {
                swap(nums, i, len - 1 - b);
                b++;
                i--;
            }
        }
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {0, 1, 0, 1, 2, 1, 0};
        new SortColorsTest().sortColors(data);
        System.out.println(Arrays.toString(data));
    }
}
