package cn.algorithm.sordoffer;

/**
 * 文件描述：从旋转数组中获取最小数字
 * 创建作者：陈苗
 * 创建时间：2016年6月1日 17:15
 */
public class SmallestNumberInRotateArrayTest {
    private int[] array;

    /**
     * 构造函数
     *
     * @param array
     */
    public SmallestNumberInRotateArrayTest(int[] array) {
        this.array = array;
    }

    /**
     * 从相对排序的数组中找到最小元素（采用二分查找法）
     *
     * @return
     * @throws Exception 参数传递错误将抛出异常
     */
    public int findSmallestNumber() throws Exception {
        if (array == null || array.length <= 0)
            throw new Exception("参数非法");
        int start = 0;
        int end = array.length - 1;
        int mid = start;//初始化为起始索引避免无谓的循环，如果不满足循环条件，则直接认为数组已经有序，则最小元素即为第一个数字
        while (array[start] >= array[end]) {
            if (end - start == 1) {
                mid = end;
                break;
            }
            mid = (start + end) / 2;
            if (array[start] == array[end] && array[start] == array[mid])
                return minium(start, end);//如果起始、中间以及结束索引位置处三个元素都相同，则只能顺序查找
            if (array[mid] >= array[start])
                start = mid;
            else if (array[mid] <= array[end])
                end = mid;
        }
        return array[mid];
    }

    /**
     * 数组指定范围内的最小元素
     *
     * @param start 起始索引
     * @param end   结束索引
     * @return 最小元素
     */
    private int minium(int start, int end) {
        int result = array[start];
        for (int i = start + 1; i <= end; i++) {
            if (result > array[i])
                result = array[i];
        }
        return result;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        SmallestNumberInRotateArrayTest test = new SmallestNumberInRotateArrayTest(new int[]{4,5,6,1,2});
        try {
            int result = test.findSmallestNumber();
            System.out.println("数组中最小元素为：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
