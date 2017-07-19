package cn.algorithm.sordoffer;

/**
 * 文件描述：给定一个数，打印所有连续数字序列总和为这个数的可能情况
 * 创建作者：陈苗
 * 创建时间：2016/10/13 15:03
 */
public class PrintAllNumberAddTest {
    /**
     * 外部调用函数
     * @param sum
     */
    public void printResult(int sum) {
        if (sum < 3)
            return;
        int small = 1, big = 2, end = (sum + 1) / 2;
        int currentSum = small + big;
        while (small < end) {
            if (currentSum == sum)
                print(small, big, sum);/*找到一种可能，打印相加结果格式*/
            while (currentSum > sum && small < end) {
                currentSum -= small;
                small++;
                if (currentSum == sum)
                    print(small, big, sum);
            }
            big++;
            currentSum += big;
        }
    }

    /**
     * 显示格式函数
     * @param small
     * @param big
     * @param sum
     */
    private void print(int small, int big, int sum) {
        for (int i = small; i <= big; i++) {
            System.out.print(i);
            if (i != big)
                System.out.print("+");
        }
        System.out.print("=" + sum);
        System.out.println();
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        PrintAllNumberAddTest test = new PrintAllNumberAddTest();
        test.printResult(15);
    }
}
