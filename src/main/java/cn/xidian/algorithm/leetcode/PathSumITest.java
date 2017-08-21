package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：在给定的二叉树中查找是否存在从根节点到叶子节点的路径上的值满足和为给定值
 * 创建作者：陈苗
 * 创建时间：2017/8/21 17:30
 */
public class PathSumITest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && root.val == sum)
            return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
