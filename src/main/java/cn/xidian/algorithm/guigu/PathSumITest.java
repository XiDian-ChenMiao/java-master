package cn.xidian.algorithm.guigu;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：给定一个二叉树和一个值，判断是否存在从根节点到叶子节点的路径和等于给定的值
 * 创建作者：陈苗
 * 创建时间：2017/7/27 19:23
 */
public class PathSumITest {

    public boolean hasPathSum(BinaryTreeNode<Integer> root, int sum) {
        if (root == null)
            return false;
        if (root.getLeftChild() == null && root.getRightChild() == null && root.getData() == sum)
            return true;
        return hasPathSum(root.getLeftChild(), sum - root.getData()) || hasPathSum(root.getRightChild(), sum - root.getData());
    }
}
