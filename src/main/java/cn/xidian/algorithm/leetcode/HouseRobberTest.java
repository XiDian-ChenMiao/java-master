package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：打家劫舍
 * NOTE: 不能抢连续的房间，设置V[i]表示到i位置时不相邻数能形成的最大和，则V[i]=max{V[i-2] + v[i], V[i-1]}
 * 创建作者：陈苗
 * 创建时间：2017/8/28 11:52
 */
public class HouseRobberTest {
    /**
     * 排成一条线的房屋打劫指南
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        List<Integer> dp = new ArrayList<>();
        dp.add(nums[0]);/*把第一家打劫*/
        dp.add(Math.max(nums[0], nums[1]));/*第一家和第二家选择价值大的打劫*/
        for (int i = 2; i < nums.length; i++) {
            dp.add(Math.max(nums[i] + dp.get(i - 2), dp.get(i - 1)));
        }
        return dp.get(dp.size() - 1);
    }
}
