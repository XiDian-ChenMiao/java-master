package cn.xidian.algorithm.course;

/**
 * 文件描述：字符串以数字结果相加
 * 创建作者：陈苗
 * 创建时间：2017/8/11 10:38
 */
public class StringsAddTest {
    public String add(String one, String two) {
        int sum = 0, flag = 0;
        StringBuilder result = new StringBuilder("");
        int i, j;
        for (i = one.length() - 1, j = two.length() - 1; i >= 0 && j >= 0; i--, j--) {
            sum = (one.charAt(i) - '0') + (two.charAt(j) - '0') + flag;
            flag = sum / 10;
            sum = sum % 10;
            result.append(sum);
        }
        for (; i >= 0; i--) {
            sum = (one.charAt(i) - '0') + flag;
            flag = sum / 10;
            sum = sum % 10;
            result.append(sum);
        }
        for (; j >= 0; j--) {
            sum = (two.charAt(j) - '0') + flag;
            flag = sum / 10;
            sum = sum % 10;
            result.append(sum);
        }
        if (flag == 1)
            result.append(1);
        return result.reverse().toString();
    }
}
