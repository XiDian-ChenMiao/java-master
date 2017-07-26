package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：在有序单链表中删除重复节点
 * 创建作者：陈苗
 * 创建时间：2017/4/23 20:20
 */
public class RemoveDuplicatesNodeTest {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 删除重复节点
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        ListNode node = head;
        ListNode next = head.next;
        while (next != null) {
            if (node.val == next.val) {
                node.next = next.next;
                next = node.next;
            } else {
                node = next;
                next = next.next;
            }
        }
        return head;
    }

    /**
     * 主函数调用
     * @param args
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode one = new ListNode(2);
        ListNode two = new ListNode(3);
        ListNode three = new ListNode(4);
        head.next = one;
        one.next = two;
        two.next = three;

        ListNode result = new RemoveDuplicatesNodeTest().deleteDuplicates(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
