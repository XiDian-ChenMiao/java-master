package cn.algorithm.leetcode;

/**
 * 文件描述：旋转链表测试
 * 创建作者：陈苗
 * 创建时间：2016/12/2 16:47
 */
public class RotateListTest {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 旋转链表的外部调用函数
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        int length = 0;
        while (tail.next != null) {
            ++length;
            tail = tail.next;
        }
        length = length + 1;
        k = k % length;

        if (k == 0) {
            return head;
        }
        ListNode splitNode = head;
        int splitIndex = length - k - 1;
        while (splitIndex-- != 0) {
            splitNode = splitNode.next;
        }

        tail.next = head;
        head = splitNode.next;
        splitNode.next = null;

        return head;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);

        one.next = two;
        two.next = three;

        ListNode result = new RotateListTest().rotateRight(one, 2000000000);
        while (result != null) {
            System.out.print(result.val + "\t");
            result = result.next;
        }
    }
}
