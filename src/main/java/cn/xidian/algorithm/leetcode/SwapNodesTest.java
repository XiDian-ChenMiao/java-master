package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：
 * 创建作者：陈苗
 * 创建时间：2016/10/20 21:00
 */
public class SwapNodesTest {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 交换节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        ListNode front = head, back = head.next;
        ListNode main = new ListNode(Integer.MAX_VALUE);
        main.next = head;
        ListNode pre = main, temp;
        while (front != null && back != null) {
            pre.next = back;
            front.next = back.next;
            back.next = front;
            /*交换两个指针使满足front在back之前*/
            temp = front;
            front = back;
            back = temp;

            if (pre.next != null)
                pre = pre.next.next;
            if (front.next != null)
                front = front.next.next;
            if (back.next != null)
                back = back.next.next;
        }
        return main.next;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode one = new ListNode(2);
        ListNode two = new ListNode(3);

        head.next = one;
        one.next = two;

        ListNode result = new SwapNodesTest().swapPairs(head);
        while (result != null) {
            System.out.print(result.val + "\t");
            result = result.next;
        }
    }
}
