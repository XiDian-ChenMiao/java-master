package cn.xidian.algorithm.sordoffer;

import cn.xidian.algorithm.sordoffer.common.LinkNode;

import java.util.Stack;

/**
 * 文件描述：单向链表的反向输出
 * 创建作者：陈苗
 * 创建时间：2016年5月31日 15:38
 */
public class ReverseLinkListOutputTest {
    /**
     * 通过迭代反向输出单链表的内容
     *
     * @param headNode 单链表的头结点
     * @param <T>      元素类型
     */
    public static <T> void reverseOutput(LinkNode<T> headNode) {
        LinkNode<T> pNode = headNode;
        Stack<LinkNode<T>> stack = new Stack<LinkNode<T>>();
        while (pNode != null) {
            stack.push(pNode);
            pNode = pNode.getNext();
        }
        while (!stack.empty()) {
            pNode = stack.pop();
            System.out.print(pNode.getData() + " ");
        }
    }

    /**
     * 通过递归反向输出单链表的内容
     *
     * @param headNode 单链表的头结点
     * @param <T>      元素类型
     */
    public static <T> void reverseOutputByRecursion(LinkNode<T> headNode) {
        if (headNode != null) {
            if (headNode.getNext() != null)
                reverseOutputByRecursion(headNode.getNext());
            System.out.print(headNode.getData() + " ");
        }
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int i = 1;
        LinkNode<Integer> head = new LinkNode<Integer>(0,null);
        LinkNode<Integer> node = head;
        while(i <= 5) {
            LinkNode<Integer> temp = new LinkNode<Integer>(Integer.valueOf(i),null);
            node.setNext(temp);
            node = temp;
            i++;
        }//创建链表成功
        System.out.println("通过迭代形式反向输出链表为：");
        reverseOutput(head);
        System.out.println("\n通过递归形式反向输出链表为：");
        reverseOutputByRecursion(head);
    }
}
