package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：在给定链表中找出一个节点满足其前面的节点都比自己小，后面的节点都比自己大
 * 创建作者：陈苗
 * 创建时间：2017/4/23 20:38
 */
public class PartitionListTest {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 给定一个链表和一个数值，将此链表分成前端比x小，后一段不小于x
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode bhead = null, bigger = null;
        ListNode shead = null, smaller = null;
        ListNode node = head, temp;
        while (node != null) {
            temp = new ListNode(node.val);
            if (node.val < x) {
                if (smaller == null) {
                    smaller = temp;
                    shead = temp;
                } else {
                    smaller.next = temp;
                    smaller = smaller.next;
                }
            } else {
                if (bigger == null) {
                    bigger = temp;
                    bhead = temp;
                } else {
                    bigger.next = temp;
                    bigger = bigger.next;
                }
            }
            node = node.next;
        }
        if (shead == null)
            return bhead;
        if (bhead == null)
            return shead;
        smaller.next = bhead;
        return shead;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode one = new ListNode(4);
        ListNode two = new ListNode(3);
        ListNode three = new ListNode(2);
        ListNode four = new ListNode(5);
        ListNode five = new ListNode(2);

        head.next = one;
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        ListNode result = new PartitionListTest().partition(head, 3);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
