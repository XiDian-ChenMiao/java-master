package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：将二叉树转换为原址上通过
 * 创建作者：陈苗
 * 创建时间：2017/8/21 18:06
 */
public class FlattenTreeToLinkedListTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode prev = null;

    /**
     * 外部调用的函数，采用递归方式倒着建立链表
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
