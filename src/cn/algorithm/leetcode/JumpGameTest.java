package cn.algorithm.leetcode;

/**
 * 文件描述：跳级游戏
 * 创建作者：陈苗
 * 创建时间：2016/11/28 19:32
 */
public class JumpGameTest {
    /**
     * 判断函数
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        int max = 0;
        for (int i = 0; i <= max; i++) {
            max = Math.max(max, i + nums[i]);
            if (max >= nums.length - 1) {
                return true;
            }
        }
        return max >= nums.length - 1;
    }

    /**
     * 通过动态规划的思想
     * NOTE:其中canStillJump[i]表示到达i位置后，依然有余力走出的最大长度，如果canStillJump[i] < 0，则表示走不到位置i;
     * 所以状态转移方程为：canStillJump[i] = max(canStill[i - 1], steps[i - 1]) - 1;
     * @param nums
     * @return
     */
    public boolean canJumpByDP(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        int[] canStillJump = new int[nums.length];
        canStillJump[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            canStillJump[i] = Math.max(canStillJump[i - 1], nums[i - 1]) - 1;
            if (canStillJump[i] < 0)
                return false;
        }
        return canStillJump[nums.length - 1] >= 0;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {2, 3, 1, 1, 4};
        JumpGameTest test = new JumpGameTest();
        System.out.println(test.canJump(data));
        System.out.println(test.canJumpByDP(data));
        int[] another = {3, 2, 1, 0, 4};
        System.out.println(test.canJump(another));
        System.out.println(test.canJumpByDP(another));
    }
}
