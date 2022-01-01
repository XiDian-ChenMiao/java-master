package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: SingleValueTree
 * @description: LeetCode.965 单值二叉树判定
 * @date 8/14/21 10:16 PM
 */
public class SingleValueTreeTest {
    /**
     * 判定函数
     *
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val != root.val) {
            return false;
        }
        if (root.right != null && root.right.val != root.val) {
            return false;
        }
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }
}
