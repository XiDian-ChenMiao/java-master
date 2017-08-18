package cn.xidian.algorithm.leetcode;

import java.util.*;

/**
 * 文件描述：二叉树的层序遍历
 * 创建作者：陈苗
 * 创建时间：2017/8/18 20:41
 */
public class BinaryTreeLevelOrderTraversalTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
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
            result.add(temp);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode one = new TreeNode(2);
        TreeNode two = new TreeNode(3);
        TreeNode three = new TreeNode(4);
        TreeNode four = new TreeNode(5);
        root.left = one;
        one.left = two;
        two.left = three;
        three.left = four;

        List<List<Integer>> result = new BinaryTreeLevelOrderTraversalTest().levelOrder(root);
        for (List<Integer> temp : result)
            System.out.println(Arrays.toString(temp.toArray()));
    }
}
