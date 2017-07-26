package cn.xidian.algorithm.leetcode;

import java.util.Arrays;

/**
 * 文件描述：给定一个数组，获取其中不重复的数字的个数
 * 创建作者：陈苗
 * 创建时间：2016/10/20 22:32
 */
public class RemoveDuplicatesTest {
    /**
     * 移除重复元素，返回剩余个数
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null)
            return 0;
        if (nums.length == 1)
            return 1;
        int count = 0, key = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != key) {
                nums[count++] = key;
                key = nums[i];
            }
        }
        nums[count++] = key;
        return count;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {-3,-3,-2,-1,-1,0,0,0,0,0};
        System.out.println("不重复的元素个数为：" + new RemoveDuplicatesTest().removeDuplicates(data));
        System.out.println("数据为：" + Arrays.toString(data));
    }
}
