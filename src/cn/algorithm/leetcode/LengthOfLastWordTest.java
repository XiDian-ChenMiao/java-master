package cn.algorithm.leetcode;

import java.util.Arrays;

/**
 * 文件描述：最后一个单词的长度
 * 创建作者：陈苗
 * 创建时间：2016/11/29 15:45
 */
public class LengthOfLastWordTest {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0)
            return 0;
        if (!s.trim().contains(" "))
            return s.trim().length();
        int count = 0;
        char[] datas = s.trim().toCharArray();
        for (int i = datas.length - 1; i >= 0; i--) {
            if (datas[i] != ' ') {
                count++;
            }
            else
                break;
        }
        return count;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String data = "b a ";
        System.out.println(new LengthOfLastWordTest().lengthOfLastWord(data));
    }
}
