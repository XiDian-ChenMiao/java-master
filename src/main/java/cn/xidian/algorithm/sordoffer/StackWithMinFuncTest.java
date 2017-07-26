package cn.xidian.algorithm.sordoffer;

import java.util.Stack;

/**
 * 文件描述：拥有从栈里面以O（1）复杂度取出最小元素的类，且泛型类必须实现比较接口
 * 创建作者：陈苗
 * 创建时间：2016年6月6日 15:55
 */
@SuppressWarnings("rawtypes")
public class StackWithMinFuncTest<E extends Comparable> {
    private Stack<E> dataStack;//数据栈
    private Stack<E> assistStack;//辅助栈，用于存储最小元素

    /**
     * 构造函数
     */
    public StackWithMinFuncTest() {
        dataStack = new Stack<E>();
        assistStack = new Stack<E>();
    }

    /**
     * 压栈操作
     *
     * @param element 需要压栈的元素
     */
    @SuppressWarnings("unchecked")
	public void push(E element) {
        dataStack.push(element);
        if (assistStack.size() == 0)
            assistStack.push(element);
        else {
            E top = assistStack.lastElement();
            if (element.compareTo(top) > 0)
                assistStack.push(top);
            else
                assistStack.push(element);
        }
    }

    /**
     * 弹栈操作
     *
     * @return 弹栈的元素
     */
    public E pop() throws StackOverflowError{
        if (assistStack.size() > 0) {
            assistStack.pop();
            return dataStack.pop();
        } else {
            throw new StackOverflowError("栈已空");
        }
    }

    /**
     * 取出最小元素
     *
     * @return
     */
    public E min() throws StackOverflowError {
        if (assistStack.size() > 0)
            return assistStack.lastElement();
        else
            throw new StackOverflowError("栈已空");
    }

    /**
     * 主函数
     *
     * @param args
     */
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        StackWithMinFuncTest test = new StackWithMinFuncTest();
        test.push(-9);
        test.push(5);
        test.push(-3);
        test.push(2);
        test.push(-2);

        System.out.println("栈中最小元素为：" + test.min());
    }
}
