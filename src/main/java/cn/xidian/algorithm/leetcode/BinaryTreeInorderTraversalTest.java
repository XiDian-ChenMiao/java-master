package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：二叉树中序遍历
 * 创建作者：陈苗
 * 创建时间：2017/5/2 15:53
 */
public class BinaryTreeInorderTraversalTest {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 调用函数
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        inorderTraversal(root, result);
        return result;
    }

    /**
     * 递归中序遍历
     * @param root
     * @param result
     */
    private void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversal(root.left, result);
            result.add(root.val);
            inorderTraversal(root.right, result);
        } else {
            return;
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        root.left = null;
        root.right = two;
        two.left = three;
        two.right = null;
        List<Integer> result = new BinaryTreeInorderTraversalTest().inorderTraversal(root);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
