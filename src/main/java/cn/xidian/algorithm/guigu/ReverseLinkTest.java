package cn.xidian.algorithm.guigu;

import cn.xidian.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：反转链表
 * 创建作者：陈苗
 * 创建时间：2017/7/27 20:36
 */
public class ReverseLinkTest {

    public LinkNode<Integer> reverse(LinkNode<Integer> node) {
        LinkNode<Integer> head = new LinkNode<>(0, null);
        head.setNext(node);
        if (node == null)
            return null;
        LinkNode<Integer> cur = node.getNext();
        node.setNext(null);
        while (cur != null) {
            LinkNode next = cur.getNext();
            cur.setNext(head.getNext());
            head.setNext(cur);
            cur = next;
        }
        return head.getNext();
    }
}
