package cn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：从给定数组中找出所有满足四数之和为给定值的四元组
 * 创建作者：陈苗
 * 创建时间：2016/10/18 10:09
 */
public class FourSumTest {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    /**
     * 外部调用函数
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null)
            return null;
        if (nums.length < 4)
            return new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; ++i) {
            if (i > 0 && nums[i] == nums[i -1])
                continue;
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                find(nums, j + 1, len - 1, nums[i], nums[j], target);
            }
        }
        return result;
    }

    /**
     * 查找四元组
     *
     * @param num
     * @param begin
     * @param end
     * @param fixed_one
     * @param fixed_two
     * @param target
     */
    public void find(int[] num, int begin, int end, int fixed_one, int fixed_two, int target) {
        int l = begin, r = end;
        while (l < r) {
            int value = num[l] + num[r] + fixed_one + fixed_two;
            if (value == target) {
                result.add(Arrays.asList(new Integer[]{fixed_one, fixed_two, num[l], num[r]}));
                while (l < r && num[l] == num[l + 1]) l++;
                while (l < r && num[r] == num[r - 1]) r--;
                l++;
                r--;
            } else if (value < target) {
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
        int[] data = {-1,-5,-5,-3,2,5,0,4};
        int target = -7;
        List<List<Integer>> result = new FourSumTest().fourSum(data, target);
        for (List<Integer> l : result) {
            for (Integer i : l) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
