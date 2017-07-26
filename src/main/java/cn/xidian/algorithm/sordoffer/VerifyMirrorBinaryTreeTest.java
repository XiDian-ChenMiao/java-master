package cn.xidian.algorithm.sordoffer;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：对称二叉树的判断
 * 创建作者：陈苗
 * 创建时间：2017/2/22 15:30
 */
public class VerifyMirrorBinaryTreeTest {
    /**
     * 外部调用判断是否二叉树为对称二叉树的方法
     * @param root
     * @return
     */
    public boolean isSymmetrical(BinaryTreeNode<Integer> root) {
        return isSymmetrical(root, root);
    }

    /**
     * 判断内部函数
     * @param one
     * @param two
     * @return
     */
    private boolean isSymmetrical(BinaryTreeNode<Integer> one, BinaryTreeNode<Integer> two) {
        if (one == null && two == null)
            return true;
        if (one == null || two == null)
            return false;
        if (one.getData() != two.getData())
            return false;
        return isSymmetrical(one.getLeftChild(), two.getRightChild()) && isSymmetrical(one.getRightChild(), two.getLeftChild());
    }

    /**
     * 主程序调用
     * @param args
     */
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1, null, null);
        BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>(2, null, null);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>(2, null, null);
        root.setLeftChild(left);
        root.setRightChild(right);
        System.out.println(new VerifyMirrorBinaryTreeTest().isSymmetrical(root));
    }
}
