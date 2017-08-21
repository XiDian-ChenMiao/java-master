package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：连接二叉树的同级相邻节点
 * 创建作者：陈苗
 * 创建时间：2017/8/21 21:16
 */
public class NextRightPointersEachNodeIITest {

    public static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    /**
     * 连接函数
     * 前提：常数空间
     * @param root
     */
    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        TreeLinkNode head = null;/*下一层的头结点*/
        TreeLinkNode prev = null;/*下一层节点的临时前驱节点*/
        TreeLinkNode cur = root;/*当前层的当前节点*/
        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {
                    if (prev != null)
                        prev.next = cur.left;
                    else
                        head = cur.left;
                    prev = cur.left;
                }
                if (cur.right != null) {
                    if (prev != null)
                        prev.next = cur.right;
                    else
                        head = cur.right;
                    prev = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
            head = null;
            prev = null;
        }
    }
}
