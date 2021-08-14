package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: SubTetTest
 * @description: LeetCode 78.Sub Set
 * @date 8/14/21 10:48 AM
 */
public class SubTetTest {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Stack<Integer> temp = new Stack<>();
        backtrack(result, nums, 0, temp);
        return result;
    }

    /**
     * 回溯函数
     *
     * @param result
     * @param nums
     * @param current
     * @param temp
     */
    private void backtrack(List<List<Integer>> result, int[] nums, int current, Stack<Integer> temp) {
        result.add(new ArrayList<>(temp));
        for (int index = current; index < nums.length; index++) {
            temp.push(nums[index]);
            backtrack(result, nums, index + 1, temp);
            temp.pop();
        }
    }
}
