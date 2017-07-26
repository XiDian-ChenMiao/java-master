package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：
 * 创建作者：陈苗
 * 创建时间：2016/7/9 21:07
 */
public class AddTwoListTest {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 两个链表相加的函数
     * @param l1
     * @param l2
     * @return 相加之后构成的新的链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1,temp2 = l2;
        ListNode result = new ListNode(0);
        ListNode tempNode = result;
        ListNode node;
        int flag = 0;
        while(temp1 != null && temp2 != null) {
            node = new ListNode(0);
            node.val = (temp1.val + temp2.val + flag) % 10;
            flag = (temp1.val + temp2.val + flag) / 10;
            tempNode.next = node;
            tempNode = node;

            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        int value;
        if (temp1 == null && temp2 == null) {
            if(flag != 0)
            {
                node = new ListNode(0);
                node.val = flag;
                tempNode.next = node;
            }
        } else {
            while (temp1 != null) {
                value = (temp1.val + flag) % 10;
                flag = (temp1.val + flag) / 10;
                temp1.val = value;
                tempNode.next = temp1;

                tempNode = temp1;
                temp1 = temp1.next;
            }
            while (temp2 != null) {
                value = (temp2.val + flag) % 10;
                flag = (temp2.val + flag) / 10;
                temp2.val = value;
                tempNode.next = temp2;

                tempNode = temp2;
                temp2 = temp2.next;
            }
            if(flag != 0)
            {
                node = new ListNode(0);
                node.val = flag;
                tempNode.next = node;
            }
        }
        return result.next;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(9);
        node2.next = node3;
        ListNode result = new AddTwoListTest().addTwoNumbers(node1,node2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
