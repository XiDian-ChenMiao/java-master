package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：判断一个树是否为平衡二叉树
 * 创建作者：陈苗
 * 创建时间：2017/8/21 15:58
 */
public class IsBalancedTreeTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        int left = getHeight(root.left);
        if (left == -1)
            return -1;
        int right = getHeight(root.right);
        if (right == -1)
            return -1;
        if (Math.abs(left - right) > 1)
            return -1;
        return left > right ? left + 1 : right + 1;
    }
}
