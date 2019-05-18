package cn.xidian.algorithm.leetcode;

import java.util.Stack;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: MyQueueByStackTest
 * @description: 用栈来模拟实现队列
 * @date 2019-05-18 16:36
 */
public class MyQueueByStackTest {

    private Stack<Integer> first;
    private Stack<Integer> second;

    /** Initialize your data structure here. */
    public MyQueueByStackTest() {
        first = new Stack<>();
        second = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        first.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!second.isEmpty()) {
            return second.pop();
        }
        while (!first.isEmpty()) {
            second.push(first.pop());
        }
        return second.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (!second.isEmpty()) {
            return second.peek();
        }
        while (!first.isEmpty()) {
            second.push(first.pop());
        }
        return second.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return first.isEmpty() && second.isEmpty();
    }
}
