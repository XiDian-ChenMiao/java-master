package cn.xidian.algorithm.guigu;

import cn.xidian.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：链表分区：给定一个链表和一个值，将链表一分为二，即将小于x的节点放在大于或者等于x的节点前，并保持原先的节点次序相对不变
 * 创建作者：陈苗
 * 创建时间：2017/7/28 19:33
 */
public class LinkPartitionTest {

    public LinkNode<Integer> partition(LinkNode<Integer> node, int x) {
        LinkNode<Integer> head = new LinkNode<>(0, null);
        LinkNode<Integer> priot = new LinkNode<>(x, null);
        LinkNode<Integer> first = head, second = priot, cur = node;
        while (cur != null) {
            LinkNode<Integer> next = cur.getNext();
            if (cur.getData() < x) {
                first.setNext(cur);
                first = cur;
            } else {
                second.setNext(cur);
                second = cur;
            }
        }
        first.setNext(second.getNext());
        return head.getNext();
    }
}
