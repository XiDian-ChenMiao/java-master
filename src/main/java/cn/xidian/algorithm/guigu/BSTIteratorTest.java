package cn.xidian.algorithm.guigu;

import java.util.Iterator;
import java.util.Stack;

/**
 * 文件描述：二叉搜索树的迭代器
 * 创建作者：陈苗
 * 创建时间：2017/8/4 14:51
 */
public class BSTIteratorTest implements Iterator<Integer> {
    private static class Node {
        Node left, right;
        int data;
    }
    private Stack<Node> stack = new Stack<>();

    public BSTIteratorTest(Node root) {
        pushLeft(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        Node p = stack.pop();
        pushLeft(p.right);
        return p.data;
    }

    private void pushLeft(Node p) {
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
    }
}
