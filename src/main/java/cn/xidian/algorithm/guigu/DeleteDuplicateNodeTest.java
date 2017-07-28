package cn.xidian.algorithm.guigu;

import cn.xidian.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：链表去重
 * 创建作者：陈苗
 * 创建时间：2017/7/28 19:47
 */
public class DeleteDuplicateNodeTest {
    /**
     * 将重复节点删除直到保留一个版本
     * @param head
     * @return
     */
    public LinkNode<Integer> delete(LinkNode<Integer> head) {
        if (head == null)
            return head;
        LinkNode<Integer> pre = head, cur = head.getNext();
        while (cur != null) {
            if (pre.getData() != cur.getData())
                pre = pre.getNext();
            cur = cur.getNext();
            pre.setNext(cur);
        }
        return head;
    }

    /**
     * 删除所有重复节点版本
     * @param head
     * @return
     */
    public LinkNode<Integer> deleteD(LinkNode<Integer> head) {
        LinkNode<Integer> dummy = new LinkNode<>(0, null);
        LinkNode<Integer> tail = dummy, pre = head, cur = head;
        while (cur != null && cur.getNext() != null) {
            while (cur.getNext() != null && cur.getData() == cur.getNext().getData())
                cur = cur.getNext();
            if (pre == cur) {
                tail.setNext(pre);
                tail = tail.getNext();
            }
            pre = cur.getNext();
            cur = cur.getNext();
        }
        tail.setNext(cur);
        return dummy.getNext();
    }
}
