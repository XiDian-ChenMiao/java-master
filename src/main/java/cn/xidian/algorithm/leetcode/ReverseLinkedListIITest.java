package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：将链表中指定区间内的节点反转
 * 创建作者：陈苗
 * 创建时间：2017/5/2 10:21
 */
public class ReverseLinkedListIITest {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
    * 反转指定区间节点的外部调用
    * @param head
    * @param m
    * @param n
    */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null)
            return null;
        ListNode p = head, pre = null;
        int cnt = 0, index = 1;
        while (p != null) {
            ++cnt;
            p = p.next;
        }
        if (m > n || n > cnt)
            return null;
        ListNode result = head;
        p = head;
        while (p != null && index < m) {
            pre = p;
            p = p.next;
            ++index;
        }
        ListNode front = pre;/*前半段*/
        ListNode midHead = p;
        while (p != null && index <= n) {
            pre = p;
            p = p.next;
            ++index;
        }
        pre.next = null;
        ListNode back = p;/*后半段*/
        ListNode midReverseHead = reverse(midHead);/*反转之后的中间半段*/
        if (front == null)
            result = midReverseHead;
        else
            front.next = midReverseHead;
        p = midReverseHead;
        while (p != null && p.next != null) {
            p = p.next;
        }
        p.next = back;
        return result;
    }

    /**
    * 反转链表
    * @param head
    */
    private ListNode reverse(ListNode head) {
        if (head != null && head.next == null)
            return head;
        ListNode reverseHead = null;
        ListNode p = head;
        ListNode pre = null;
        while (p != null) {
            ListNode pNext = p.next;
            if (pNext == null)
                reverseHead = p;
            p.next = pre;
            pre = p;
            p = pNext;
        }
        return reverseHead;
    }

    /**
     * 主程序
     * @param args
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        head.next = one;
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        ListNode result = new ReverseLinkedListIITest().reverseBetween(head, 2, 4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}