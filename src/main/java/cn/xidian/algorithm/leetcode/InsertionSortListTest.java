package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：通过插入排序排序链表
 * 创建作者：陈苗
 * 创建时间：2017/9/11 17:49
 */
public class InsertionSortListTest {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 通过插入排序排序一个链表
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode helper = new ListNode(0);
        ListNode pre = helper, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            pre = helper;
            while (pre.next != null && pre.next.val <= cur.val)
                pre = pre.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        return helper.next;
    }
}
