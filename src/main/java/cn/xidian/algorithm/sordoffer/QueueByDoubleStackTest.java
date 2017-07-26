package cn.xidian.algorithm.sordoffer;

import cn.xidian.algorithm.sordoffer.common.Queue;

/**
 * 文件描述：通过两个栈来实现队列
 * 创建作者：陈苗
 * 创建时间：2016年6月1日 15:05
 */
public class QueueByDoubleStackTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(2);
        queue.enqueue(3);
        try {
            int headValue = queue.dequeue();
            System.out.println("队首元素为：" + headValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
