package cn.algorithm;

import java.util.Stack;

/**
 * 文件描述：给定两个数m和n，获取从1到m中所有数之和为n的可能组合
 * 创建作者：陈苗
 * 创建时间：2016年6月10日 17:10
 */
public class GetCombinationsBySumTest {
    private Stack<Integer> vector = new Stack<Integer>();//存储可能的结果容器
    private static int count = 1;
    public void findFactor(int sum,int n){
        if (n <= 0 || sum <= 0)
            return;//递归出口
        if (sum == n) {//输出找到的数
            System.out.print("第" + count++ + "种可能：");
            for (int data : vector)
                System.out.print(data + "+");
            System.out.println(n);
        }
        vector.push(n);
        findFactor(sum - n,n - 1);//将n放入容器中
        vector.pop();
        findFactor(sum,n - 1);//不将n放入容器中
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int sum = 8,n = 8;
        GetCombinationsBySumTest test = new GetCombinationsBySumTest();
        test.findFactor(sum,n);
    }
}
