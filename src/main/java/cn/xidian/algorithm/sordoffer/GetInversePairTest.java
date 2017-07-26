package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：求给定数组中逆序对的个数
 * 创建作者：陈苗
 * 创建时间：2016/9/16 11:36
 */
public class GetInversePairTest {
    /**
     * 外部调用函数
     * @param data
     * @return
     */
    public int getInversePairs(int[] data) {
        if (data == null || data.length == 0)
            return 0;
        int[] copy = new int[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        return getInversePairs(data, copy, 0, data.length - 1);
    }

    /**
     * 内部通过类似归并排序的方式递归求解函数
     * @param data
     * @param copy
     * @param start
     * @param end
     * @return
     */
    private int getInversePairs(int[] data, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = data[start];
            return 0;
        }
        int length = (end - start) / 2;
        int left = getInversePairs(data, copy, start, start + length);
        int right = getInversePairs(data, copy, start + length + 1, end);
        /*其中i为数组前半段最后一个数字的下标，j为数组后半段最后一个数字的下标*/
        int i = start + length, j = end, indexCopy = end, count = 0;
        while (i >= start && j >= start + length + 1) {
            if (data[i] > data[j]) {
                copy[indexCopy--] = data[i--];
                count += j - start - length;
            } else {
                copy[indexCopy--] = data[j--];
            }
        }
        for (; i >= start; --i)
            copy[indexCopy--] = data[i];
        for (; j >= start + length + 1; --j)
            copy[indexCopy--] = data[j];
        return left + right + count;
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {7, 5, 6, 4};
        System.out.println("逆序对的个数为：" + new GetInversePairTest().getInversePairs(array));
    }
}
