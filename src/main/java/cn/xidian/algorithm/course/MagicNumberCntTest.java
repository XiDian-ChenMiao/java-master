package cn.xidian.algorithm.course;

import java.util.Scanner;

/**
 * 文件描述：求给定区间内的神奇数的个数
 * 创建作者：陈苗
 * 创建时间：2017/5/23 18:20
 */
public class MagicNumberCntTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int from = sc.nextInt();
        int end = sc.nextInt();

        int cnt = 0;
        for (int i = from; i <= end; i++) {
            String valueStr = i + "";
            if (valueStr.length() >= 2) {
                if (isMagic(valueStr))
                    ++cnt;
            }
        }

        System.out.println(cnt);
    }

    public static boolean isMagic(String value) {
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            for (int j = 0; j < chars.length; ++j) {
                if (j != i) {
                    String str = chars[i] + "" + chars[j];
                    if (chars[i] != '0' && Integer.parseInt(str) % 2 == 1 && isPrime(Integer.parseInt(str))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean isPrime(int value) {
        int mid = value / 2;
        for (int i = 2; i <= mid; i++) {
            if (value % i == 0)
                return false;
        }
        return true;
    }
}
