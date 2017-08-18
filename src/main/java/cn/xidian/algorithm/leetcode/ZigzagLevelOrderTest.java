package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 文件描述：按照Z序层序遍历二叉树
 * 创建作者：陈苗
 * 创建时间：2017/8/18 21:48
 */
public class ZigzagLevelOrderTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        LinkedList<TreeNode> curLevel = new LinkedList<>();
        curLevel.add(root);
        boolean leftToRight = true;
        while (curLevel.size() > 0) {
            ArrayList<Integer> curList = new ArrayList<>();
            LinkedList<TreeNode> nextLevel = new LinkedList<>();
            while (curLevel.size() > 0) {
                TreeNode node = curLevel.poll();
                if (node.left != null)
                    nextLevel.add(node.left);
                if (node.right != null)
                    nextLevel.add(node.right);
                if (leftToRight)
                    curList.add(node.val);
                else
                    curList.add(0, node.val);
            }
            result.add(curList);
            curLevel = nextLevel;
            leftToRight = !leftToRight;
        }
        return result;
    }
}
