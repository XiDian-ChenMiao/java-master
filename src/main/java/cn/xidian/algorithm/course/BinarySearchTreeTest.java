package cn.xidian.algorithm.course;

import cn.xidian.algorithm.sordoffer.common.BinaryTreeNode;

/**
 * 文件描述：二叉搜索树插入、删除和遍历操作
 * 创建作者：陈苗
 * 创建时间：2016年6月18日 16:11
 */
public class BinarySearchTreeTest {
    private BinaryTreeNode<Integer> root;
    /**
     * 构造函数
     */
    public BinarySearchTreeTest() {
        root = null;
    }

    /**
     * 二叉搜索树上的搜索函数，时间复杂度为O（logN）
     * @param key 需要查找的关键字
     * @return 符合查找条件的二叉树的节点
     */
    public BinaryTreeNode<Integer> find(int key) {
        BinaryTreeNode<Integer> current = root;
        if (current != null) {
            while (current.getData() != key) {
                if (key > current.getData())
                    current = current.getRightChild();
                else
                    current = current.getLeftChild();
                if (current == null)
                    return null;
            }
        }
        return current;
    }

    /**
     * 二叉搜索树上的插入函数，时间复杂度为O（logN）
     * @param data 需要插入的信息
     */
    public void insert(int data) {
        BinaryTreeNode<Integer> node = new BinaryTreeNode<Integer>(data,null,null);
        if (root == null)
            root = node;
        else {
            BinaryTreeNode<Integer> current = root;
            BinaryTreeNode<Integer> parent;
            while (true) {
                parent = current;
                if (data < current.getData()) {
                    current = current.getLeftChild();
                    if (current == null) {
                        parent.setLeftChild(node);
                        return;
                    }
                } else {
                    current = current.getRightChild();
                    if (current == null) {
                        parent.setRightChild(node);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 获取最小节点的函数
     * @return
     */
    public BinaryTreeNode<Integer> findMinNode() {
        BinaryTreeNode<Integer> current = root;
        BinaryTreeNode<Integer> parent = root;
        while (current != null) {
            parent = current;
            current = current.getLeftChild();
        }
        return parent;
    }
    /**
     * 获取最大节点的函数
     * @return
     */
    public BinaryTreeNode<Integer> findMaxNode() {
        BinaryTreeNode<Integer> current = root;
        BinaryTreeNode<Integer> parent = root;
        while (current != null) {
            parent = current;
            current = current.getRightChild();
        }
        return parent;
    }

    /**
     * 删除指定节点的函数
     * @param key 待删节点的值
     * @return 是否成功删除的标志
     */
    public boolean delete(int key) {
        BinaryTreeNode<Integer> current = root;
        BinaryTreeNode<Integer> parent = root;
        boolean isLeftChild = true;
        if (current == null)
            return false;
        //寻找要删除的节点
        while (current.getData() != key) {
            parent = current;
            if (key < current.getData()) {
                isLeftChild = true;
                current = current.getLeftChild();
            }
            else {
                isLeftChild = false;
                current = current.getRightChild();
            }
            if (current == null) {
                return false;
            }
        }
        if (current.getLeftChild() == null && current.getRightChild() == null) {
            //第一步：要删除的节点没有子节点，直接将其父节点的左子节点或者右子节点设置为NULL即可
            return deleteNoChild(parent,current,isLeftChild);
        } else if(current.getLeftChild() != null && current.getRightChild() != null) {
            //第二步：要删除的节点有两个子节点
            return deleteTwoChild(parent,current,isLeftChild);
        } else {
            //第三步：要删除的节点有一个子节点，直接将其砍断，将其子节点与父节点连起来即可，要考虑特殊情况就是删除根节点，因为根节点没有父节点
            return deleteOneChild(parent,current,isLeftChild);
        }
    }

    /**
     * 待删除节点有一个子节点的函数
     * @param parent 当前节点的父节点
     * @param current 当前待删节点
     * @param isLeftChild 属于左子树还是右子树的标志，若为true则为左子树，如果false则为右子树
     * @return 是否成功删除的结果
     */
    private boolean deleteOneChild(BinaryTreeNode<Integer> parent,BinaryTreeNode<Integer> current, boolean isLeftChild) {
        if (current.getLeftChild() == null) {
            if (current == root) {
                root = root.getRightChild();
                return true;
            }
            if (isLeftChild)
                parent.setLeftChild(current.getRightChild());
            else
                parent.setRightChild(current.getRightChild());
        }
        else {
            if (current == root) {
                root = root.getLeftChild();
                return true;
            }
            if (isLeftChild)
                parent.setLeftChild(current.getLeftChild());
            else
                parent.setRightChild(current.getRightChild());
        }
        return true;
    }

    /**
     * 获取待删除节点的后继节点
     * @param node 待删除节点
     * @return 其后继节点
     */
    public BinaryTreeNode<Integer> getNext(BinaryTreeNode<Integer> node) {
        BinaryTreeNode<Integer> next = node;
        BinaryTreeNode<Integer> parent = next;
        BinaryTreeNode<Integer> current = node.getRightChild();
        while (current != null) {
            parent = next;
            next = current;
            current = current.getLeftChild();
        }
        if (next != node.getRightChild()) {
            parent.setLeftChild(next.getRightChild());
            next.setRightChild(node.getRightChild());
        }
        return next;
    }
    /**
     * 待删除节点有左右子节点的函数
     * @param parent 当前节点的父节点
     * @param current 当前待删节点
     * @param isLeftChild 属于左子树还是右子树的标志，若为true则为左子树，如果false则为右子树
     * @return 是否成功删除的结果
     */
    private boolean deleteTwoChild(BinaryTreeNode<Integer> parent,BinaryTreeNode<Integer> current, boolean isLeftChild) {
        BinaryTreeNode<Integer> next = getNext(current);
        if (current == root) {
            next.setLeftChild(root.getLeftChild());
            if (next != root.getRightChild())
                next.setRightChild(root.getRightChild());
            root = next;
        } else if (isLeftChild) {
            parent.setLeftChild(next);
        } else {
            parent.setRightChild(next);
        }
        next.setLeftChild(current.getLeftChild());
        return false;
    }

    /**
     * 待删除节点为叶子节点的函数
     * @param parent 当前叶子节点的父节点
     * @param current 当前的叶子节点
     * @param isLeftChild 属于左子树还是右子树的标志，若为true则为左子树，如果false则为右子树
     * @return 是否成功删除的结果
     */
    private boolean deleteNoChild(BinaryTreeNode<Integer> parent,BinaryTreeNode<Integer> current, boolean isLeftChild) {
        if (root == null)
            return false;
        if (current == root) {
            root = null;
            return true;
        }
        if (isLeftChild) {
            parent.setLeftChild(null);
            return true;
        }
        else {
            parent.setRightChild(null);
            return true;
        }
    }

    /**
     * 前序遍历函数
     */
    public void previousOrderTraverse() {
        if (root != null) {
            root.previousTraversal(root);
        }
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchTreeTest test = new BinarySearchTreeTest();
        test.insert(5);
        test.insert(-3);
        test.insert(-2);
        test.insert(8);
        test.insert(6);
        test.insert(9);
        test.insert(1);
        System.out.println("前序遍历结果为：");
        test.previousOrderTraverse();
        test.delete(8);
        System.out.println("\n删除掉节点5之后，前序遍历结果为：");
        test.previousOrderTraverse();
    }
}
