package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：三角
 * NOTE: 给定一个三角形，获取从头到底的最短路径
 * 动态规划递推式：用F(i,k)表示到第i层的第k个顶点的最小路径，则F(i,k) = min{F(i-1, k), F(i-1, k-1)} + d(i,k)，其中d(i,k)表示原来三角形数组里的第i行第k列的元素
 * 创建作者：陈苗
 * 创建时间：2017/8/28 10:33
 */
public class TriangleTest {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0)
            return 0;
        int[] res = new int[triangle.size()];
        for (int i = 0; i < triangle.size(); i++) {
            res[i] = triangle.get(triangle.size() - 1).get(i);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
            }
        }
        return res[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> params = new ArrayList<>();
        params.add(Arrays.asList(new Integer[]{2}));
        params.add(Arrays.asList(new Integer[]{3, 4}));
        params.add(Arrays.asList(new Integer[]{6, 5, 7}));
        params.add(Arrays.asList(new Integer[]{4, 1, 8, 3}));
        System.out.println(new TriangleTest().minimumTotal(params));
    }
}
