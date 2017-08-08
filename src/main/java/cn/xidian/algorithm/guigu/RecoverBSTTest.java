package cn.xidian.algorithm.guigu;

/**
 * 文件描述：二叉搜索树中有两个节点被调换，要求用常数空间恢复此二叉搜索树
 * 创建作者：陈苗
 * 创建时间：2017/8/7 18:47
 */
public class RecoverBSTTest {

    private static class Node {
        Node left, right;
        int data;
    }

    private void recover(Node root, Node one, Node two, Node pre) {
        if (root == null)
            return;
        recover(root.left, one, two, pre);
        if (pre != null && pre.data > root.data) {
            two = root;
            if (one == null)
                one = pre;
        }
        pre = root;
        recover(root.right, one, two, pre);
    }

    public void recover(Node root) {
        Node one = null, two = null, pre = null;
        recover(root, one, two, pre);
        if (one != null && two != null) {
            int temp = one.data;
            one.data = two.data;
            two.data = temp;
        }
    }
}
