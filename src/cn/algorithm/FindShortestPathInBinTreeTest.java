package cn.algorithm;

import cn.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：从二叉树上搜索路径最短的分支
 * 创建作者：陈苗
 * 创建时间：2016年6月13日 14:51
 */
public class FindShortestPathInBinTreeTest {
    private static int result = Integer.MAX_VALUE;

    /**
     * 获取二叉树中从根节点到叶子节点的最短路径调用接口
     * @param head 二叉树的头结点
     * @return 最短路径
     */
    public static int findShortestPath(BinaryTreeNode<Integer> head) {
        if (head == null)
            return 0;
        if (head.getLeftChild() == null && head.getRightChild() == null) {
            return head.getData();
        }
        BinaryTreeNode<Integer> node = head;
        getResult(node,node.getData());
        return result;
    }

    /**
     * 获取最短路径函数
     * @param head 二叉树头结点
     * @param temp 中间存储的临时值
     */
    private static void getResult(BinaryTreeNode<Integer> head,int temp) {
        if (head.getLeftChild() == null && head.getRightChild() == null) {
            if (temp < result)
                result = temp;
            return;
        } else {
            getResult(head.getLeftChild(),head.getLeftChild().getData() + temp);
            getResult(head.getRightChild(),head.getRightChild().getData() + temp);
        }
    }
    public static void main(String[] args) {
        //构建二叉树
        BinaryTreeNode<Integer> node9 = new BinaryTreeNode<Integer>(9,null,null);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(2,null,null);
        BinaryTreeNode<Integer> node8 = new BinaryTreeNode<Integer>(8,node9,node2);
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(1,null,null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(10,node8,node1);
        System.out.println(FindShortestPathInBinTreeTest.findShortestPath(root));
    }
}
