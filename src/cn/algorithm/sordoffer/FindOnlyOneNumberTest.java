package cn.algorithm.sordoffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：从给定的数组中查找只出现一次的两个不同数字，其余数字都出现两次
 * 创建作者：陈苗
 * 创建时间：2016/9/26 21:22
 */
public class FindOnlyOneNumberTest {
    /**
     * 查找函数
     * @param array
     * @return 返回查找到的两个数字
     */
    public int[] findOnlyOnceInArray(int[] array) {
        List<Integer> one = new ArrayList<Integer>();
        List<Integer> zero = new ArrayList<Integer>();

        if (array == null || array.length == 0)
            return null;
        int result = array[0];/*最终的数组所有元素的异或结果*/
        for (int i = 1; i < array.length; ++i)
            result ^= array[i];
        String binStr = Integer.toBinaryString(result);
        char[] charArray = binStr.toCharArray();
        int lastOneIndex = charArray.length - 1;
        for (int i = lastOneIndex; i >= 0; --i) {
            if (charArray[i] == '1') {
                lastOneIndex = i;
                break;
            }
        }
        int temp_value = Integer.parseInt(binStr.substring(lastOneIndex, charArray.length), 2);
        for (int data : array) {
            if ((data & temp_value) == 0) {
                zero.add(data);
            } else {
                one.add(data);
            }
        }
        int[] final_result = new int[2];
        if (zero.size() != 0) {
            final_result[0] = zero.get(0);
            int length = zero.size();
            for (int i = 1; i < length; i++) {
                final_result[0] ^= zero.get(i);
            }
        }
        if (one.size() != 0) {
            final_result[1] = one.get(0);
            int length = one.size();
            for (int i = 1; i < length; i++) {
                final_result[1] ^= one.get(i);
            }
        }
        return final_result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {2, 4, 4, 6, 2, 3, 5, 5};
        int[] result = new FindOnlyOneNumberTest().findOnlyOnceInArray(data);
        System.out.printf("结果为：%d和%d", result[0], result[1]);
    }
}
