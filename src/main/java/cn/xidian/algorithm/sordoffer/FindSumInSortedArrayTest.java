package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：在给定的有序数组中查找有两个值和为给定值的类
 * 创建作者：陈苗
 * 创建时间：2016/9/26 22:25
 */
public class FindSumInSortedArrayTest {
    /**
     * 查找函数
     * @param data
     * @param value
     */
    public void findSumInSortedArray(int[] data, int value) {
        if (data == null || data.length == 0)
            return;
        int start = 0, end = data.length - 1;
        while (start != end) {
            if (data[start] + data[end] > value)
                --end;
            else if (data[start] + data[end] < value)
                ++start;
            else {
                System.out.printf("在数组中找到和为给定值%d的两个数分别为%d和%d，下标分别为%d和%d.", value, data[start], data[end], start, end);
                break;
            }
        }
        if (start == end)
            System.out.printf("在数组中未能找到两个数的和为给定值%d.", value);
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {1, 2, 4, 7, 11, 15};
        new FindSumInSortedArrayTest().findSumInSortedArray(array, 15);
    }
}
