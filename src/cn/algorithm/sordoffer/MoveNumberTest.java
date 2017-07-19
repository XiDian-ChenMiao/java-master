package cn.algorithm.sordoffer;

/**
 * 使用策略模式实现类似于C语言中函数指针的功能
 */
interface MoveStrategy {
    /**
     * 判定函数
     * @param number
     * @return
     */
    boolean justify(int number);
}

/**
 * 通过奇偶性确定移动策略类
 */
class ReorderEvenOdd implements MoveStrategy {
    /**
     * 判定奇偶函数
     * @param number
     * @return 返回true，则为偶数；返回false，则为奇数
     */
    @Override
    public boolean justify(int number) {
        return (number & 0x1) == 0;
    }
}

/**
 * 文件描述：按照指定要求移动数组中元素的测试类
 * 创建作者：陈苗
 * 创建时间：2016年6月2日 15:40
 */
public class MoveNumberTest {
    private int[] data;

    /**
     * 构造函数
     * @param data 待调整数组
     */
    public MoveNumberTest(int[] data) {
        this.data = data;
    }

    /**
     * 按照奇数在前，偶数在后的策略重排数组
     * @param strategy
     */
    public void reOrder(MoveStrategy strategy) {
        if (data == null || data.length == 0)
            return;
        int start = 0;
        int end = data.length - 1;
        while (start < end) {
            while (start < end && !strategy.justify(data[start]))
                ++start;
            while (start < end && strategy.justify(data[end]))
                --end;
            if (start < end) {
                int temp = data[start];
                data[start] = data[end];
                data[end] = temp;
            }
        }
        for (int result : data)
            System.out.println(result + " ");
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        MoveNumberTest test = new MoveNumberTest(new int[]{1,2,3,4,5,6});
        test.reOrder(new ReorderEvenOdd());
    }
}
