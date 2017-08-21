package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 文件描述：在给定的二叉树中查找是否存在从根节点到叶子节点的路径上的值满足和为给定值，若存在，则获取路径
 * 创建作者：陈苗
 * 创建时间：2017/8/21 17:35
 */
public class PathSumIITest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Stack<Integer> path = new Stack<>();
        List<Integer> current_sum = new ArrayList<>();
        current_sum.add(0);
        getPathSum(root, result, path, sum, current_sum);
        return result;
    }

    private void getPathSum(TreeNode root, List<List<Integer>> result, Stack<Integer> path, int sum, List<Integer> current_sum) {
        path.push(root.val);
        current_sum.set(0, current_sum.get(0) + root.val);
        if (root.left == null && root.right == null && current_sum.get(0) == sum) {
            List<Integer> temp_path = new ArrayList<>();
            for (Integer node : path)
                temp_path.add(node);
            result.add(temp_path);
        }
        if (root.left != null)
            getPathSum(root.left, result, path, sum, current_sum);
        if (root.right != null)
            getPathSum(root.right, result, path, sum, current_sum);
        int pop_val = path.pop();
        current_sum.set(0, current_sum.get(0) - pop_val);
    }
}
