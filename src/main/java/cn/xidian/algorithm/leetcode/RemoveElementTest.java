package cn.xidian.algorithm.leetcode;

import java.util.Arrays;

/**
 * 文件描述：在数组中移除指定元素
 * 创建作者：陈苗
 * 创建时间：2016/10/21 10:13
 */
public class RemoveElementTest {
    /**
     * 移除元素
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0)
            return 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[count++] = nums[i];
            }
        }
        return count;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {3, 4, 3, 5};
        System.out.printf("在数组中移除‘%d’之后数组剩余的个数为：%d", 3, new RemoveElementTest().removeElement(data, 3));
        System.out.println("数组中元素为：" + Arrays.toString(data));
    }
}
