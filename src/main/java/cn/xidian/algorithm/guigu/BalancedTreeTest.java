package cn.xidian.algorithm.guigu;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：判断给定的二叉树是否为平衡二叉树
 * 创建作者：陈苗
 * 创建时间：2017/7/27 19:39
 */
public class BalancedTreeTest {

    public boolean isBalanced(BinaryTreeNode<Integer> root) {
        return getHeight(root) != -1;
    }

    private int getHeight(BinaryTreeNode<Integer> root) {
        if (root == null)
            return 0;
        int left_height = getHeight(root.getLeftChild());
        if (left_height == -1)
            return -1;
        int right_height = getHeight(root.getRightChild());
        if (right_height == -1)
            return -1;
        if (Math.abs(left_height - right_height) > 1)
            return -1;
        return 1 + Math.max(left_height, right_height);
    }
}
