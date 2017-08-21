package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 文件描述：二叉树的最小深度
 * 创建作者：陈苗
 * 创建时间：2017/8/21 16:35
 */
public class MinimumDepthOfBinaryTreeTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        List<Integer> result = new ArrayList<>();
        result.add(Integer.MAX_VALUE);
        Stack<TreeNode> path = new Stack<>();
        getMinDepth(root, path, result);
        return result.get(0);
    }

    private void getMinDepth(TreeNode root, Stack<TreeNode> path, List<Integer> result) {
        path.push(root);
        if (root.left == null && root.right == null) {
            if (path.size() < result.get(0))
                result.set(0, path.size());
        }
        if (root.left != null)
            getMinDepth(root.left, path, result);
        if (root.right != null)
            getMinDepth(root.right, path, result);
        path.pop();
    }
}
