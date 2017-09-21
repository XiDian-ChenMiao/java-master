package cn.xidian.algorithm.leetcode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 文件描述：通过队列实现一个栈
 * 创建作者：陈苗
 * 创建时间：2017/9/20 16:50
 */
public class StackImplementByQueueTest {

    private Queue<Integer> queue;

    public StackImplementByQueueTest() {
        queue = new LinkedList<Integer>();
    }

    public void push(int x) {
        queue.add(x);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
