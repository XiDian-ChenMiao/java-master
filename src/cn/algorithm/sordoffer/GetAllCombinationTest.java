package cn.algorithm.sordoffer;

import java.util.Stack;

/**
 * 文件描述：获取给定字符序列的所有组合数
 * 创建作者：陈苗
 * 创建时间：2016年6月9日 22:02
 */
public class GetAllCombinationTest {
    private static int count = 1;

    /**
     * 获取组合数的接口函数
     *
     * @param data
     */
    public void combination(char[] data) {
        Stack<Character> vector = new Stack<Character>();
        int length = data.length;
        for (int i = 1; i <= length; i++)
            combination(data, 0, i, vector);
    }

    /**
     * 辅助计算组合数的函数
     *
     * @param data
     * @param number 组合中出现字符的个数
     * @param result 结果向量
     */
    public void combination(char[] data, int start, int number, Stack<Character> result) {
        if (number == 0) {
            System.out.print("第" + count++ + "个组合：");
            for (char c : result)
                System.out.print(c);
            System.out.println();
            return;
        }
        if (start == data.length)
            return;
        result.push(data[start]);
        combination(data, start + 1, number - 1, result);
        result.pop();
        combination(data, start + 1, number, result);
    }

    /**
     * 通过位运算获取给定序列的组合数
     * @param data
     */
    public void combinationByBitOperation(char[] data) {
        int length = data.length;
        for (int i = 1; i < (1 << length); i++) {
            printCombination(data,length,i);
        }
    }

    /**
     * 通过位运算的辅助函数
     * @param data 字符序列
     * @param length 序列长度
     * @param k 所有取值的可能
     */
    private void printCombination(char[] data,int length,int k) {
        System.out.print("第" + count++ + "个组合：");
        for (int i = 0; i < length; i++) {
            if ((k & (1 << i)) != 0x0)
                System.out.print(data[i]);
        }
        System.out.println();
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        GetAllCombinationTest test = new GetAllCombinationTest();
        char[] data = "abc".toCharArray();
        test.combination(data);
        test.combinationByBitOperation(data);
    }
}
