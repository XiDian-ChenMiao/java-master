package cn.xidian.other;

import java.util.Arrays;

/**
 * Description：
 * Author：Chen Miao
 * Create Time：2016/10/20 11:34
 */
public class SortDescTest {

    public static void main(String[] args) {
        int[] data = {5, 2, 4, 1, 3};
        System.out.println("源数据为：" + Arrays.toString(data));
        Arrays.sort(data);
        System.out.println("升序排列为：" + Arrays.toString(data));
        for (int start = 0, end = data.length - 1; start < end; start++, end--) {
            int temp = data[end];
            data[end] = data[start];
            data[start] = temp;
        }
        System.out.println("降序排列为：" + Arrays.toString(data));
    }
}
