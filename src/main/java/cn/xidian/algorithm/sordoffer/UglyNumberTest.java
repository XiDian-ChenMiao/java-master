package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：生成丑数
 * 创建作者：陈苗
 * 创建时间：2017/2/20 22:06
 */
public class UglyNumberTest {
    /**
     * 获取指定第几个的丑数方法
     * @param index
     * @return
     */
    public int getUglyNumber(int index) {
        if (index <= 0)
            return 0;
        int[] data = new int[index];
        data[0] = 1;
        int nextUglyIndex = 1, ugly_2 = 0, ugly_3 = 0, ugly_5 = 0;
        while (nextUglyIndex < index) {
            int min = Math.min(data[ugly_2] * 2, Math.min(data[ugly_3] * 3, data[ugly_5] * 5));
            data[nextUglyIndex] = min;
            while (data[ugly_2] * 2 <= min)
                ++ugly_2;
            while (data[ugly_3] * 3 <= min)
                ++ugly_3;
            while (data[ugly_5] * 5 <= min)
                ++ugly_5;
            ++nextUglyIndex;
        }
        return data[nextUglyIndex - 1];
    }

    /**
     * 主函数调用
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new UglyNumberTest().getUglyNumber(1500));
    }
}
