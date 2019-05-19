package cn.xidian.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: KthSmallestInBinarySearchTreeTest
 * @description: 230.二叉搜索树中查找第K小的元素
 * @date 2019-05-19 08:42
 */
public class KthSmallestInBinarySearchTreeTest {
    /**
     * 通过递归计算左右子树的节点树来获取元素
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        int leftCnt = treeNodesCnt(root.left);
        if (leftCnt == k - 1) {
            return root.val;
        }
        if (leftCnt > k - 1) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - leftCnt - 1);
        }
    }

    private int treeNodesCnt(TreeNode root) {
        return root == null ? 0 : 1 + treeNodesCnt(root.left) + treeNodesCnt(root.right);
    }

    /**
     * 利用队列来获取元素
     * @param root
     * @param k
     * @return
     */
    public int kthSmallestByNoRecursion(TreeNode root, int k) {
        Queue<Integer> queue = new LinkedList<Integer>();
        offerToQueue(root, queue);
        int cnt = 1;
        while (!queue.isEmpty()) {
            int value = queue.poll().intValue();
            if (cnt == k) {
                return value;
            }
            cnt += 1;
        }
        return 0;
    }

    private void offerToQueue(TreeNode root, Queue<Integer> queue) {
        if (root != null) {
            offerToQueue(root.left, queue);
            queue.offer(root.val);
            offerToQueue(root.right, queue);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(3);

        System.out.println(new KthSmallestInBinarySearchTreeTest().kthSmallest(treeNode, 2));
        System.out.println(new KthSmallestInBinarySearchTreeTest().kthSmallestByNoRecursion(treeNode, 2));
    }
}
