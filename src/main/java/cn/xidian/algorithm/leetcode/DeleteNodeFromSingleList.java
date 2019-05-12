package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: DeleteNodeFromSingleList
 * @description: 从单链表中删除任何给定要求的链表节点
 * @date 2019-05-12 22:27
 */
public class DeleteNodeFromSingleList {
    /**
     * 删除给定节点
     * NOTE: 相当于对该节点之后的节点进行逻辑复制，并控制指针的指向
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
