package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：通过使用O（nlgn）时间复杂度和O（1）空间复杂度对链表进行排序
 * 创建作者：陈苗
 * 创建时间：2017/9/11 21:10
 */
public class SortListTest {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode p = head, q = head, pre = null;
        while (q != null && q.next != null) {
            q = q.next.next;
            pre = p;
            p = p.next;
        }
        pre.next = null;
        ListNode lhalf = mergeSort(head);
        ListNode rhalf = mergeSort(p);
        return merge(lhalf, rhalf);
    }

    private ListNode merge(ListNode lhalf, ListNode rhalf) {
        ListNode temp = new ListNode(0);
        ListNode p = temp;
        while (lhalf != null && rhalf != null) {
            if (lhalf.val <= rhalf.val) {
                p.next = lhalf;
                lhalf = lhalf.next;
            } else {
                p.next = rhalf;
                rhalf = rhalf.next;
            }
            p = p.next;
        }
        if (lhalf == null)
            p.next = rhalf;
        else
            p.next = lhalf;
        return temp.next;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode one = new ListNode(1);
        root.next = two;
        two.next = one;

        ListNode result = new SortListTest().sortList(root);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
