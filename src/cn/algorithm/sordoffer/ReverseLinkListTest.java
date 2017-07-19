package cn.algorithm.sordoffer;

import cn.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：反转链表测试
 * 创建作者：陈苗
 * 创建时间：2016年6月2日 16:42
 */
public class ReverseLinkListTest {
    private LinkNode<Integer> pHead;

    /**
     * 构造函数
     * @param pHead 链表头结点
     */
    public ReverseLinkListTest(LinkNode<Integer> pHead) {
        this.pHead = pHead;
    }

    /**
     * 反转函数
     * @return 反转之后的头结点
     */
    public LinkNode<Integer> reverse() {
        if (pHead == null)
            return null;
        else if (pHead.getNext() == null)//如果只有一个节点，则直接返回当前链表的头结点
            return pHead;
        LinkNode<Integer> pReversedHead = null;//反转之后的链表的头结点
        LinkNode<Integer> node = pHead;
        LinkNode<Integer> preNode = null;
        while (node != null) {
            LinkNode<Integer> nextNode = node.getNext();
            if (nextNode != null)
                pReversedHead = nextNode;
            node.setNext(preNode);
            preNode = node;
            node = nextNode;
        }
        return pReversedHead;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int i = 1;
        LinkNode<Integer> head = new LinkNode<Integer>(0, null);
        LinkNode<Integer> node = head;
        while (i <= 5) {
            LinkNode<Integer> temp = new LinkNode<Integer>(Integer.valueOf(i), null);
            node.setNext(temp);
            node = temp;
            i++;
        }//创建链表成功

        ReverseLinkListTest test = new ReverseLinkListTest(head);
        LinkNode<Integer> result = test.reverse();
        while (result != null) {
            System.out.print(result.getData() + " ");
            result = result.getNext();
        }
    }
}
