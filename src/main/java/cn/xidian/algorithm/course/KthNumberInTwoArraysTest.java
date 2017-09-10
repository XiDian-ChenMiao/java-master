package cn.xidian.algorithm.course;

/**
 * 文件描述：求两个有序数组中的第k个值
 * 创建作者：陈苗
 * 创建时间：2017/6/19 22:31
 */
public class KthNumberInTwoArraysTest {
    /**
     * 外部调用函数
     * @param a
     * @param b
     * @param k
     * @return
     */
    public int findKthSortedArrays(int[] a, int[] b, int k) {
        int m = a.length, n = b.length;
        if (m > n)
            return findKthSortedArrays(b, a, k);
        int left = 0, right = m;
        while (left < right) {
            int mid = left + (right - left) / 2, j = k - 1 - mid;
            if (j >= n || a[mid] < b[j])
                left = mid + 1;
            else
                right = mid;
        }
        int aminus = left - 1 >= 0 ? a[left - 1] : Integer.MIN_VALUE;
        int bminus = k - 1 - left >= 0 ? b[k - 1- left] : Integer.MIN_VALUE;
        return Math.max(aminus, bminus);
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new KthNumberInTwoArraysTest().findKthSortedArrays(new int[]{1, 3, 4}, new int[] {2, 6, 7}, 5));
    }
}
