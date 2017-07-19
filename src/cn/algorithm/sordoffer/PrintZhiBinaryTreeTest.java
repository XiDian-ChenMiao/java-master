package cn.algorithm.sordoffer;

import cn.algorithm.sordoffer.common.BinaryTreeNode;

import java.util.Stack;

/**
 * 文件描述：将二叉树之字形打印
 * 创建作者：陈苗
 * 创建时间：2017/2/22 16:06
 */
public class PrintZhiBinaryTreeTest {
    /**
     * 打印函数
     * @param root
     */
    public void print(BinaryTreeNode<Integer> root) {
        if (root == null)
            return;
        Stack<BinaryTreeNode<Integer>>[] levels = new Stack[2];
        levels[0] = new Stack<BinaryTreeNode<Integer>>();
        levels[1] = new Stack<BinaryTreeNode<Integer>>();
        int current = 0;
        int next = 1;

        levels[current].push(root);
        while (!levels[0].isEmpty() || !levels[1].isEmpty()) {
            BinaryTreeNode<Integer> node = levels[current].pop();
            System.out.print(node.getData() + "\t");
            if (current == 0) {
                if (node.getLeftChild() != null)
                    levels[next].push(node.getLeftChild());
                if (node.getRightChild() != null)
                    levels[next].push(node.getRightChild());
            } else {
                if (node.getRightChild() != null)
                    levels[next].push(node.getRightChild());
                if (node.getLeftChild() != null)
                    levels[next].push(node.getLeftChild());
            }
            if (levels[current].isEmpty()) {
                System.out.println();
                current = 1 - current;
                next = 1 - next;
            }
        }
    }

    /**
     * 主程序调用
     * @param args
     */
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1, null, null);
        BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>(2, null, null);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>(3, null, null);
        root.setLeftChild(left);
        root.setRightChild(right);
        new PrintZhiBinaryTreeTest().print(root);
    }
}
