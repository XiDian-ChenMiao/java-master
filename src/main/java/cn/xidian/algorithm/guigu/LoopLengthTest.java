package cn.xidian.algorithm.guigu;

import cn.xidian.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：获取环长度
 * 创建作者：陈苗
 * 创建时间：2017/7/27 20:21
 */
public class LoopLengthTest {

    public int getLoopLength(LinkNode<Integer> head) {
        LinkNode<Integer> slow = head;
        if (slow == null || slow.getNext() == null)
            return 0;
        LinkNode<Integer> fast = slow.getNext().getNext();
        while (fast != null && fast.getNext() != null) {
            if (slow == fast)
                return this.getLength(slow);
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return 0;
    }

    private int getLength(LinkNode<Integer> slow) {
        int length = 1;
        LinkNode<Integer> cur = slow;
        while (cur.getNext() != slow) {
            length++;
            cur = cur.getNext();
        }
        return length;
    }
}
