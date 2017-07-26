package cn.xidian.algorithm.sordoffer;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：根据二叉树的前序遍历和中序遍历结果重建二叉树
 * 创建作者：陈苗
 * 创建时间：2016年5月31日 16:17
 */
public class RebuildBinaryTreeTest {
    private int[] preOrder;
    private int[] inOrder;

    /**
     * 构造函数
     *
     * @param preOrder 前序遍历数组
     * @param inOrder  后序遍历数组
     */
    public RebuildBinaryTreeTest(int[] preOrder, int[] inOrder) {
        this.preOrder = preOrder;
        this.inOrder = inOrder;
    }

    /**
     * 构建二叉树的函数
     *
     * @return 由前序遍历和中序遍历结果所确定的二叉树结构
     */
    public BinaryTreeNode<Integer> construct() throws Exception {
        if (preOrder == null || inOrder == null || preOrder.length != inOrder.length || preOrder.length <= 0)
            return null;
        return constructCore(0, preOrder.length - 1, 0, inOrder.length - 1);
    }

    /**
     * 构建二叉树的核心函数
     *
     * @param startPreIndex 前序遍历数组的起始索引
     * @param endPreIndex   前序遍历数组的结束索引
     * @param startMidIndex 后序遍历数组的起始索引
     * @param endMidIndex   后序遍历数组的结束索引
     * @return 二叉树的结构
     * @throws Exception 程序抛出的异常
     */
    private BinaryTreeNode<Integer> constructCore(int startPreIndex, int endPreIndex, int startMidIndex, int endMidIndex) throws Exception {
        int rootValue = preOrder[startPreIndex];//前序遍历序列中第一个数字即为根节点的值
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootValue, null, null);
        if (startPreIndex == endPreIndex) {
            if (startMidIndex == endMidIndex && preOrder[startPreIndex] == inOrder[startMidIndex])
                return root;
            else
                throw new Exception("非法的输入参数");
        }
        //从中序遍历数组中找到根节点的值
        int rootMidOrder = startMidIndex;
        while (rootMidOrder <= endMidIndex && inOrder[rootMidOrder] != rootValue)
            ++rootMidOrder;
        if (rootMidOrder == endMidIndex && inOrder[rootMidOrder] != rootValue)
            throw new Exception("非法的输入参数");
        int leftLength = rootMidOrder - startMidIndex;
        int leftPreorderEnd = startPreIndex + leftLength;
        if (leftLength > 0)
            root.setLeftChild(constructCore(startPreIndex + 1, leftPreorderEnd, startMidIndex, rootMidOrder - 1));
        if (leftLength < endPreIndex - startPreIndex)
            root.setRightChild(constructCore(leftPreorderEnd + 1, endPreIndex, rootMidOrder + 1, endMidIndex));
        return root;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};
        RebuildBinaryTreeTest treeTest = new RebuildBinaryTreeTest(preOrder, inOrder);
        BinaryTreeNode<Integer> binaryTreeRoot = null;
        try {
            binaryTreeRoot = treeTest.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        binaryTreeRoot.behindTraversal(binaryTreeRoot);
    }
}
