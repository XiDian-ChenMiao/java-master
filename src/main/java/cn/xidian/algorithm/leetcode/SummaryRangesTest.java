package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：总结区间
 * 创建作者：陈苗
 * 创建时间：2017/9/21 19:34
 */
public class SummaryRangesTest {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int len;
        if (nums == null || (len = nums.length) == 0)
            return result;
        int start, end, j;
        for (int i = 0; i < len; i = end + 1) {
            start = i;
            end = i;
            for (j = i + 1; j < len; j++) {
                if (nums[j] - nums[j - 1] == 1) {
                    end++;
                } else {
                    break;
                }
            }
            if (end == start)
                result.add("" + nums[start]);
            else
                result.add(nums[start] + "->" + nums[end]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SummaryRangesTest().summaryRanges(new int[]{0, 1, 2, 4, 5, 7}).toArray()));
    }
}
