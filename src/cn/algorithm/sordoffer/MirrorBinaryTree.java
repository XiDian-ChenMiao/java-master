package cn.algorithm.sordoffer;

import cn.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：求给定二叉树的镜像
 * 创建作者：陈苗
 * 创建时间：2016年6月4日 21:55
 */
public class MirrorBinaryTree {
    /**
     * 转镜像函数
     * @param root 给定二叉树的根节点
     */
    public static void mirror(BinaryTreeNode<Integer> root) {
        if (root == null)
            return;
        else if (root.getLeftChild() == null && root.getRightChild() == null)
            return;
        else {
            BinaryTreeNode<Integer> left = root.getLeftChild();
            BinaryTreeNode<Integer> right = root.getRightChild();
            root.setLeftChild(right);
            root.setRightChild(left);
            if (left != null)
                mirror(left);
            if (right != null)
                mirror(right);
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        //构建二叉树
        BinaryTreeNode<Integer> node9 = new BinaryTreeNode<Integer>(9,null,null);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(2,null,null);
        BinaryTreeNode<Integer> node8 = new BinaryTreeNode<Integer>(8,node9,node2);
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(1,null,null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(10,node8,node1);

        MirrorBinaryTree.mirror(root);//对所给的二叉树执行转镜像操作
        root.previousTraversal(root);//中序遍历输出
    }
}
