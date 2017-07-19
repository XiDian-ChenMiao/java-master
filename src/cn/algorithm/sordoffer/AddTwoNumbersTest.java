package cn.algorithm.sordoffer;

/**
 * 文件描述：不使用加减乘除计算两数相加
 * 创建作者：陈苗
 * 创建时间：2016/10/23 13:43
 */
public class AddTwoNumbersTest {
    /**
     * 利用位操作实现两数相加
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        int sum, carry;
        do {
            sum = a ^ b;/*不考虑进位两数相加*/
            carry = (a & b) << 1;/*考虑进位*/
            a = sum;/*两数相加结果*/
            b = carry;
        } while (b != 0);/*直到不产生进位为止*/
        return a;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new AddTwoNumbersTest().add(5, 17));
    }
}
