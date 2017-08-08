package cn.xidian.algorithm.guigu;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 文件描述：按照Z型序遍历二叉树
 * 创建作者：陈苗
 * 创建时间：2017/8/7 18:55
 */
public class ZagLevelTreeTest {
    private static class Node {
        Node left, right;
        int data;
    }

    public ArrayList<ArrayList<Integer>> zigSequence(Node root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        LinkedList<Node> curLevel = new LinkedList<>();
        curLevel.add(root);
        boolean leftToRight = true;
        while (curLevel.size() > 0) {
            ArrayList<Integer> curList = new ArrayList<>();
            LinkedList<Node> nextLevel = new LinkedList<>();
            while (curLevel.size() > 0) {
                Node node = curLevel.poll();
                if (node.left != null)
                    nextLevel.add(node.left);
                if (node.right != null)
                    nextLevel.add(node.right);
                if (leftToRight)
                    curList.add(node.data);
                else
                    curList.add(0, node.data);
            }
            result.add(curList);
            curLevel = nextLevel;
            leftToRight = !leftToRight;
        }
        return result;
    }
}
