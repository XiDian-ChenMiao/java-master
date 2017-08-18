package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：判断给定的二叉树是否为相同的二叉树
 * 创建作者：陈苗
 * 创建时间：2017/8/18 20:02
 */
public class IsSameTreeTest {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null && q != null)
            return false;
        else if (p != null && q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
