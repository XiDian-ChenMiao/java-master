package cn.xidian.algorithm.course;

/**
 * 文件描述：KMP模式匹配算法
 * 创建作者：陈苗
 * 创建时间：2016/10/22 19:56
 */
public class KMPAlgorithmTest {
    /**
     * 求模式串在主串中第一次出现的位置
     * @param main 主串
     * @param pattern 模式串
     * @return 如果模式串在主串中存在，则返回第一次出现的位置；如果不存在，则返回-1
     */
    public int indexOf(String main, String pattern) {
        if (main == null || pattern == null || main.length() == 0 || pattern.length() == 0)
            return -1;
        if (main.length() < pattern.length())
            return -1;
        int[] next = getNext(pattern);
        char[] mains = main.toCharArray();
        char[] ps = pattern.toCharArray();
        int q = 0;
        for (int i = 0; i < mains.length; i++) {
            while (q > 0 && ps[q] != mains[i])
                q = next[q - 1];
            if (ps[q] == mains[i])
                ++q;
            if (q == ps.length)
                return i - ps.length + 1;
        }
        return -1;
    }

    /**
     * 通过模式串获取next数组
     * @param pattern
     * @return
     */
    private int[] getNext(String pattern) {
        char[] ps = pattern.toCharArray();
        int[] next = new int[ps.length];
        int length = 0;/*最大前后缀长度*/
        next[0] = 0;/*模版字符串的第一个字符的最大前后缀长度为0*/
        for (int i = 1; i < ps.length; i++) {
            while (length > 0 && ps[length] != ps[i])
                length = next[length - 1];
            if (ps[length] == ps[i])
                ++length;
            next[i] = length;
        }
        return next;
    }
    /**
     * 主程序
     * @param args
     */
    public static void main(String[] args) {
        String main = "daqinzhidi";
        String pattern = "qin";
        System.out.println(new KMPAlgorithmTest().indexOf(main, pattern));
    }
}
