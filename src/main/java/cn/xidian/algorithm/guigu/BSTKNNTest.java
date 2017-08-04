package cn.xidian.algorithm.guigu;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 文件描述：给定二叉搜索树和一个值，找出最接近的K个值
 * 创建作者：陈苗
 * 创建时间：2017/8/4 14:34
 */
public class BSTKNNTest {
    private static class Node {
        Node left, right;
        int data;
    }
    public void findKNN(Node root, int k, int target) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int aabs = Math.abs(a);
                int babs = Math.abs(b);
                if (babs > aabs)
                    return 1;
                else if (babs < aabs)
                    return -1;
                else
                    return 0;
            }
        };
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, comparator);
        this.findKNN(root, k, target, heap);
        for (Integer i : heap)
            System.out.println(i);
    }

    private void findKNN(Node root, int k, int target, PriorityQueue<Integer> heap) {
        if (root == null)
            return;
        findKNN(root.left, k, target, heap);
        if (heap.size() < k)
            heap.add(root.data - target);
        else {
            int diff = Math.abs(root.data - target);
            int maxdiff = Math.abs(heap.peek());
            if (maxdiff > diff) {
                heap.poll();
                heap.add(root.data - target);
            }
        }
        findKNN(root.right, k, target, heap);
    }
}
