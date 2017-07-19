package cn.algorithm.leetcode;

/**
 * 文件描述：二进制相加
 * 创建作者：陈苗
 * 创建时间：2016/12/4 19:47
 */
public class AddBinaryTest {
    /**
     * 相加外部调用函数
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        if (a == null || b == null)
            return null;
        if (a.length() == 0)
            return b;
        if (b.length() == 0)
            return a;
        StringBuilder result = new StringBuilder();

        int difference = a.length() >= b.length() ? a.length() - b.length() : b.length() - a.length();
        StringBuilder addZero = new StringBuilder();
        for (int i = 0; i < difference; i++) {
            addZero.append('0');
        }
        if (a.length() >= b.length()) {
            b = addZero.append(b).toString();
        } else {
            a = addZero.append(a).toString();
        }
        int carry = 0, cur_carry, index = addZero.length() - 1;
        while (index >= 0) {
            cur_carry = ((a.charAt(index) - '0') + (b.charAt(index) - '0') + carry) / 2;
            result.append(((a.charAt(index) - '0') + (b.charAt(index) - '0') + carry) % 2);
            index--;
            carry = cur_carry;
        }
        if (carry == 1) {
            result.append('1');
        }
        return result.reverse().toString();
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String added = "110", adder = "10";
        System.out.println(new AddBinaryTest().addBinary(added, adder));
    }
}
