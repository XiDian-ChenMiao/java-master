package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: LargestRectangleInHistogramTest
 * @description: 84.柱状图中的最大矩形
 * @date 2019-05-18 16:49
 */
public class LargestRectangleInHistogramTest {
    /**
     * 求取柱状图中的最大矩形
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int largest = 0;
        if (heights == null || heights.length == 0) {
            return largest;
        }
        int area;
        for (int i = 0; i < heights.length; i++) {
            int low = heights[i];
            for (int j = i; j < heights.length; j++) {
                if (heights[j] < low) {/*如果右边相邻的柱子比左边相邻的柱子低*/
                    low = heights[j];
                }
                area = low * (j - i + 1);
                if (largest < area) {
                    largest = area;
                }
            }
        }
        return largest;
    }

    public static void main(String[] args) {
        System.out.println(new LargestRectangleInHistogramTest().largestRectangleArea(new int[]{2, 6, 5, 6, 2, 3}));
    }
}
