package cn.algorithm.leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsIITest {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> array = new ArrayList<Integer>();
        result.add(array);
        if (nums == null)
            return result;
        Arrays.sort(nums);
        for (int i = 1; i <= nums.length; ++i) {
            array.clear();
            dfs(nums, 0, i, array, result);
        }  
        return result;
    }

    private void dfs(int[] nums, int start, int number, List<Integer> array, List<List<Integer>> result) {
        if (array.size() == number) {
            result.add(new ArrayList<Integer>(array));
            return;
        }
        int i = start;
        while (i < nums.length) {
            array.add(nums[i]);
            dfs(nums, i + 1, number, array, result);
            array.remove(array.size() - 1);
            while (i < (nums.length - 1) && nums[i] == nums[i + 1]) {
                ++i;
            }
            ++i;
        }
    }

    public static void main(String[] args) {

    }
}