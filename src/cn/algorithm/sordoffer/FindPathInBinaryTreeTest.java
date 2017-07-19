package cn.algorithm.sordoffer;

import cn.algorithm.sordoffer.common.BinaryTreeNode;

import java.util.Stack;

/**
 * 文件描述：从二叉树上查找某条路径上的和是否存在为一给定值的类
 * 创建作者：陈苗
 * 创建时间：2016年6月7日 10:15
 */
public class FindPathInBinaryTreeTest {
    /**
     * 判断函数
     * 只判断给定的二叉树中是否存在路径之和满足给定值，不输出路径
     * @param sum 给定值
     * @return 如果存在，则返回true；否则，返回false
     */
    public boolean isExist(BinaryTreeNode<Integer> node, int sum) {
        if (node == null)
            return false;
        //如果此节点为叶子节点
        if (node.getLeftChild() == null && node.getRightChild() == null)
            return node.getData() == sum ? true : false;//如果当前叶子节点的值与当前给定的值相同，则确定存在路径
        else {
            boolean result = false;
            //说明此节点存在孩子节点
            if (node.getLeftChild() != null)
                result = isExist(node.getLeftChild(), sum - node.getData());
            if (node.getRightChild() != null && !result)//如果左子树没有发现，再去右子树查找；如果发现，则不需要继续查找
                result = isExist(node.getRightChild(), sum - node.getData());
            return result;
        }
    }

    /**
     * 查找和为定值的路径函数
     * 判定找到满足条件之后输出路径
     * @param node        给定二叉树的根节点
     * @param expectedSum 期望存在的和值
     */
    public void findPath(BinaryTreeNode<Integer> node, int expectedSum) {
        if (node == null)
            return;
        Stack<Integer> path = new Stack<Integer>();
        int currentSum = 0;
        findPath(node, expectedSum, path, currentSum);
    }

    /**
     * 辅助查找和为定值的路径函数
     *
     * @param node        给定二叉树的根节点
     * @param expectedSum 期望存在的和值
     * @param path        路径栈
     * @param currentSum  当前节点之前构成的和值
     */
    private void findPath(BinaryTreeNode<Integer> node, int expectedSum, Stack<Integer> path, int currentSum) {
        currentSum += node.getData();//访问到某一节点时，需要将此节点的值加到当前的总和变量中
        path.push(node.getData());//并且需要在路径栈中添加此节点的值
        //如果当前节点为叶子节点，且当前和值与期望给定的和值相等，则找到路径
        if (node.getLeftChild() == null && node.getRightChild() == null && currentSum == expectedSum) {
            System.out.println("查找到一条路径：");
            for (Integer data : path)
                System.out.print(data + "\t");
            System.out.println();
        }
        if (node.getLeftChild() != null)
            findPath(node.getLeftChild(), expectedSum, path, currentSum);
        if (node.getRightChild() != null)
            findPath(node.getRightChild(), expectedSum, path, currentSum);
        path.pop();//在返回父节点之前，在路径上删除当前节点
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        //构建二叉树
        BinaryTreeNode<Integer> node9 = new BinaryTreeNode<Integer>(9, null, null);
        BinaryTreeNode<Integer> node4 = new BinaryTreeNode<Integer>(4, null, null);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(2, null, null);
        BinaryTreeNode<Integer> node8 = new BinaryTreeNode<Integer>(8, node9, node2);
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(1, null, node4);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(10, node8, node1);
        FindPathInBinaryTreeTest test = new FindPathInBinaryTreeTest();
        int sum = 27;
        System.out.println("在二叉树上是否存在由根节点到叶子节点的路径之和为" + sum + "的结果：" + test.isExist(root, sum));
        test.findPath(root, sum);
    }
}
