package cn.xidian.algorithm.sordoffer;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：平衡二叉树的判断
 * 创建作者：陈苗
 * 创建时间：2017/2/21 15:25
 */
public class IsBalancedTest {
    private class DepthClass {
        int depth;
    }
    /**
     * 平衡二叉树的判断函数
     * @param root
     * @param cls
     * @return
     */
    private boolean isBalanced(BinaryTreeNode<Integer> root, DepthClass cls) {
        if (root == null) {
            cls.depth = 0;
            return true;
        }
        DepthClass left = new DepthClass();
        DepthClass right = new DepthClass();
        if (isBalanced(root.getLeftChild(), left) && isBalanced(root.getRightChild(), right)) {
            if (Math.abs(left.depth - right.depth) <= 1) {
                cls.depth = 1 + (left.depth > right.depth ? left.depth : right.depth);
                return true;
            }
        }
        return false;
    }

    /**
     * 外部调用方法
     * @param root
     * @return
     */
    public boolean isBalanced(BinaryTreeNode<Integer> root) {
        DepthClass cls = new DepthClass();
        return isBalanced(root, cls);
    }
    /**
     * 主程序调用
     * @param args
     */
    public static void main(String[] args) {
        BinaryTreeNode<Integer> one = new BinaryTreeNode<Integer>(1, null, null);
        BinaryTreeNode<Integer> four = new BinaryTreeNode<Integer>(4, one, null);
        BinaryTreeNode<Integer> eight = new BinaryTreeNode<Integer>(8, null, null);
        BinaryTreeNode<Integer> six = new BinaryTreeNode<Integer>(6, four, eight);
        BinaryTreeNode<Integer> ten = new BinaryTreeNode<Integer>(10, six, null);
        System.out.println(new IsBalancedTest().isBalanced(ten));
    }
}
