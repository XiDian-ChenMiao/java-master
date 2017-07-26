package cn.xidian.algorithm.leetcode;

import java.util.Arrays;

/**
 * 文件描述：合并已排序的数组
 * 创建作者：陈苗
 * 创建时间：2017/4/23 21:31
 */
public class MergeSortedArrayTest {

    /**
     * 合并函数
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1, m_index = m - 1, n_index = n - 1;
        while (m_index >= 0 && n_index >= 0) {
            if (nums1[m_index] > nums2[n_index]) {
                nums1[index--] = nums1[m_index--];
            } else {
                nums1[index--] = nums2[n_index--];
            }
        }
        while (m_index >= 0 && index >= 0) {
            nums1[index--] = nums1[m_index--];
        }
        while (n_index >= 0 && index >= 0) {
            nums1[index--] = nums2[n_index--];
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {0};
        int[] b = {1};
        new MergeSortedArrayTest().merge(a, 0, b, 1);
        System.out.println(Arrays.toString(a));
    }
}
