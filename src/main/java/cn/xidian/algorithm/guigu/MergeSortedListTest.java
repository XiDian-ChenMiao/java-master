package cn.xidian.algorithm.guigu;

import cn.xidian.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：合并两个有序链表
 * 创建作者：陈苗
 * 创建时间：2017/7/27 20:10
 */
public class MergeSortedListTest {
    /**
     * 合并函数
     * @param one
     * @param two
     * @return
     */
    public LinkNode merge(LinkNode<Integer> one, LinkNode<Integer> two) {
        LinkNode<Integer> head = new LinkNode<>(0, null);
        LinkNode<Integer> cur = head;
        while (one != null && two != null) {
            if (one.getData() < two.getData()) {
                cur.setNext(one);
                one = one.getNext();
            } else {
                cur.setNext(two);
                two = two.getNext();
            }
            cur = cur.getNext();
        }
        if (one != null)
            cur.setNext(one);
        if (two != null)
            cur.setNext(two);
        return head.getNext();
    }
}
