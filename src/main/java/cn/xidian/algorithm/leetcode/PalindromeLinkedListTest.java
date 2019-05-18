package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: PalindromeLinkedListTest
 * @description: 234.回文链表
 * @date 2019-05-18 22:06
 */
public class PalindromeLinkedListTest {
    /**
     * 用快慢指针找到单链表的中间节点，然后反转中间节点之后的所有节点，然后逐一对比即可
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverse(slow.next);
        while (slow != null) {
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    private ListNode reverse(ListNode node) {
        if (node.next == null) {
            return node;/*返回反转之后链表的头节点*/
        }
        ListNode newHead = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }
}
