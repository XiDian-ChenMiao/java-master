package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：给定数组生成最大的数
 * 创建作者：陈苗
 * 创建时间：2016/10/20 20:41
 */
public class GenerateBiggestNumberTest {
    /**
     * 生成最大的数
     * @param nums
     * @return
     */
    public String generateBiggestNumber(int[] nums) {
        String[] numsStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsStr[i] = String.valueOf(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((numsStr[i] + numsStr[j]).compareTo(numsStr[j] + numsStr[i]) < 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        if ("0".compareTo(String.valueOf(nums[0])) == 0)
            return "0";
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            strBuilder.append(nums[i]);
        }
        return strBuilder.toString();
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {3, 30, 34, 5, 9};
        System.out.println("生成的最大的数为：" + new GenerateBiggestNumberTest().generateBiggestNumber(data));
    }
}
