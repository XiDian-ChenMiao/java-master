package cn.algorithm.sordoffer;

import cn.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：合并两个有序单链表
 * 创建作者：陈苗
 * 创建时间：2016年6月3日 15:00
 */
public class MergeSortedLinkTest {
    /**
     * 合并两个有序链表
     * @param pHead
     * @param qHead
     * @return 排好序的链表头结点
     */
    public static LinkNode<Integer> merge(LinkNode<Integer> pHead, LinkNode<Integer> qHead) {
        if (pHead == null)
            return qHead;
        else if (qHead == null)
            return pHead;
        LinkNode<Integer> result;
        if (pHead.getData() > qHead.getData()) {
            result = qHead;
            result.setNext(merge(pHead,qHead.getNext()));
        } else {
            result = pHead;
            result.setNext(merge(pHead.getNext(),qHead));
        }
        return result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int i = 1;
        LinkNode<Integer> phead = new LinkNode<Integer>(0, null);
        LinkNode<Integer> qhead = new LinkNode<Integer>(2, null);

        LinkNode<Integer> pNode = phead;
        LinkNode<Integer> qNode = qhead;
        while (i <= 3) {
            LinkNode<Integer> temp = new LinkNode<Integer>(Integer.valueOf(i), null);
            pNode.setNext(temp);
            pNode = temp;
            i++;
        }//创建p链表成功
        i = 3;
        while (i <= 5) {
            LinkNode<Integer> temp = new LinkNode<Integer>(Integer.valueOf(i), null);
            qNode.setNext(temp);
            qNode = temp;
            i++;
        }//创建q链表成功
        LinkNode<Integer> result = MergeSortedLinkTest.merge(phead,qhead);
        while (result != null) {
            System.out.print(result.getData() + " ");
            result = result.getNext();
        }
    }
}
