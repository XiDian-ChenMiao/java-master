package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：给定一个数组，找出所有满足所有值之和为给定值的组合
 * 创建作者：陈苗
 * 创建时间：2016/11/22 16:35
 */
public class CombinationSumIITest {
    /**
     * 给定一个数组，获取其中满足所有值之和为给定值的组合
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        backTracking(result, new ArrayList<Integer>(), 0, target, candidates);
        return result;
    }

    /**
     * 回溯函数
     * @param result
     * @param cur
     * @param from
     * @param target
     * @param candidates
     */
    private void backTracking(List<List<Integer>> result, List<Integer> cur, int from, int target, int[] candidates) {
        if (target == 0) {
            boolean isRepeat = false;
            for (int i = result.size() - 1; i >= 0; i--) {
                List<Integer> temp = result.get(i);
                if (temp.size() != cur.size())
                    continue;
                int j = 0;
                while (j < cur.size() && cur.get(j) == temp.get(j))
                    j++;
                if (j == cur.size()) {
                    isRepeat = true;
                    break;
                }
            }
            if (!isRepeat) {
                List<Integer> list = new ArrayList<Integer>(cur);
                result.add(list);
            }
            return;
        } else {
            for (int i = from; i < candidates.length && candidates[i] <= target; i++) {
                cur.add(candidates[i]);
                backTracking(result, cur, i + 1, target - candidates[i], candidates);
                cur.remove(new Integer(candidates[i]));
            }
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {1, 1, 2, 5, 6, 6};
        List<List<Integer>> result = new CombinationSumIITest().combinationSum(data, 8);
        for (List<Integer> i : result)
            System.out.println(Arrays.toString(i.toArray()));
    }
}
