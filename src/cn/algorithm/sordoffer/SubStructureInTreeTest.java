package cn.algorithm.sordoffer;

import cn.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：判定给定的树是不是已知树的子树
 * 创建作者：陈苗
 * 创建时间：2016年6月4日 20:07
 */
public class SubStructureInTreeTest {

    /**
     * 判定是否为子树的函数
     * @return 若是，返回true；反之，返回false；
     */
    public boolean hasSubTree(BinaryTreeNode<Integer> bigTree, BinaryTreeNode<Integer> subTree) {
        boolean result = false;
        if (bigTree != null && subTree != null) {
            if (bigTree.getData() == subTree.getData())
                result = justifySubChild(bigTree,subTree);
            if (!result)
                result = hasSubTree(bigTree.getLeftChild(),subTree);
            if (!result)
                result = hasSubTree(bigTree.getRightChild(),subTree);
        }
        return result;
    }

    /**
     * 判断子节点是否相同
     * @param p
     * @param q
     * @return
     */
    public boolean justifySubChild(BinaryTreeNode<Integer> p,BinaryTreeNode<Integer> q) {
        if (q == null)
            return true;
        if (p == null)
            return false;
        if (p.getData() != q.getData())
            return false;
        return justifySubChild(p.getLeftChild(),q.getLeftChild()) && justifySubChild(p.getRightChild(),q.getRightChild());
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        //构建子树
        BinaryTreeNode<Integer> node9 = new BinaryTreeNode<Integer>(9,null,null);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(2,null,null);
        BinaryTreeNode<Integer> node8 = new BinaryTreeNode<Integer>(8,node9,node2);

        //构建大树
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(1,null,null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(8,node8,node1);

        System.out.println("大树是否包含子树：" + new SubStructureInTreeTest().hasSubTree(root,node8));

    }
}
