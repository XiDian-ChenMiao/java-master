package cn.algorithm.sordoffer.common;

import java.util.Stack;

/**
 * 文件描述：二叉树节点类
 * 创建作者：陈苗
 * 创建时间：2016年5月31日 16:13
 */
public class BinaryTreeNode<T> {
    private T data;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;

    public BinaryTreeNode(T data, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * 后续遍历二叉树节点
     */
    public void behindTraversal(BinaryTreeNode<T> root) {
        if(root != null) {
            behindTraversal(root.leftChild);
            behindTraversal(root.rightChild);
            System.out.print(root.data + " ");
        }
    }
    /**
     * 中序遍历二叉树节点
     * @param root 二叉树根节点
     */
    public void middleTraversal(BinaryTreeNode<T> root) {
        if(root != null) {
            middleTraversal(root.leftChild);
            System.out.print(root.data + " ");
            middleTraversal(root.rightChild);
        }
    }
    /**
     * 前序遍历二叉树节点
     * @param root 二叉树根节点
     */
    public void previousTraversal(BinaryTreeNode<T> root) {
        if(root != null) {
            System.out.print(root.data + " ");
            previousTraversal(root.leftChild);
            previousTraversal(root.rightChild);
        }
    }

    /**
     * 常规版先序遍历非递归版
     * @param t
     */
    public void preOrderTraverse(BinaryTreeNode<T> t) {
        Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
        while (t != null || (stack.size() != 0)) {
            while (t != null) {
                stack.push(t);
                System.out.print(t.data + " ");
                t = t.leftChild;
            }
            if (stack.size() != 0) {
                t = stack.pop();
                t = t.rightChild;
            }
        }
    }

    /**
     * 非递归版先序遍历算法
     * @param t
     */
    public void preOrderTraverseAnother(BinaryTreeNode<T> t) {
        if (t == null)
            return;
        Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
        stack.push(t);
        while (stack.size() != 0) {
            BinaryTreeNode<T> temp = stack.pop();
            System.out.print(temp.data + " ");
            if (temp.rightChild != null) {
                stack.push(temp.rightChild);
            }
            if (temp.leftChild != null) {
                stack.push(temp.leftChild);
            }
        }
    }

    /**
     * 中序非递归遍历
     * @param t
     */
    public void midOrderTraverse(BinaryTreeNode<T> t) {
        Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
        while (t != null || stack.size() != 0) {
            while (t != null) {
                stack.push(t);
                t = t.leftChild;
            }
            t = stack.pop();
            System.out.print(t.data + " ");
            t = t.rightChild;
        }
    }

    /**
     * 非递归后序遍历
     * @param t
     */
    public void postOrderTraverse(BinaryTreeNode<T> t) {
        Stack<BinaryTreeNode<T>> one = new Stack<BinaryTreeNode<T>>();
        Stack<BinaryTreeNode<T>> two = new Stack<BinaryTreeNode<T>>();
        BinaryTreeNode<T> cur;
        one.push(t);
        while (one.size() != 0) {
            cur = one.pop();
            two.push(cur);
            if (cur.leftChild != null)
                one.push(cur.leftChild);
            if (cur.rightChild != null)
                one.push(cur.rightChild);
        }
        while (two.size() != 0) {
            System.out.print(two.pop().data + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> node9 = new BinaryTreeNode<Integer>(9,null,null);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(2,null,null);
        BinaryTreeNode<Integer> node8 = new BinaryTreeNode<Integer>(8,node9,node2);
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(1,null,null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(10,node8,node1);
        System.out.print("常规先序遍历：");
        root.preOrderTraverse(root);
        System.out.print("\n非常规先序遍历：");
        root.preOrderTraverseAnother(root);
        System.out.print("\n常规中序遍历：");
        root.midOrderTraverse(root);
        System.out.print("\n常规后序遍历：");
        root.postOrderTraverse(root);
    }
}
