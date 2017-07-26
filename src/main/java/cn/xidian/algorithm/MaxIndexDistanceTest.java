package cn.xidian.algorithm;

/**
 * 文件描述：最大下标距离
 * 创建作者：陈苗
 * 创建时间：2017/6/19 20:36
 */
public class MaxIndexDistanceTest {
    /**
     * 外部调用函数
     * @param array
     * @return
     */
    public int maxDistance(int[] array) {
        if (array == null || array.length < 2)
            return 0;
        boolean[] inDescSeq = new boolean[array.length];
        int min = array[0], cnt = array.length;
        inDescSeq[0] = true;
        for (int i = 1; i < cnt; ++i) {
            if (array[i] < min) {
                inDescSeq[i] = true;
                min = array[i];
            }
        }
        int maxDistance = 0, i = cnt - 1, j = cnt - 1;
        while (i >= 0) {
            if (!inDescSeq[i]) {
                i--;/*倒序找第一个倒序的元素*/
                continue;
            }
            while (array[j] <= array[i] && j > i)
                j--;/*从后往前直到找到符合的元素*/
            if ((j - i) > maxDistance)
                maxDistance = j - i;
            i--;
        }
        return maxDistance;
    }

    /**
     * 主函数调用
     * @param args
     */
    public static void main(String[] args) {
        MaxIndexDistanceTest test = new MaxIndexDistanceTest();
        System.out.println(test.maxDistance(new int[]{5, 3, 4, 0, 1, 4, 1}));
    }
}
