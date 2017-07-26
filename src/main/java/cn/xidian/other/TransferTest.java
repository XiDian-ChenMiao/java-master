package cn.xidian.other;

import java.util.Scanner;

/**
 * 文件描述：十六进制向十进制的转化
 * 创建作者：陈苗
 * 创建时间：2016/9/14 20:44
 */
public class TransferTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String param = scanner.next();
            System.out.println(Integer.parseInt(param.toUpperCase().substring(2), 16));
        }
    }
}
