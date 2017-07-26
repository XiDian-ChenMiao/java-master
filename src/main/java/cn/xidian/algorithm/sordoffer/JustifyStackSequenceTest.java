package cn.xidian.algorithm.sordoffer;

import java.util.Stack;

/**
 * 文件描述：判断给定的栈的序列是否为已知栈的一个输出序列类
 * 创建作者：陈苗
 * 创建时间：2016年6月6日 16:47
 */
public class JustifyStackSequenceTest {
    private int[] dataPush;
    private int[] dataPop;

    /**
     * 构造函数
     * @param dataPush 已知的压栈序列
     * @param dataPop 给定的出栈序列
     */
    public JustifyStackSequenceTest(int[] dataPush, int[] dataPop) {
        this.dataPush = dataPush;
        this.dataPop = dataPop;
    }

    /**
     * 判断函数
     * @return 若是，则返回true；否则，返回false；
     */
    public boolean justify() {
        boolean isPossible = false;
        if (dataPop != null && dataPush != null && dataPush.length == dataPop.length) {
            Stack<Integer> stack = new Stack<Integer>();
            int length = dataPop.length;//栈数据的长度
            int pNextPop = 0;
            int pPop = 0;
            int pNextPush = 0;
            int pPush = 0;
            while (pNextPop - pPop < length) {
                while (stack.empty() || stack.lastElement() != dataPop[pNextPop]) {
                    if (pNextPush - pPush == length)
                        break;
                    stack.push(dataPush[pNextPush]);
                    ++pNextPush;
                }
                if (stack.lastElement() != dataPop[pNextPop])
                    break;
                stack.pop();
                ++pNextPop;
            }
            if (stack.empty() && pNextPop - pPop == length)
                isPossible = true;
        }
        return isPossible;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] dataPush = {1,2,3,4,5};
        int[] dataPop = {4,5,3,2,1};

        System.out.println("是否为输出序列：" + new JustifyStackSequenceTest(dataPush,dataPop).justify());
    }
}
