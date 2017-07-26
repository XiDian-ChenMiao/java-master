package cn.xidian.algorithm.leetcode;

import java.util.Arrays;

/**
 * 文件描述：在数组中找到最接近给定数字的三元组之和
 * 创建作者：陈苗
 * 创建时间：2016/10/17 21:35
 */
public class ThreeSumClosestTest {
    private int width = Integer.MAX_VALUE;
    private int result;
    /**
     * 求给定目标之后在数组中找到三数之和最接近的元组
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3)
            return 0;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            if(find(nums, i + 1, len - 1, nums[i], target))
                break;
        }
        return result;
    }

    public boolean find(int[] num, int begin, int end, int value, int target) {
        int l = begin, r = end;
        while (l < r) {
            int sum = num[l] + num[r] + value;
            int length = getLength(sum, target);
            if (sum == target) {
                result = sum;
                return true;
            } else if (sum < target) {
                if (width > length) {
                    width = length;
                    result = sum;
                }
                l++;
            } else {
                if (width > length) {
                    width = length;
                    result = sum;
                }
                r--;
            }
        }
        return false;
    }

    /**
     * 获取坐标轴相对长度
     * @param one
     * @param two
     * @return
     */
    private int getLength(int one, int two) {
        if ((one >= 0 && two >= 0) || (one <= 0 && two <= 0))
            return Math.abs(one - two);
        else if (one < 0 && two >= 0) {
            return two - one;
        } else if (one > 0 && two <= 0) {
            return one - two;
        }
        return 0;
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {0, 1, 2};
        System.out.println("最接近的值为：" + new ThreeSumClosestTest().threeSumClosest(data, 3));
    }
}
