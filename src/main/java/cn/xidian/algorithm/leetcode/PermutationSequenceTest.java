package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：列举指定次数的排列
 * 创建作者：陈苗
 * 创建时间：2016/12/2 16:10
 */
public class PermutationSequenceTest {
    /**
     * 下一个排列的外部调用方法
     * @param nums
     */
    private void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int i;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1])
                break;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] < nums[i])
                j--;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        reverse(nums, i + 1);
    }

    /**
     * 反转函数
     * @param nums
     * @param i
     */
    private void reverse(int[] nums, int i) {
        int start = i, end = nums.length - 1;
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 求1到n这n个数形成的第k个全排列
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        if (n < 0 || k < 0)
            return "";
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        int count = 1;
        while (count < k) {
            nextPermutation(nums);
            ++count;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : nums)
            sb.append(num);
        return sb.toString();
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new PermutationSequenceTest().getPermutation(9, 80));
    }
}
