package cn.xidian.algorithm.guigu;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 文件描述：给定一个二叉树，判断其是否为自身的镜像
 * 创建作者：陈苗
 * 创建时间：2017/7/27 19:48
 */
public class SymmetricTreeTest {

    /**
     * 通过广度优先遍历加队列判断是否为自身镜像
     * @param root
     * @return
     */
    public boolean isSymmetric(BinaryTreeNode<Integer> root) {
        if (root == null)
            return true;
        Queue<BinaryTreeNode<Integer>> left = new LinkedList<BinaryTreeNode<Integer>>();
        Queue<BinaryTreeNode<Integer>> right = new LinkedList<BinaryTreeNode<Integer>>();
        left.add(root.getLeftChild());
        right.add(root.getRightChild());
        while (!left.isEmpty() && !right.isEmpty()) {
            BinaryTreeNode<Integer> leftNode = left.poll();
            BinaryTreeNode<Integer> rightNode = right.poll();
            if (leftNode == null && rightNode == null)
                continue;
            if (leftNode == null || rightNode == null)
                return false;
            if (leftNode.getData() != rightNode.getData())
                return false;
            left.add(leftNode.getLeftChild());
            left.add(leftNode.getRightChild());

            right.add(rightNode.getRightChild());
            right.add(rightNode.getLeftChild());
        }
        return left.isEmpty() && right.isEmpty();
    }
}
