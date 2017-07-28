package cn.xidian.algorithm.guigu;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：最小公共祖先
 * 创建作者：陈苗
 * 创建时间：2017/7/28 20:41
 */
public class LatestCommonAncestorTest {
    /**
     * 在二叉查找树中寻找给定的两个节点的最小公共祖先
     * @param root
     * @param one
     * @param two
     * @return
     */
    public BinaryTreeNode<Integer> lca_in_bin_search_tree(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> one, BinaryTreeNode<Integer> two) {
        if (root == null || one == null || two == null)
            return null;
        if (root.getData() > one.getData() && root.getData() > two.getData())
            return lca_in_bin_search_tree(root.getLeftChild(), one, two);
        else if (root.getData() < one.getData() && root.getData() < two.getData())
            return lca_in_bin_search_tree(root.getRightChild(), one, two);
        else
            return root;
    }

    /**
     * 在二叉树中寻找给定的两个节点最小公共祖先
     * @param root
     * @param one
     * @param two
     * @return
     */
    public BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> one, BinaryTreeNode<Integer> two) {
        if (root == null || one == null || two == null)
            return null;
        BinaryTreeNode<Integer> left = lca(root.getLeftChild(), one, two);
        BinaryTreeNode<Integer> right = lca(root.getRightChild(), one, two);
        if (left != null && right != null)
            return root;
        return left == null ? right : left;
    }
}
