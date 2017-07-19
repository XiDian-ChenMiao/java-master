package cn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：给定一个不存在重复元素的数组，找出所有满足所有值之和为给定值的组合，其中元素可以出现任意多次
 * 创建作者：陈苗
 * 创建时间：2016/11/22 16:02
 */
public class CombinationSumTest {
    /**
     * 外部调用函数
     * @param candidates 参数为不重复的正整数数组
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        backTracking(result, new ArrayList(), 0, target, candidates);
        return result;
    }

    /**
     * 回溯函数
     * @param cur
     * @param from
     * @param target
     */
    private void backTracking(List<List<Integer>> result, List<Integer> cur, int from, int target, int[] candidates) {
        if (target == 0) {
            List<Integer> list = new ArrayList<Integer>(cur);
            result.add(list);/*将可能的结果集加入到最终的结果中*/
        } else {
            for (int i = from; i < candidates.length && candidates[i] <= target; i++) {
                cur.add(candidates[i]);
                /*从标号i开始的原因是数字可以选择多个*/
                backTracking(result, cur, i, target - candidates[i], candidates);
                cur.remove(new Integer(candidates[i]));
            }
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {2, 3, 5, 6};
        List<List<Integer>> result = new CombinationSumTest().combinationSum(data, 8);
        for (List<Integer> i : result)
            System.out.println(Arrays.toString(i.toArray()));
    }
}
