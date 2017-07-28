package cn.xidian.algorithm.guigu;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：将二叉查找树转为有序双向循环链表
 * 创建作者：陈苗
 * 创建时间：2017/7/28 20:27
 */
public class TreeToDublyListTest {

    public BinaryTreeNode<Integer> treeToDoublyList(BinaryTreeNode<Integer> root) {
        BinaryTreeNode<Integer> pre = null, head = null;
        tree2list(root, pre, head);
        return head;
    }

    private void tree2list(BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> pre, BinaryTreeNode<Integer> head) {
        if (p == null)
            return;
        tree2list(p.getLeftChild(), pre, head);
        if (pre != null) {
            pre.setRightChild(p);/*前一个节点的右节点指向该节点*/
        } else {
            head = p;/*如果pre未被初始化，则此节点为链表的头*/
        }
        BinaryTreeNode<Integer> right = p.getRightChild();
        head.setLeftChild(p);/*将首尾连接，建立循环关系*/
        p.setRightChild(head);
        pre = p;/*在进入下一个节点之前，更新pre节点*/
        tree2list(right, pre, head);
    }
}
