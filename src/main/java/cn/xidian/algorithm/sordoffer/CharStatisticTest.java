package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：从字符流中找出第一个不重复的字符
 * 创建作者：陈苗
 * 创建时间：2017/2/21 20:18
 */
public class CharStatisticTest {
    /**
     * 外部调用函数
     * @param str
     * @return
     */
    public char getFirstAppearingOnce(String str) {
        int[] indexs = new int[256];
        for (int i = 0; i < indexs.length; i++) {
            indexs[i] = -1;
        }
        int index = 0;
        for (char c : str.toCharArray()) {
            if (indexs[(int) c] == -1)
                indexs[(int) c] = index;
            else if (indexs[(int) c] >= 0)
                indexs[(int) c] = -2;
            index++;
        }
        int min = Integer.MAX_VALUE;
        char result = '\0';
        for (int i = 0; i < indexs.length; i++) {
            if (indexs[i] >= 0 && indexs[i] < min) {
                result = (char) i;
                min = indexs[i];
            }
        }
        return result;
    }

    /**
     * 主程序调用
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new CharStatisticTest().getFirstAppearingOnce("google"));
    }
}
