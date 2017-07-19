package cn.algorithm.leetcode;

/**
 * 文件描述：合并两个有序链表
 * 创建作者：陈苗
 * 创建时间：2016/10/18 20:11
 */
public class MergeSortedListTest {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 合并两个有序链表的外部调用函数
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode main = new ListNode(Integer.MAX_VALUE);
        ListNode l1_temp = l1, l2_temp = l2;
        ListNode head = main;
        if (l1 == null && l2 == null)
            return null;
        else if (l1 == null && l2 != null)
            return l2;
        else if (l1 != null && l2 == null)
            return l1;
        else {
            while (l1_temp != null && l2_temp != null) {
                if (l1_temp.val <= l2_temp.val) {
                    main.next = l1_temp;
                    l1_temp = l1_temp.next;
                } else {
                    main.next = l2_temp;
                    l2_temp = l2_temp.next;
                }
                main = main.next;
            }
            while (l1_temp != null) {
                main.next = l1_temp;
                main = main.next;
                l1_temp = l1_temp.next;
            }
            while (l2_temp != null) {
                main.next = l2_temp;
                main = main.next;
                l2_temp = l2_temp.next;
            }
        }
        return head.next;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        ListNode l1_one = new ListNode(1);
        ListNode l1_two = new ListNode(3);
        ListNode l1_three = new ListNode(5);
        l1_one.next = l1_two;
        l1_two.next = l1_three;

        ListNode l2_one = new ListNode(2);
        ListNode l2_two = new ListNode(4);
        ListNode l2_three = new ListNode(5);
        ListNode l2_four = new ListNode(6);
        l2_one.next = l2_two;
        l2_two.next = l2_three;
        l2_three.next = l2_four;
        ListNode result = new MergeSortedListTest().mergeTwoLists(l1_one, l2_one);
        while (result != null) {
            System.out.println(result.val + "\t");
            result = result.next;
        }
    }
}
