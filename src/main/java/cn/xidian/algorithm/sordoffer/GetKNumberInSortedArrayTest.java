package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：从一个有序数组中找出给定数字出现的次数
 * 创建作者：陈苗
 * 创建时间：2016/9/17 16:38
 */
public class GetKNumberInSortedArrayTest {
    /**
     * 查找第一个数字
     * @param array
     * @param k
     * @param start
     * @param end
     * @return
     */
    private int getFirstK(int[] array, int k, int start, int end) {
        if (array != null) {
            if (start > end)
                return -1;
            int middle = (start + end) / 2;
            int middle_data = array[middle];
            if (middle_data == k) {
                if ((middle > 0 && array[middle - 1] != k) || middle == 0)
                    return middle;
                else
                    end = middle - 1;
            } else if (middle_data > k)
                end = middle - 1;
            else
                start = middle + 1;
            return getFirstK(array, k, start, end);
        }
        return -1;
    }

    /**
     * 查找最后一个数字
     * @param array
     * @param k
     * @param start
     * @param end
     * @return
     */
    private int getLastK(int[] array, int k, int start, int end) {
        if (array != null) {
            if (start > end)
                return -1;
            int middle = (start + end) / 2;
            int middle_data = array[middle];
            if (middle_data == k) {
                if ((middle < array.length - 1 && array[middle + 1] != k) || middle == array.length - 1)
                    return middle;
                else
                    start = middle + 1;
            } else if (middle_data > k)
                end = middle - 1;
            else
                start = middle + 1;
            return getLastK(array, k, start, end);
        }
        return -1;
    }

    /**
     * 获取已排序数组中指定元素存在个数的外部调用
     * @param array
     * @param k
     * @return
     */
    public int getNumberOfK(int[] array, int k) {
        int number = 0;
        if (array != null && array.length > 0) {
            int first = getFirstK(array, k, 0, array.length - 1);
            int second = getLastK(array, k, 0, array.length - 1);
            if (first > -1 && second > -1)
                number = second - first + 1;
        }
        return number;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {1, 1, 2, 2, 3, 3, 3};
        System.out.println("数组中3出现的次数为：" + new GetKNumberInSortedArrayTest().getNumberOfK(data, 3));
    }
}
