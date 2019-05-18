package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: RectangleAreaTest
 * @description: 223.矩形面积
 * @date 2019-05-18 18:21
 */
public class RectangleAreaTest {

    /**
     * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @param E
     * @param F
     * @param G
     * @param H
     * @return
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area_a = (C - A) * (D - B);
        int area_b = (G - E) * (H - F);
        int max_bottom = Math.max(B, F);
        int max_left = Math.max(A, E);
        int min_head = Math.min(D, H);
        int min_right = Math.min(C, G);
        if (max_bottom >= min_head || max_left >= min_right) {
            return area_a + area_b;
        } else {
            return area_a - (min_head - max_bottom) * (min_right - max_left) + area_b;
        }
    }

    public static void main(String[] args) {
        int a = -1500000001;
        int b = 0;
        int c = -1500000000;
        int d = 1;
        int e = 1500000000;
        int f = 0;
        int g = 1500000001;
        int h = 1;
        System.out.println(new RectangleAreaTest().computeArea(a, b, c, d, e, f, g, h));
    }
}
