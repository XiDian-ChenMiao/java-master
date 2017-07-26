package cn.xidian.algorithm.sordoffer;

import cn.xidian.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：获取两个链表的第一个公共节点
 * 创建作者：陈苗
 * 创建时间：2016/9/16 15:36
 */
public class GetCommonNodeInLinkTest {
    /**
     * 获取两个链表的公共节点的外部调用
     * @param first
     * @param second
     * @return
     */
    public LinkNode<Integer> getFirstCommonNode(LinkNode<Integer> first, LinkNode<Integer> second) {
        if (first == null || second == null)
            return null;
        int first_length = getLinkLength(first);
        int second_length = getLinkLength(second);
        boolean first_bigger = first_length > second_length;
        if (first_bigger) {
            int more_than = first_length - second_length;
            int i = 0;
            while (i < more_than) {
                first = first.getNext();
                ++i;
            }
        } else {
            int more_than = second_length - first_length;
            int i = 0;
            while (i < more_than) {
                second = second.getNext();
                ++i;
            }
        }
        while (first != null && second != null) {
            if (first == second)
                return first;
            first = first.getNext();
            second = second.getNext();
        }
        return null;
    }

    /**
     * 获取链表长度的方法
     * @param link
     * @return
     */
    private int getLinkLength(LinkNode<Integer> link) {
        LinkNode<Integer> temp = link;
        int length = 0;
        if (temp != null) {
            while (temp != null) {
                ++length;
                temp = temp.getNext();
            }
            return length;
        }
        return 0;
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        LinkNode<Integer> node1 = new LinkNode<Integer>(15, null);
        LinkNode<Integer> node2 = new LinkNode<Integer>(10, null);
        LinkNode<Integer> node3 = new LinkNode<Integer>(12, null);
        LinkNode<Integer> node4 = new LinkNode<Integer>(11, null);
        node1.setNext(node2);
        node2.setNext(node3);
        node4.setNext(node3);
        LinkNode<Integer> common_node = new GetCommonNodeInLinkTest().getFirstCommonNode(node1, node4);
        System.out.println("两个链表的第一个公共节点为：" + common_node + "，节点值为：" + common_node.getData());
    }
}
