package cn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：生成格雷码
 * 创建作者：陈苗
 * 创建时间：2017/4/23 22:15
 */
public class GrayCodeTest {

    /**
     * 生成n位格雷码
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int size = 1 << n;
        for (int i = 0; i < size; ++i) {
            result.add((i >> 1) ^ i);
            System.out.println(Integer.toBinaryString((i >> 1) ^ i));
        }
        return result;
    }

    /**
     * 获取格雷码
     * @param n
     * @return
     */
    public List<Integer> getGrayCode(int n) {
        List<Integer> result = new ArrayList<>();
        getCode(n, "", result);
        return result;
    }

    /**
     * 递归方式获取格雷码
     * @param x
     * @param prefix
     * @param result
     */
    private void getCode(int x, String prefix, List<Integer> result) {
        if (x == 0) {
            System.out.println(prefix);
            result.add(Integer.parseInt(prefix, 2));
        } else {
            getCode(x - 1, prefix + "0", result);
            getCode(x - 1, prefix + "1", result);
        }
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> result = new GrayCodeTest().grayCode(3);
        System.out.println(Arrays.toString(result.toArray()));
        result = new GrayCodeTest().getGrayCode(3);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
