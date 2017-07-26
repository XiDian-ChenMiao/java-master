package cn.xidian.algorithm;

import java.util.Scanner;

/**
 * 文件描述：华为机试题
 * 创建作者：陈苗
 * 创建时间：2016/7/21 15:02
 */
public class HWGetPotNumber {
    public static int getPotNumber(int empty) {
        int sum = 0, rest;
        while(empty >= 3) {
            rest = empty % 3;
            sum += empty / 3;
            empty = empty / 3 + rest;
        }
        if (empty == 2) {
            sum += 1;
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int casevalue;
        while (scanner.hasNext()) {
            casevalue = Integer.parseInt(scanner.nextLine());
            if (casevalue == 0)
                break;
            System.out.println(HWGetPotNumber.getPotNumber(casevalue));
        }
    }
}
