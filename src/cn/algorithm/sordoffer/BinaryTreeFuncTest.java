package cn.algorithm.sordoffer;

import cn.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：二叉树功能测试类
 * 创建作者：陈苗
 * 创建时间：2016/9/18 9:29
 */
public class BinaryTreeFuncTest {
    /**
     * 获取树的高度
     * @param root
     * @return
     */
    public int getTreeDepth(BinaryTreeNode<Integer> root) {
        if (root == null)
            return 0;
        int left = getTreeDepth(root.getLeftChild());
        int right = getTreeDepth(root.getRightChild());
        return left > right ? 1 + left : 1 + right;
    }

    /**
     * 判断给定二叉树是否为平衡二叉树
     * @param root
     * @return
     */
    public boolean isBalanced(BinaryTreeNode<Integer> root) {
        Integer depth = 0;
        return isBalanced(root, depth);
    }

    private boolean isBalanced(BinaryTreeNode<Integer> root, Integer depth) {
        if (root == null) {
            depth = 0;
            return true;
        }
        Integer left = 0, right = 0;
        if (isBalanced(root.getLeftChild(), left) && isBalanced(root.getRightChild(), right)) {
            int diff = left - right;
            if (1 >= Math.abs(diff)) {
                depth = 1 + (left > right ? left : right);
                return true;
            }
        }
        return false;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1, null, null);
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(2, null, null);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(3, null, null);
        BinaryTreeNode<Integer> node3 = new BinaryTreeNode<Integer>(4, null, null);
        BinaryTreeNode<Integer> node4 = new BinaryTreeNode<Integer>(5, null, null);
        node3.setRightChild(node4);
        node2.setLeftChild(node3);
        root.setLeftChild(node2);
        root.setRightChild(node1);
        BinaryTreeFuncTest test = new BinaryTreeFuncTest();

        System.out.println("树的深度为：" + test.getTreeDepth(root));
        System.out.println("树是否为平衡二叉树：" + test.isBalanced(root));
    }
}
