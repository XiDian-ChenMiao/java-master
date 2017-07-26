package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：在给定数组中给出超过元素总数一半的元素
 * 创建作者：陈苗
 * 创建时间：2016/8/6 16:14
 */
public class FindMoreHalfTest {
    /**
     * 查找的外部访问接口
     * @param array
     * @param length
     * @return
     */
    public int findMoreHalf(int[] array,int length) {
        if (array == null || array.length <= 0 || length <= 0 || array.length != length)
            return 0;
        int result = array[0];
        int times = 1;
        for (int i = 1; i < length; i++) {
            if (result != array[i])
                times--;
            else
                times++;
            if (times == 0)
                result = array[i];
            else if (times > length >> 1)
                return result;
        }
        times = 0;
        for (int i = 0; i < length; i++) {
            if (array[i] == result)
                times++;
        }
        return 2 * times > length ? result : 0;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {1,3,3,3,1};
        System.out.println("个数超过一半的元素为：" + new FindMoreHalfTest().findMoreHalf(array,array.length));
    }
}
