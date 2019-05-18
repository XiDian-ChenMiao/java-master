package cn.xidian.algorithm.leetcode;

import java.util.Stack;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: FullBinaryTreeNodeCntTest
 * @description: 获取完全二叉树的节点个数
 * @date 2019-05-18 16:11
 */
public class FullBinaryTreeNodeCntTest {
    /**
     * 通过栈这样的数据结构来存储遍历每一层的节点
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int cnt = 0;
        if (root != null) {
            stack.push(root);
            cnt += 1;
        }
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.push(node.left);
                cnt += 1;
            }
            if (node.right != null) {
                stack.push(node.right);
                cnt += 1;
            }
        }
        return cnt;
    }

    /**
     * 通过递归方法来求取完全二叉树总节点数
     * @param root
     * @return
     */
    public int countNodesByRecursion(TreeNode root) {
        return root == null ? 0 : 1 + countNodesByRecursion(root.left) + countNodesByRecursion(root.right);
    }
}
