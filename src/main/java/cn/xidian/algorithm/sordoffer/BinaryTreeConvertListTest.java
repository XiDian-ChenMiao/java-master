package cn.xidian.algorithm.sordoffer;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：二叉搜索树转双向链表
 * 创建作者：陈苗
 * 创建时间：2017/2/20 21:01
 */
public class BinaryTreeConvertListTest {
    BinaryTreeNode<Integer> head;
    BinaryTreeNode<Integer> back;

    /**
     * 采用中序遍历的方法进行二叉搜索树的遍历，同时创建链表
     *
     * @param root
     */
    public void visit(BinaryTreeNode<Integer> root) {
        if (root == null)
            return;
        visit(root.getLeftChild());
        createList(root);
        visit(root.getRightChild());
    }

    /**
     * 构造链表
     *
     * @param cur
     */
    private void createList(BinaryTreeNode<Integer> cur) {
        cur.setLeftChild(back);
        if (back != null)
            back.setRightChild(cur);
        else
            head = cur;
        back = cur;
    }

    /**
     * 主程序调用
     *
     * @param args
     */
    public static void main(String[] args) {
        BinaryTreeNode<Integer> four = new BinaryTreeNode<Integer>(4, null, null);
        BinaryTreeNode<Integer> eight = new BinaryTreeNode<Integer>(8, null, null);
        BinaryTreeNode<Integer> twelve = new BinaryTreeNode<Integer>(12, null, null);
        BinaryTreeNode<Integer> sixteen = new BinaryTreeNode<Integer>(16, null, null);
        BinaryTreeNode<Integer> six = new BinaryTreeNode<Integer>(6, four, eight);
        BinaryTreeNode<Integer> fourteen = new BinaryTreeNode<Integer>(14, twelve, sixteen);
        BinaryTreeNode<Integer> ten = new BinaryTreeNode<Integer>(10, six, fourteen);

        BinaryTreeConvertListTest test = new BinaryTreeConvertListTest();
        test.visit(ten);
        BinaryTreeNode<Integer> result = test.head;
        while (result != null) {
            System.out.print(result.getData() + "\t");
            result = result.getRightChild();
        }
    }
}
