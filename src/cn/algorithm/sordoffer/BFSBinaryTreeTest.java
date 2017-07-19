package cn.algorithm.sordoffer;

import cn.algorithm.sordoffer.common.BinaryTreeNode;

import java.util.LinkedList;

/**
 * 文件描述：二叉树的广度优先遍历
 * 创建作者：陈苗
 * 创建时间：2016年6月6日 17:17
 */
public class BFSBinaryTreeTest {
    /**
     * 广度优先遍历
     * @param root 需要遍历的二叉树根节点
     */
    public void bfsTraversal(BinaryTreeNode<Integer> root) {
        LinkedList<BinaryTreeNode<Integer>> childQueue = new LinkedList<BinaryTreeNode<Integer>>();//初始化存储孩子节点的队列;
        if (root == null)
            return;
        childQueue.add(root);
        while (childQueue.size() > 0) {
            Integer dataHead = childQueue.getFirst().getData();
            System.out.print(dataHead + "\t");
            BinaryTreeNode<Integer> headNode = childQueue.getFirst();
            if (headNode.getLeftChild() != null)
                childQueue.add(headNode.getLeftChild());
            if (headNode.getRightChild() != null)
                childQueue.add(headNode.getRightChild());
            childQueue.removeFirst();
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

        BFSBinaryTreeTest test = new BFSBinaryTreeTest();
        System.out.println("广度优先遍历结果为：");
        test.bfsTraversal(root);
    }
}
