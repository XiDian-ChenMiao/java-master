package cn.xidian.algorithm.leetcode;

import java.util.LinkedList;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: LowestCommonAncestorInBSTTest
 * @description: 235.二叉搜索树中求取最近公共祖先
 * @date 2019-05-19 10:58
 */
public class LowestCommonAncestorInBSTTest {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> pPath = getPath(root, p);
        LinkedList<TreeNode> qPath = getPath(root, q);
        return getCommonAncestor(pPath, qPath);
    }

    private TreeNode getCommonAncestor(LinkedList<TreeNode> pPath, LinkedList<TreeNode> qPath) {
        TreeNode pre = null;
        for (int i = 0; i < Math.min(pPath.size(), qPath.size()); i++) {
            if (pPath.get(i) != qPath.get(i)) {
                return pre;
            }
            pre = pPath.get(i);
        }
        return pre;
    }

    private LinkedList<TreeNode> getPath(TreeNode root, TreeNode target) {
        TreeNode node = root;
        LinkedList<TreeNode> result = new LinkedList<>();
        while (node != null) {
            result.add(node);
            if (target.val == node.val) {
                return result;
            } else if (node.val > target.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left = new TreeNode(8);

        System.out.println(new LowestCommonAncestorInBSTTest().lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8)).val);
    }
}
