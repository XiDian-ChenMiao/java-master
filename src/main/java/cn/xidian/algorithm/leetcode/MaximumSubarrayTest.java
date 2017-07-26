package cn.xidian.algorithm.leetcode;


public class MaximumSubarrayTest {
    /**
     * 最大子数组和外部调用函数
     * @param number
     * @return
     */
    public int maxSubArray(int[] number) {
        if (number == null || number.length == 0) {
            return 0;
        } 
        int sum = number[0],max = number[0];
        for(int i = 1;i < number.length; ++i) {
            if (sum > 0) {
                sum += number[i];
            } else {
                sum = number[i];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new MaximumSubarrayTest().maxSubArray(data));
    }
}