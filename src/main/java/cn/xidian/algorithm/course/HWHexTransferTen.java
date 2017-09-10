package cn.xidian.algorithm.course;

/**
 * 文件描述：华为机试题，十六进制转十进制
 * 创建作者：陈苗
 * 创建时间：2016/7/21 15:09
 */
import java.util.Scanner;
public class HWHexTransferTen {
    public static String transfer(String hex) {
        if (hex.startsWith("0x") || hex.startsWith("0X")) {
            int length = hex.length();
            int sum = 0;
            int zero = '0';
            int Avalue = 'A';
            int charvalue = 0;
            for (int i = 2; i < length; i++) {
                if (hex.charAt(i) >= '0' && hex.charAt(i) <= '9')
                    charvalue = hex.charAt(i) - zero;
                else if (hex.charAt(i) >= 'A' && hex.charAt(i) <= 'F') {
                    charvalue = hex.charAt(i);
                    charvalue = charvalue - Avalue + 10;
                }
                sum = sum * 16 + charvalue;
            }
            return sum + "";
        }
        return null;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(HWHexTransferTen.transfer(scanner.nextLine()));
        }
    }
}
