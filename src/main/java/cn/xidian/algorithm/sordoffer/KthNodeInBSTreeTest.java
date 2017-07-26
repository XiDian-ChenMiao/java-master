package cn.xidian.algorithm.sordoffer;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：查找二叉搜索树中的第K个节点
 * 创建作者：陈苗
 * 创建时间：2017/2/22 17:50
 */
public class KthNodeInBSTreeTest {
    private class Number {
        int k;
    }
    /**
     * 查找二叉搜索树中的第K个节点外部调用函数
     * @param root
     * @param k
     * @return
     */
    public BinaryTreeNode<Integer> kthNode(BinaryTreeNode<Integer> root, int k) {
        if (root == null || k == 0)
            return null;
        Number number = new Number();
        number.k = k;
        return kthNodeCore(root, number);
    }

    /**
     * 查找核心算法
     * @param root
     * @return
     */
    private BinaryTreeNode<Integer> kthNodeCore(BinaryTreeNode<Integer> root, Number n) {
        BinaryTreeNode<Integer> target = null;
        if (root.getLeftChild() != null)
            target = kthNodeCore(root.getLeftChild(), n);
        if (target == null) {
            if (n.k == 1)
                target = root;
            --n.k;
        }
        if (target == null && root.getRightChild() != null)
            target = kthNodeCore(root.getRightChild(), n);
        return target;
    }

    /**
     * 主程序调用
     * @param args
     */
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(2, null, null);
        BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>(1, null, null);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>(3, null, null);
        root.setLeftChild(left);
        root.setRightChild(right);

        System.out.println(new KthNodeInBSTreeTest().kthNode(root, 3).getData());
    }
}
