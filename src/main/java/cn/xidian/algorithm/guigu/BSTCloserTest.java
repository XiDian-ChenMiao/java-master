package cn.xidian.algorithm.guigu;

/**
 * 文件描述：从二叉搜索树中找出最接近给定点的节点
 * 创建作者：陈苗
 * 创建时间：2017/8/4 14:24
 */
public class BSTCloserTest {
    private static class Node {
        Node left, right;
        int data;
    }

    public Node getCloserBST(Node root, int value) {
        if (root == null)
            return null;
        if (root.data == value)
            return root;
        Node left = getCloserBST(root.left, value);
        Node right = getCloserBST(root.right, value);
        int diffLeft = left == null ? Integer.MAX_VALUE : Math.abs(value - left.data);
        int diffRight = right == null ? Integer.MAX_VALUE : Math.abs(value - right.data);
        int diff = Math.abs(value - root.data);
        int min = Math.min(diff, Math.min(diffLeft, diffRight));
        if (min == diff)
            return root;
        else if (min == diffLeft)
            return left;
        else
            return right;
    }
}
