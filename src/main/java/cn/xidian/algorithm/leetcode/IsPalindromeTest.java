package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：回文数的判断
 * 创建作者：陈苗
 * 创建时间：2016/7/13 17:39
 */
public class IsPalindromeTest {
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        if(x / 10 == 0)/*如果为个位数，则判定其为回文数*/
            return true;
        int data = Math.abs(x);
        int sum = 0,previous;
        while (data != 0) {
            previous = sum;
            sum = sum * 10 + data % 10;
            if (previous != sum / 10)
                return false;
            data = data / 10;
        }
        if (x == sum)
            return true;
        return false;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new IsPalindromeTest().isPalindrome(-2147483648));
    }
}
