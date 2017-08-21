package cn.xidian.algorithm.leetcode;

import java.util.*;

/**
 * 文件描述：自底向上层序遍历二叉树
 * 创建作者：陈苗
 * 创建时间：2017/8/21 10:23
 */
public class BinaryTreeLevelOrderTraversalIITest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Stack<List<Integer>> stack = new Stack<List<Integer>>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;
        int level = 0;
        List<Integer> temp;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            temp = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            stack.add(temp);
        }
        while (!stack.isEmpty())
            result.add(stack.pop());
        return result;
    }
}
