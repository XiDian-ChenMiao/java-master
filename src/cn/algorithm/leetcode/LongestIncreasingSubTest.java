package cn.algorithm.leetcode;

import java.util.*;

/**
 * Description：最长递增子序列
 * Author：Chen Miao
 * Create Time：2016/10/19 18:52
 */
public class LongestIncreasingSubTest {
    /**
     * 通过使用动态规划来获取最长递增子序列
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] f = new int[nums.length];
        List<Integer> result = new ArrayList<Integer>();
        f[0] = 1;
        for (int i = 1; i < nums.length; ++i) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && f[j] > f[i] - 1) {
                    f[i] = f[j] + 1;
                    if (result.isEmpty())
                        result.add(nums[j]);
                    if (!result.contains(nums[i]))
                        result.add(nums[i]);
                }
            }
        }
        System.out.println("原序列为：" + Arrays.toString(nums));
        System.out.println("最长递增子序列长度为：" + f[nums.length - 1]);
        System.out.println("最长递增子序列为：" + result);
        return f[nums.length - 1];
    }

    /**
     * 最长递减子序列
     * @param nums
     * @return
     */
    public int lengthOfLDS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] f = new int[nums.length];
        int max = 0;/*用来记录最长递减序列的末尾元素*/
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i] && f[j] > f[i] - 1) {
                    f[i] = f[j] + 1;
                    if (f[i] > f[max])
                        max = i;/*更新当前找到的最长递减序列*/
                }
            }
        }
        generate(nums, f, max, result);
        System.out.println("原序列为：" + Arrays.toString(nums));
        System.out.println("最长递减子序列长度为：" + result.size());
        System.out.println("最长递减子序列为：" + result);
        return result.size();
    }

    private void generate(int[] nums, int f[], int max, List<Integer> result) {
        for (int i = max - 1; i >= 0; i--) {
            if (nums[i] > nums[max] && f[max] == f[i] + 1) {
                generate(nums, f, i, result);
                break;
            }
        }
        result.add(nums[max]);
    }
    /**
     * 最长公共子序列
     */
    public static <E> List<E> longestCommonSubsequence(E[] s1, E[] s2) {
        int[][] num = new int[s1.length + 1][s2.length + 1];
        for (int i = 1; i < s1.length + 1; i++) {
            for (int j = 1; j < s2.length + 1; j++) {
                if (s1[i - 1].equals(s2[j - 1])) {
                    num[i][j] = 1 + num[i - 1][j - 1];
                } else {
                    num[i][j] = Math.max(num[i - 1][j], num[i][j - 1]);
                }
            }
        }
        System.out.println("最长公共子序列的长度为：" + num[s1.length][s2.length]);
        int s1position = s1.length, s2position = s2.length;
        List<E> result = new LinkedList<E>();
        while (s1position > 0 && s2position > 0) {
            if (s1[s1position - 1].equals(s2[s2position - 1])) {
                result.add(s1[s1position - 1]);
                s1position--;
                s2position--;
            } else if (num[s1position][s2position - 1] >= num[s1position - 1][s2position]) {
                s2position--;
            } else {
                s1position--;
            }
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * 最长公共子串
     * @param s1
     * @param s2
     * @return
     */
    public String longestIncreasingString(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
            return null;
        char[] chars_s1 = s1.toCharArray();
        char[] chars_s2 = s2.toCharArray();
        int[][] temp = new int[chars_s1.length][chars_s2.length];
        int length = 0;
        int end = 0, start;
        for (int i = 0; i < chars_s2.length; i++) {
            temp[0][i] = (chars_s1[0] == chars_s2[i]) ? 1 : 0;
        }
        for (int i = 0; i < chars_s1.length; i++) {
            temp[i][0] = chars_s2[0] == chars_s1[i] ? 1 : 0;
        }
        for (int i = 1; i < chars_s1.length; i++) {
            for (int j = 1; j < chars_s2.length; j++) {
                if (chars_s1[i] == chars_s2[j]) {
                    temp[i][j] = temp[i - 1][j - 1] + 1;
                    if (temp[i][j] > length) {
                        length = temp[i][j];
                        end = j;
                    }
                }
                else
                    temp[i][j] = 0;
            }
        }
        start = end - length + 1;
        StringBuilder result = new StringBuilder();
        for (int i = start; i < end + 1; i++) {
            result.append(chars_s2[i]);
        }
        System.out.printf("%s和%s的最长公共子串为：%s", s1, s2, result.toString());
        return result.toString();
    }
    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {3, 5, 2, 1, 7};
        LongestIncreasingSubTest test = new LongestIncreasingSubTest();
        test.lengthOfLIS(data);
        Character[] c1 = {'a', 'b', 'd'};
        Character[] c2 = {'a', 'b', 'c'};
        List<Character> result = LongestIncreasingSubTest.longestCommonSubsequence(c1, c2);
        System.out.println("最长公共子序列为：" + result);
        test.lengthOfLDS(data);
        test.longestIncreasingString("ChenMiao", "henA");
        System.out.println();
    }
}
