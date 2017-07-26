package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：木桶效应，求最大容积
 * 创建作者：陈苗
 * 创建时间：2016/10/17 14:15
 */
public class GetMaxWaterTest {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int max = 0, area, left = 0, right = height.length - 1;
        while (left < right) {
            area = (height[right] > height[left] ? height[left] : height[right]) * (right - left);
            max = area > max ? area : max;
            if (height[left] < height[right])
                ++left;
            else
                --right;
        }
        return max;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] height = {2, 5, 3, 4};
        GetMaxWaterTest obj = new GetMaxWaterTest();
        System.out.println("Max Area:" + obj.maxArea(height));
    }
}
