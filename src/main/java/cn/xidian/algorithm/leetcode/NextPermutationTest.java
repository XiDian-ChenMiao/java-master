package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：下一个排列测试类
 * 创建作者：陈苗
 * 创建时间：2016/10/23 17:05
 */
public class NextPermutationTest {
    /**
     * 判断给定一个数组是否存在下一个排列
     * @param nums
     * @return
     */
    public boolean hasNext(int[] nums) {
        int i;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1])
                break;
        }
        return i == -1 ? false : true;
    }

    /**
     * 下一个排列的外部调用方法
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        if (!hasNext(nums))
            return;
        int i;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1])
                break;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] < nums[i])
                j--;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        reverse(nums, i + 1);
    }

    /**
     * 反转函数
     * @param nums
     * @param i
     */
    private void reverse(int[] nums, int i) {
        int start = i, end = nums.length - 1;
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 求所有排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> permutation = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            permutation.add(nums[i]);
        }
        result.add(permutation);
        while (hasNext(nums)) {
            nextPermutation(nums);
            permutation = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++) {
                permutation.add(nums[i]);
            }
            result.add(permutation);

        }
        return result;
    }

    List<List<Integer>> result = new ArrayList<List<Integer>>();
    /**
     * 有重复元素的全排列问题
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        full_permutation(nums, 0, nums.length - 1);
        return result;
    }

    private boolean swap_accepted(int[] data, int start, int end) {
        for (int k = start; k < end; k++) {
            if (data[k] == data[end])
                return false;
        }
        return true;
    }
    /**
     * 全排列的递归算法
     * @param data
     * @param cursor
     * @param end
     */
    private void full_permutation(int[] data, int cursor, int end) {
        if (cursor == end) {
            List<Integer> permutation = new ArrayList<Integer>();
            for (int i = 0; i < data.length; i++) {
                permutation.add(data[i]);
            }
            result.add(permutation);
        } else {
            for (int i = cursor; i <= end; i++) {
                if (!swap_accepted(data, cursor, i))
                    continue;
                swap(data, cursor, i);
                full_permutation(data, cursor + 1, end);
                swap(data, cursor, i);
            }
        }
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {3, 2, 1};
        List<List<Integer>> result = new NextPermutationTest().permute(data);
        for (List<Integer> permuation : result)
            System.out.println(Arrays.toString(permuation.toArray()));

        data = new int[] {4, 2, 3, 4};
        result = new NextPermutationTest().permuteUnique(data);
        for (List<Integer> permuation : result)
            System.out.println(Arrays.toString(permuation.toArray()));
    }
}
