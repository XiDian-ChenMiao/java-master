package cn.algorithm.sordoffer;

import java.util.Arrays;
import java.util.Stack;

/**
 * 文件描述：给定压栈顺序，判断一个序列是否为出栈顺序
 * 创建作者：陈苗
 * 创建时间：2017/2/18 20:40
 */
public class StackPopOrderTest {
    /**
     * 判断接口
     * @param push
     * @param pop
     * @return
     */
    public boolean isPopOrder(int[] push, int[] pop) {
        if (push == null || pop == null || push.length == 0 || pop.length == 0 || push.length != pop.length)
            return false;
        Stack<Integer> stack = new Stack<Integer>();
        int pop_index = 0, push_index = 0;
        while (pop_index < pop.length) {
            while (stack.empty() || stack.lastElement() != pop[pop_index]) {
                if (push_index == push.length)
                    break;
                stack.push(push[push_index++]);
            }
            if (stack.lastElement() != pop[pop_index])
                break;
            stack.pop();
            pop_index++;
        }
        if (stack.empty() && pop_index == pop.length)
            return true;
        return false;
    }

    /**
     * 主程序
     * @param args
     */
    public static void main(String[] args) {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop = {4, 5, 3, 1, 2};

        System.out.println(Arrays.toString(pop) + "是否为" + Arrays.toString(push) + "的出栈序列：" + new StackPopOrderTest().isPopOrder(push, pop));
    }
}
