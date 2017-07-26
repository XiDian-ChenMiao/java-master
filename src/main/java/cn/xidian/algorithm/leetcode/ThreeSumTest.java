package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：从给定数组中找出所有满足三数之和为零的三元组
 * 创建作者：陈苗
 * 创建时间：2016/10/17 16:30
 */
public class ThreeSumTest {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    /**
     * 外部调用函数
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null)
            return null;
        if (nums.length < 3)
            return new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int len = nums.length;

        for (int i = 0; i < len - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            find(nums, i + 1, len - 1, nums[i]);
        }
        return result;
    }

    public void find(int[] num, int begin, int end, int target) {
        int l = begin, r = end;
        while (l < r) {
            if (num[l] + num[r] + target == 0) {
                result.add(Arrays.asList(new Integer[]{target, num[l], num[r]}));
                while (l < r && num[l] == num[l + 1]) l++;
                while (l < r && num[r] == num[r - 1]) r--;
                l++;
                r--;
            } else if (num[l] + num[r] + target < 0) {
                l++;
            } else {
                r--;
            }
        }
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        List<List<Integer>> result = new ThreeSumTest().threeSum(data);
        for (List<Integer> l : result) {
            for (Integer i : l) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
