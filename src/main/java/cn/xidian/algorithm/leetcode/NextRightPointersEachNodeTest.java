package cn.xidian.algorithm.leetcode;


/**
 * 文件描述：连接二叉树的同级相邻节点
 * 创建作者：陈苗
 * 创建时间：2017/8/21 20:24
 */
public class NextRightPointersEachNodeTest {
    public static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    /**
     * 连接函数
     * 前提：满二叉树和常数空间
     * @param root
     */
    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        TreeLinkNode curLevelNode = null;
        while (root.left != null) {
            curLevelNode = root;
            while (curLevelNode != null) {
                curLevelNode.left.next = curLevelNode.right;
                if (curLevelNode.next != null)
                    curLevelNode.right.next = curLevelNode.next.left;
                curLevelNode = curLevelNode.next;
            }
            root = root.left;
        }
    }
}
