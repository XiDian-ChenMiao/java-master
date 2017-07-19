package cn.algorithm.sordoffer;

import cn.algorithm.sordoffer.common.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 文件描述：按层打印二叉树
 * 创建作者：陈苗
 * 创建时间：2017/2/22 15:43
 */
public class LayerPrintTreeTest {
    /**
     * 按层打印外部调用函数
     * @param root
     */
    public void print(BinaryTreeNode<Integer> root) {
        if (root == null)
            return;
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<BinaryTreeNode<Integer>>();
        int toBePrinted = 1, nextLevel = 0;/*toBePrinted指代还没有打印的节点数，nextLevel表示下一层节点的数目*/
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<Integer> front = queue.remove();
            System.out.print(front.getData() + "\t");
            if (front.getLeftChild() != null) {
                queue.add(front.getLeftChild());
                ++nextLevel;
            }
            if (front.getRightChild() != null) {
                queue.add(front.getRightChild());
                ++nextLevel;
            }
            --toBePrinted;
            if (toBePrinted == 0) {
                System.out.println();
                toBePrinted = nextLevel;
                nextLevel = 0;
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
        new LayerPrintTreeTest().print(root);
    }
}
