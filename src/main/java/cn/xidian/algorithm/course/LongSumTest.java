package cn.xidian.algorithm.course;

/**
 * 文件描述：求数组中连续元素的最大和问题
 * 创建作者：陈苗
 * 创建时间：2016年月日 21:25
 */
public class LongSumTest {
    private int[] number;
    public int start = 0,end = 0;
    /**
     * 构造函数
     * @param number
     */
    public LongSumTest(int[] number) {
        this.number = number;
    }

    /**
     * 获取连续元素和的最大值
     * @return
     */
    public int getMaxSumValue() {
        int sum = number[0],max = number[0];
        for(int i = 1;i < number.length; ++i) {
            if (sum > 0) {
                sum += number[i];
            } else {
                sum = number[i];
                start = i;
                end = i;
            }
            if (sum > max) {
                max = sum;
                end = i;
            }
        }
        return max;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        LongSumTest longSumTest = new LongSumTest(new int[]{1,2,-1,-2,2,1,-2,1,4,-5,4});
        int result = longSumTest.getMaxSumValue();
        System.out.println("最大和为：" + result + ",起始位置为：" + longSumTest.start + ",终止位置为：" + longSumTest.end);
    }
}
