package cn.xidian.algorithm.guigu;

/**
 * 文件描述：在给定包含有指向父节点的二叉树结构中，求其中序遍历的下一个节点
 * 创建作者：陈苗
 * 创建时间：2017/8/4 14:16
 */
public class InorderSucceesorTest {

    private static class Node {
        Node left, right, parent;
        int data;
    }

    public Node inorderSuccessor(Node e) {
        if (e == null)
            return null;
        if (e.right != null)
            return getLeftMost(e.right);
        else {
            Node p = e.parent;
            while (p != null && p.left != e) {
                e = p;
                p = e.parent;
            }
            return p;
        }
    }

    private Node getLeftMost(Node p) {
        Node res = p;
        while (res.left != null)
            res = res.left;
        return res;
    }
}
