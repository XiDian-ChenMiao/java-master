package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二叉树的所有路径
 * @version v1.0.0
 * @author chenmiao
 */
public class AllBinaryTreePath {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        StringBuilder path = new StringBuilder();
        addPathSet(root, result, path);
        return result;
    }

    /**
     * 把路径添加到结果集合中
     * @param node 二叉树节点
     * @param result 结果集合
     * @param path 路径
     */
    private void addPathSet(TreeNode node, List<String> result, StringBuilder path) {
        path.append(node.val);
        if (node.left == null && node.right == null) {
            result.add(path.toString());
            return;
        }
        StringBuilder leftPath = new StringBuilder(path.toString());
        StringBuilder rightPath = new StringBuilder(path.toString());
        if (node.left != null) {
            leftPath.append("->");
            addPathSet(node.left, result, leftPath);
        }
        if (node.right != null) {
            rightPath.append("->");
            addPathSet(node.right, result, rightPath);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode one = new TreeNode(2);
        TreeNode two = new TreeNode(3);
        TreeNode three = new TreeNode(5);
        root.left = one;
        root.right = two;
        one.right = three;

        System.out.println(Arrays.asList(new AllBinaryTreePath().binaryTreePaths(root)));
    }
}
