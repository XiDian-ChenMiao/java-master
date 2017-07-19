package cn.algorithm.leetcode;

/**
 * 文件描述：从链表的尾部移除指定位置的节点
 * 创建作者：陈苗
 * 创建时间：2016/10/18 10:51
 */
public class RemoveNodeFromEndTest {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 从尾部移除指定节点的外部调用函数
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return null;
        ListNode s = new ListNode(Integer.MAX_VALUE);/*充当哨兵*/
        s.next = head;

        ListNode first = s;
        int count = 0;
        while (first != null) {
            ++count;
            first = first.next;
        }
        if (count == 2 && n == 1)
            return null;
        first = head;
        ListNode pre = s;
        int location = count - n + 1, i = 2;
        while (i != location) {
            pre = first;
            first = first.next;
            ++i;
        }
        pre.next = first.next;
        return s.next;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode result = new RemoveNodeFromEndTest().removeNthFromEnd(head, 1);
        while (result != null) {
            System.out.println(result.val + "\t");
            result = result.next;
        }
    }
}
