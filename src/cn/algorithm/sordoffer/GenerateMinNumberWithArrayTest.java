package cn.algorithm.sordoffer;

/**
 * 文件描述：利用数组中的数字，生成最小的数
 * 创建作者：陈苗
 * 创建时间：2017/2/20 21:30
 */
public class GenerateMinNumberWithArrayTest {
    /**
     * 外部调用函数（利用冒泡排序以及字符串比较的方法）
     * @param data
     * @return
     */
    public String generateMinNumber(int[] data) {
        if (data == null || data.length == 0)
            return null;
        if (data.length == 1)
            return data[0] + "";
        boolean flag = true;
        for (int i = 0; i < data.length && flag; i++) {
            flag = false;
            for (int j = data.length - 1; j > i ; j--) {
                if ((data[i] + "" + data[j]).compareTo(data[j] + "" + data[i]) > 0) {
                    swap(data, i, j);
                    flag = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]);
        }
        return sb.toString();
    }

    /**
     * 交换函数
     * @param data
     * @param i
     * @param j
     */
    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    /**
     * 主程序
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {5, 56, 7};
        System.out.println(new GenerateMinNumberWithArrayTest().generateMinNumber(data));
    }
}
