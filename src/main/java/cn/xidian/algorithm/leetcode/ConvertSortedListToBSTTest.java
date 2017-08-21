package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;

/**
 * 文件描述：将已按照升序排列的单链表转换为高度平衡的二叉搜索树
 * 创建作者：陈苗
 * 创建时间：2017/8/21 10:46
 */
public class ConvertSortedListToBSTTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        ArrayList<ListNode> list = new ArrayList<>();
        list.add(head);
        return build(list, 0, count - 1);
    }

    private TreeNode build(ArrayList<ListNode> list, int from, int to) {
        if (from > to)
            return null;
        int mid = (from + to) / 2;
        TreeNode left = build(list, from, mid - 1);
        TreeNode root = new TreeNode(list.get(0).val);
        root.left = left;
        list.set(0, list.get(0).next);
        root.right = build(list, mid + 1, to);
        return root;
    }
}
