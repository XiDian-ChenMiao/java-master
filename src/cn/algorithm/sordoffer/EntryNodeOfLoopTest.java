package cn.algorithm.sordoffer;

import cn.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：链表中环的入口节点
 * 创建作者：陈苗
 * 创建时间：2017/2/21 20:44
 */
public class EntryNodeOfLoopTest {
    /**
     * 从链表中查找快慢指针相遇的节点
     * @param head
     * @return
     */
    private LinkNode<Integer> getMeeting(LinkNode<Integer> head) {
        if (head == null)
            return null;
        LinkNode<Integer> slow = head.getNext();
        if (slow == null)
            return null;
        LinkNode<Integer> fast = slow.getNext();
        while (fast != null && slow != null) {
            if (fast == slow)
                return fast;
            slow = slow.getNext();
            fast = fast.getNext();
            if (fast != null)
                fast = fast.getNext();
        }
        return null;
    }

    /**
     * 寻找环中的入口节点
     * @param head
     * @return
     */
    public LinkNode<Integer> entryNodeOfLoop(LinkNode<Integer> head) {
        LinkNode<Integer> meetingNode = getMeeting(head);
        if (meetingNode == null)
            return null;
        int loop = 1;
        LinkNode pNode = meetingNode;
        while (pNode.getNext() != meetingNode) {
            pNode = pNode.getNext();
            ++loop;
        }
        pNode = head;
        for (int i = 0; i < loop; i++) {
            pNode = pNode.getNext();
        }
        LinkNode<Integer> other = head;
        while (pNode != other) {
            other = other.getNext();
            pNode = pNode.getNext();
        }
        return pNode;
    }

    /**
     * 主程序调用
     * @param args
     */
    public static void main(String[] args) {
        LinkNode<Integer> one = new LinkNode<Integer>(1, null);
        LinkNode<Integer> two = new LinkNode<Integer>(2, null);
        LinkNode<Integer> three = new LinkNode<Integer>(3, null);
        LinkNode<Integer> four = new LinkNode<Integer>(4, null);
        LinkNode<Integer> five = new LinkNode<Integer>(5, null);

        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(three);

        System.out.println(new EntryNodeOfLoopTest().entryNodeOfLoop(one).getData());
    }
}
