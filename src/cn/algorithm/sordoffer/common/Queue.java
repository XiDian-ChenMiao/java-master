package cn.algorithm.sordoffer.common;

import java.util.Stack;

/**
 * 文件描述：队列类
 * 创建作者：陈苗
 * 创建时间：2016年6月1日 15:06
 */
public class Queue<E> {
    private Stack<E> stack1;
    private Stack<E> stack2;

    /**
     * 构造函数
     */
    public Queue() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

    /**
     * 从队尾执行入队操作
     * @param element 要加入的元素
     */
    public void enqueue(E element) {
        stack1.push(element);
    }

    /**
     * 从队首执行出队操作
     * @return 队首元素
     * @throws Exception
     */
    public E dequeue() throws Exception{
        if(stack2.size() <= 0) {
            while (stack1.size() > 0) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.size() == 0)
            throw new Exception("队列为空");
        return stack2.pop();
    }
}
