package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：从1到N之间的数中找出1出现的次数
 * 创建作者：陈苗
 * 创建时间：2016/8/6 17:09
 */
public class NumberOf1Between1AndNTest {
    /**
     * 寻找从1到N的N个数中1出现的次数
     * @param n
     * @return
     */
    public int numberOf1Between1AndN(int n) {
        if (n <= 0)
            return 0;
        return numberOf1(n + "");
    }

    private int numberOf1(String number) {
        if (number == null)
            return 0;
        char[] digitNumber = number.toCharArray();
        if (digitNumber[0] < '0' || digitNumber[0] > '9')
            return 0;
        int zero = '0';
        int firstValue = digitNumber[0];
        int first = firstValue - zero;
        if (digitNumber.length == 1 && first == 0)
            return 0;
        if (digitNumber.length == 1 && first > 0)
            return 1;
        int numberFirstDigit = 0;
        if (first > 1)/*如果最高位大于1*/
            numberFirstDigit = powerBase10(digitNumber.length - 1);
        else if (first == 1)/*如果最高位等于1*/
            numberFirstDigit = Integer.parseInt(number) - powerBase10(digitNumber.length - 1) + 1;
        /*变量值代表共将数据分为多少段（first），并且从除最高位之外的其余位中任取一位为1，其余位从0到9任取*/
        int numberOtherDigits = first * (digitNumber.length - 1) * powerBase10(digitNumber.length - 2);
        int numberRescursive = numberOf1(Integer.parseInt(number) / 10 + "");/*递归求解从1到除最高位之外的数据中1出现次数的结果*/
        System.out.println("最高位为：" + numberFirstDigit + ",其余位为：" + numberOtherDigits + ",递归为：" + numberRescursive);
        return numberFirstDigit + numberOtherDigits + numberRescursive;
    }

    private int powerBase10(int i) {
        int result = 1;
        for (int j = 0; j < i; j++) {
            result *= 10;
        }
        return result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new NumberOf1Between1AndNTest().numberOf1Between1AndN(22));
    }
}
