package cn.algorithm.sordoffer;

import java.util.Arrays;

/**
 * 文件描述：以O（n）的时间复杂度替换字符串中的指定的字符
 * 创建作者：陈苗
 * 创建时间：2016年5月31日 14:34
 */
public class ReplaceElementTest {
    /**
     * 替换字符
     * @param original    原始字符数组
     * @param length      数组最大长度
     * @param replaceChar 需要被替换的字符
     * @param paddingChar 填充字符数组
     */
    public static void replace(char[] original, int length, char replaceChar, char[] paddingChar) {
        if (original == null || length <= 0)
            return;
        int originalLength = 0;//初始化原字符数组的长度
        int numberOfReplaceChar = 0;//初始化被替换的字符的个数
        int i = 0;
        while (original[i] != '\0') {
            ++originalLength;
            if (original[i] == replaceChar)
                ++numberOfReplaceChar;
            ++i;
        }
        int newLength = originalLength + (paddingChar.length - 1) * numberOfReplaceChar;//替换之后新字符数组的长度
        if (newLength > length)
            return;//如果赋予的字符数组所存储元素的最大长度不够填充字符填充之后的大小，则直接退出
        int indexOfOriginal = originalLength;
        int indexOfNew = newLength;
        while (indexOfOriginal >= 0 && indexOfNew > indexOfOriginal) {
            if (original[indexOfOriginal] == replaceChar) {
                for (int j = paddingChar.length - 1; j >= 0; --j) {
                    original[indexOfNew--] = paddingChar[j];
                }
            } else {
                original[indexOfNew--] = original[indexOfOriginal];
            }
            --indexOfOriginal;
        }
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        char[] init = Arrays.copyOf("DAQINZHIDI 陈苗".toCharArray(),50);
        ReplaceElementTest.replace(init,50,' ',new char[]{'%','2','0'});
        System.out.println("替换之后结果为：" + new String(init));
    }
}
