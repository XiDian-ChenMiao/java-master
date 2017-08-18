package cn.xidian.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 文件描述：判断给定的一个二叉树是否自对称
 * 创建作者：陈苗
 * 创建时间：2017/8/18 20:31
 */
public class SymmetricTreeTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        Queue<TreeNode> left = new LinkedList<TreeNode>();
        Queue<TreeNode> right = new LinkedList<TreeNode>();
        left.add(root.left);
        right.add(root.right);
        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode leftNode = left.poll();
            TreeNode rightNode = right.poll();
            if (leftNode == null && rightNode == null)
                continue;
            if (leftNode == null || rightNode == null)
                return false;
            if (leftNode.val != rightNode.val)
                return false;
            left.add(leftNode.left);
            left.add(leftNode.right);
            right.add(rightNode.right);
            right.add(rightNode.left);
        }
        return left.isEmpty() && right.isEmpty();
    }
}
