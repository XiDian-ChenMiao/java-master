package cn.xidian.algorithm.sordoffer;

import cn.xidian.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：查找链表中倒数第k个节点
 * 创建作者：陈苗
 * 创建时间：2016年6月2日 16:16
 */
public class FindBehindKthNodeTest {
    private LinkNode<Integer> pHead;

    /**
     * 构造函数
     *
     * @param pHead 链表头结点
     */
    public FindBehindKthNodeTest(LinkNode<Integer> pHead) {
        this.pHead = pHead;
    }

    /**
     * 获取倒数第k个节点的函数
     *
     * @param k
     * @return 查找到的节点
     */
    public LinkNode<Integer> getBehindKthNode(int k) {
        if (pHead == null || k <= 0)
            return null;
        LinkNode<Integer> nodeAhead = pHead;
        LinkNode<Integer> nodeBehind = pHead;
        for (int i = 0; i < k - 1; i++) {
            if (nodeAhead.getNext() != null)
                nodeAhead = nodeBehind.getNext();
            else
                return null;//防止输入的数字长度大于实际链表的长度
        }
        while (nodeAhead.getNext() != null) {
            nodeAhead = nodeAhead.getNext();
            nodeBehind = nodeBehind.getNext();
        }
        return nodeBehind;
    }

    /**
     * 主函数
     *
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

        FindBehindKthNodeTest test = new FindBehindKthNodeTest(head);
        LinkNode<Integer> result = test.getBehindKthNode(2);
        if (result != null)
            System.out.println("倒数第二个节点的数据为：" + result.getData());
    }
}
