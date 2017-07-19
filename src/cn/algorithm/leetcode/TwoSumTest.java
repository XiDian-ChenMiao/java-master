package cn.algorithm.leetcode;

/**
 * 文件描述：
 * 创建作者：陈苗
 * 创建时间：2016/7/9 20:37
 */
public class TwoSumTest {
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int[] result = new int[2];
        boolean find = false;
        for(int i = 0;i < length - 1;++i) {
            if(find != false)
                break;
            for(int j = i + 1;j < length;++j)
            {
                if(nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    find = true;
                }
            }
        }
        return result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] result = new TwoSumTest().twoSum(new int[]{3,2,4},6);
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }
}
