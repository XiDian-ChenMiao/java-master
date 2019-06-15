package cn.xidian.algorithm.course;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: Minium2PowerTest
 * @description: 求给定数的最小2的自然数幂
 * @date 2019-06-15 09:51
 */
public class Minium2PowerTest {
    private final static int MAXIMUM_CAPACITY = Integer.MAX_VALUE;

    public final int minium2power(int num) {
        int n = num - 1;/*防止给定的数字已经为2的幂次方*/
        n |= n >>> 1;/*保证结果n的前两位均为2*/
        n |= n >>> 2;/*保证结果n的前两位均为4*/
        n |= n >>> 4;/*保证结果n的前两位均为8*/
        n |= n >>> 8;/*保证结果n的前两位均为16*/
        n |= n >>> 16;/*保证结果n的前两位均为32*/
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Minium2PowerTest().minium2power(11));
    }
}
