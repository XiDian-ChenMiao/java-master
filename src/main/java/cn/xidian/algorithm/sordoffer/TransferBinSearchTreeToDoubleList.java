package cn.xidian.algorithm.sordoffer;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：将二叉搜索树转换为双向链表类
 * 创建作者：陈苗
 * 创建时间：2016年6月8日 16:29
 */
public class TransferBinSearchTreeToDoubleList {
    private BinaryTreeNode<Integer> previous;

    /**
     * 获取由二叉搜索树所构成的双向链表的接口函数
     *
     * @param root
     * @return
     */
    public void transfer(BinaryTreeNode<Integer> root) {
        if (root == null)
            return;
        else {
            transfer(root.getLeftChild());
            if (previous != null) {
                root.setLeftChild(previous);
                previous.setRightChild(root);
            }
            previous = root;
            transfer(root.getRightChild());
        }
    }

    /**
     * 打印函数
     * @param root
     */
    public void print(BinaryTreeNode<Integer> root) {
        if (root != null) {
            while (root.getLeftChild() != null) {
                root = root.getLeftChild();
            }
            while (root != null) {
                System.out.println(root.getData());
                root = root.getRightChild();
            }
        }
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        //构建二叉树
        BinaryTreeNode<Integer> node9 = new BinaryTreeNode<Integer>(9, null, null);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(2, null, null);
        BinaryTreeNode<Integer> node8 = new BinaryTreeNode<Integer>(8, node2, node9);
        BinaryTreeNode<Integer> node11 = new BinaryTreeNode<Integer>(11, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(10, node8, node11);

        TransferBinSearchTreeToDoubleList test = new TransferBinSearchTreeToDoubleList();
        test.transfer(root);
        test.print(root);
    }
}
