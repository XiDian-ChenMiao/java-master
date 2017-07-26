package cn.xidian.algorithm.sordoffer;

import cn.xidian.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：在O（1）时间要求下删除链表中指定的节点
 * 创建作者：陈苗
 * 创建时间：2016年6月2日 15:00
 */
public class DeleteLinkNodeTest {
    private LinkNode<Integer> pHead;

    /**
     * 构造函数
     * @param pHead 链表头结点
     */
    public DeleteLinkNodeTest(LinkNode<Integer> pHead) {
        this.pHead = pHead;
    }

    /**
     * 删除指定节点的函数
     * @param node 待删除的节点
     */
    public void delete(LinkNode<Integer> node) {
        if (pHead == null || node == null)
            return;
        if (node.getNext() != null) {//如果待删除节点不是尾节点
            LinkNode<Integer> nodeNext = node.getNext();
            node.setData(nodeNext.getData());
            node.setNext(nodeNext.getNext());
            nodeNext = null;
        } else if (pHead == node) {//如果待删除节点为头结点
            pHead = null;
            node = null;
        } else {//链表中有多个节点，待删除节点为尾节点
            LinkNode<Integer> startNode = pHead;
            while (startNode.getNext() != node)
                startNode = startNode.getNext();
            startNode.setNext(null);
            node = null;
        }
    }

    /**
     * 删除单链表中倒数第k个节点，要求时间复杂度为绝对O（n）
     * @param pHead
     * @param k
     */
    private int count = 0;
    public void deleteKthNode(LinkNode<Integer> pHead, LinkNode<Integer> pHNext, int k) {
        if (k == 0)
            return;
        LinkNode<Integer> pNode = pHead;
        if (pHNext != null) {
            deleteKthNode(pNode.getNext(), pHNext.getNext(), k);
        }
        if (count++ == k) {
            pNode.setNext(pHNext.getNext());
        }
    }

    /**
     * 打印链表数据
     */
    public void print() {
        if (pHead != null) {
            LinkNode<Integer> node = pHead;
            while (node != null) {
                System.out.print(node.getData() + "\t");
                node = node.getNext();
            }
        }
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        LinkNode<Integer> pHead = new LinkNode<Integer>(0,null);
        LinkNode<Integer> node1 = new LinkNode<Integer>(2,null);
        LinkNode<Integer> node2 = new LinkNode<Integer>(5,null);
        pHead.setNext(node1);
        node1.setNext(node2);

        DeleteLinkNodeTest test = new DeleteLinkNodeTest(pHead);
        test.deleteKthNode(pHead, pHead.getNext(), 4);
        test.print();
    }
}
