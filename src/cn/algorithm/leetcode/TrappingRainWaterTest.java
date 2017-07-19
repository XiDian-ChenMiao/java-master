package cn.algorithm.leetcode;

/**
 * 文件描述：
 * 创建作者：陈苗
 * 创建时间：2016/11/22 19:14
 */
public class TrappingRainWaterTest {
    /**
     * 求需要水覆盖的面积
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length <= 2)
            return 0;
        int max_index = -1, area = 0, max_value = -1;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max_value) {
                max_value = height[i];
                max_index = i;
            }
        }
        int root = height[0];
        for (int i = 0; i < max_index; i++) {
            if (root < height[i])
                root = height[i];
            else
                area += (root - height[i]);
        }
        int i = height.length - 1;
        root = height[i];
        for (; i > max_index; i--) {
            if (height[i] > root)
                root = height[i];
            else
                area += (root - height[i]);
        }
        return area;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new TrappingRainWaterTest().trap(data));
    }
}
