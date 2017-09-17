package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 文件描述：二叉树右视图
 * 创建作者：陈苗
 * 创建时间：2017/9/17 20:59
 */
public class BinaryTreeRightSideViewTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() != 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode top = queue.remove();
                if (i == 0)
                    result.add(top.val);
                if (top.right != null)
                    queue.add(top.right);
                if (top.left != null)
                    queue.add(top.left);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(4);
        TreeNode d = new TreeNode(5);
        root.left = a;
        root.right = b;
        a.right = c;
        b.right = d;

        List<Integer> result = new BinaryTreeRightSideViewTest().rightSideView(root);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
