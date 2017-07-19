package cn.algorithm.leetcode;

/**
 * 文件描述：在主串中寻找子串出现的位置
 * 创建作者：陈苗
 * 创建时间：2016/10/21 10:22
 */
public class ImplementStrStrTest {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null)
            return -1;
        if (haystack.length() < needle.length())
            return -1;
        if (haystack.length() == 0 && needle.length() == 0)
            return 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).compareTo(needle) == 0)
                return i;
        }
        return -1;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String haystack = "Daqinzhidi-ChenMiao";
        String needle = "Chen";
        System.out.printf("串%s在串%s中出现的位置为：%d", haystack, needle, new ImplementStrStrTest().strStr(haystack, needle));
    }
}
