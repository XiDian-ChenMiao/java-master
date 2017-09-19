package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 从1到9给定的9个数字中获得k个数字可以组成和为n的所有组合
 */
public class CombinationSumIIITest {

	public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        combination(ans, new ArrayList<Integer>(), k, 1, n);
        return ans;
    }

    private void combination(List<List<Integer>> ans, List<Integer> result, int k, int start, int n) {
    	if (result.size() == k && n == 0) {
    		List<Integer> temp = new ArrayList<Integer>(result);
    		ans.add(temp);
    		return;
    	}
    	for (int i = start; i <= 9; i++) {
    		result.add(i);
    		combination(ans, result, k, i + 1, n - i);
    		result.remove(result.size() - 1);
    	}
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new CombinationSumIIITest().combinationSum3(3, 9);
        for (List<Integer> temp : result)
            System.out.println(Arrays.toString(temp.toArray()));
    }
}