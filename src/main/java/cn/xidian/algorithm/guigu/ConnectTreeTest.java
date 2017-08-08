package cn.xidian.algorithm.guigu;

/**
 * 文件描述：通过next将完全二叉树左右节点连接并可以跨子树
 * 创建作者：陈苗
 * 创建时间：2017/8/7 18:31
 */
public class ConnectTreeTest {
    private static class Node {
        Node left, right, next;
        int data;
    }

    public void connect(Node root) {
        Node currentLevel = root;
        while (currentLevel != null) {
            Node across = currentLevel;
            while (across != null) {
                if (across.left != null)
                    across.left.next = across.right;
                if (across.right != null && across.next != null)
                    across.right.next = across.next.left;
                across = across.next;
            }
            currentLevel = currentLevel.left;
        }
    }
}
