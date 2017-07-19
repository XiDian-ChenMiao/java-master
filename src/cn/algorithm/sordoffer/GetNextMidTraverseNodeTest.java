package cn.algorithm.sordoffer;


/**
 * 文件描述：给定一个节点，求其对应的中序遍历的后一个节点
 * 创建作者：陈苗
 * 创建时间：2017/2/22 15:07
 */
public class GetNextMidTraverseNodeTest {
    private static class BinaryTreeNode {
        char val;
        BinaryTreeNode left;
        BinaryTreeNode right;
        BinaryTreeNode parent;
    }
    /**
     * 外部调用获取下一个中序遍历节点的函数
     * @param node
     * @return
     */
    public BinaryTreeNode getNext(BinaryTreeNode node) {
        if (node == null)
            return null;
        BinaryTreeNode next = null;
        if (node.right != null) {
            BinaryTreeNode right = node.right;
            while (right.left != null) {
                right = right.left;
            }
            next = right;
        } else if (node.parent != null) {
            BinaryTreeNode cur = node;
            BinaryTreeNode parent = cur.parent;
            while (parent != null && cur == parent.right) {
                cur = parent;
                parent = parent.parent;
            }
            next = parent;
        }
        return next;
    }

    /**
     * 主程序调用
     * @param args
     */
    public static void main(String[] args) {
        BinaryTreeNode a = new BinaryTreeNode();
        BinaryTreeNode b = new BinaryTreeNode();
        BinaryTreeNode c = new BinaryTreeNode();
        BinaryTreeNode d = new BinaryTreeNode();
        BinaryTreeNode e = new BinaryTreeNode();
        BinaryTreeNode f = new BinaryTreeNode();
        BinaryTreeNode g = new BinaryTreeNode();
        BinaryTreeNode h = new BinaryTreeNode();
        BinaryTreeNode i = new BinaryTreeNode();
        a.left = b;
        a.right = c;
        b.parent = a;
        c.parent = a;

        b.left = d;
        b.right = e;
        d.parent = b;
        e.parent = b;

        c.left = f;
        c.right = g;
        f.parent = c;
        g.parent = c;

        e.left = h;
        e.right = i;
        h.parent = e;
        i.parent = e;

        a.val = 'A';
        b.val = 'B';
        c.val = 'C';
        d.val = 'D';
        e.val = 'E';
        f.val = 'F';
        g.val = 'G';
        h.val = 'H';
        i.val = 'I';

        System.out.println(new GetNextMidTraverseNodeTest().getNext(i).val);
    }
}
