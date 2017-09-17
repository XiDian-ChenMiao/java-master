package cn.xidian.algorithm.leetcode;

import java.util.Stack;

/**
 * 二叉搜索树迭代器的实现，要求hasNext方法为O（1）的复杂度，next方法为O（h）的复杂度，其中h为二叉搜索树的树高
 */
public class BinarySearchTreeIteratorTest {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    private Stack<TreeNode> stack;
    public BinarySearchTreeIteratorTest(TreeNode root) {
        stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null){
            stack.push(cur);
            if(cur.left != null)
                cur = cur.left;
            else
                break;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        TreeNode cur = node;
        if(cur.right != null){
            cur = cur.right;
            while(cur != null){
                stack.push(cur);
                if(cur.left != null)
                    cur = cur.left;
                else
                    break;
            }
        }
        return node.val;
    }
}