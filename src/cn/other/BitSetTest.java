package cn.other;

import java.util.BitSet;

/**
 * 文件描述：BitSet类学习测试
 * NOTE:
 *  flip：将指定索引处的位设置为其当前值的补码；
 *  cardinality：返回此BitSet中设置为true的位数；
 *  get：返回指定索引处的位值，结果为true或者false；
 *  intersects：如果指定的 BitSet 中有设置为 true 的位，并且在此 BitSet 中也将其设置为 true，则返回 ture；
 *  isEmpty：如果此 BitSet 中没有包含任何设置为 true 的位，则返回 ture；
 *  nextClearBit(int fromIndex)：返回第一个设置为 false 的位的索引，这发生在指定的起始索引或之后的索引上；
 *  nextSetBit(int fromIndex)：返回第一个设置为 true 的位的索引，这发生在指定的起始索引或之后的索引上；
 *  xor：对此位 set 和位 set 参数执行逻辑异或操作；
 * 创建作者：陈苗
 * 创建时间：2016/9/13 15:28
 */
public class BitSetTest {
    public static void main(String[] args) {
        BitSet s1 = new BitSet(32);
        s1.flip(2);
        BitSet s2 = new BitSet(32);
        s2.flip(3);
        System.out.printf("s1中位为1的个数为%d，s2中位为1的个数为%d\n", s1.cardinality(), s2.cardinality());
        s1.intersects(s2);
        System.out.println("s1中位为1的个数为" + s1.cardinality());
        System.out.println("s2是否为空：" + s2.isEmpty());
        System.out.println("s2长度为：" + s2.length());
        s1.xor(s2);
        System.out.println("s1异或s2：" + s1.toString());
    }
}
