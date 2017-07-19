package cn.algorithm.leetcode;

/**
 * 文件描述：字符串转数字
 * 创建作者：陈苗
 * 创建时间：2016/7/13 14:15
 */
public class StringToIntegerTest {
    /**
     * 字符串转数字的调用接口
     * @param str 给定的字符串
     * @return 转化后的数字
     */
    public int myAtoi(String str) {
        if (str == null || str.length() == 0)
            return 0;
        String content = str.trim();
        if ("".equals(content))
            return 0;
        char[] data = content.toCharArray();
        int flag,start = 0,sum = 0,previous,value,posCount = 0,nagCount = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '+' || data[i] == '-') {
                if (data[i] == '+')
                    posCount++;
                if (data[i] == '-')
                    nagCount++;
                start++;
                continue;
            }
            break;
        }
        if ((posCount == 0 && nagCount == 0) || (posCount == 1 && nagCount == 0))
            flag = 1;
        else if (posCount == 0 && nagCount == 1)
            flag = -1;
        else
            return 0;
        for (int i = start; i < data.length; i++) {
            if (data[i] < '0' || data[i] > '9')
                break;
            previous = sum;
            value = data[i] - '0';
            sum = sum * 10 + value;
            if (sum / 10 != previous)
                return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return sum * flag;
    }

    /**
     * 主程序
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new StringToIntegerTest().myAtoi("  +0012a34"));
    }
}
