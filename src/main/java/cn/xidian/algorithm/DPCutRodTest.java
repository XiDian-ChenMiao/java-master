package cn.xidian.algorithm;

/**
 * 文件描述：用动态规划解决钢条切割问题
 * 创建作者：陈苗
 * 创建时间：2016年6月14日 10:31
 */
public class DPCutRodTest {
    private int[] result;//最大收益结果
    private int[] solution;//切割方案

    /**
     * 切割函数
     *
     * @param value  钢条的售价结果
     * @param length 需要售出的钢条的长度
     */
    private void cutRod(int[] value, int length) {
        result = new int[length + 1];
        solution = new int[length + 1];
        result[0] = solution[0] = 0;
        for (int i = 1; i <= length; i++) {
            int min = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                if (min < value[j] + result[i - j]) {
                    min = value[j] + result[i - j];
                    solution[i] = j;
                }
            }
            result[i] = min;
        }
    }

    /**
     * 打印裁剪方案
     */
    public void printSolution(int[] value, int n) {
        if (value == null || n < 0)
            return;
        cutRod(value, n);
        System.out.println("效益最大值为：" + result[n]);
        System.out.print("裁剪方案是：");
        while (n != 0) {
            System.out.print(solution[n] + "\t");
            n = n - solution[n];
        }
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] value = {0,1,5,8,10,13,17,18,22,25,30};
        new DPCutRodTest().printSolution(value,4);
    }
}
