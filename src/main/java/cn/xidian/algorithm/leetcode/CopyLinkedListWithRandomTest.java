package cn.xidian.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: CopyLinkedListWithRandomTest
 * @description: 138. Copy List with Random Pointer
 * @date 2019-11-02 13:49
 */
public class CopyLinkedListWithRandomTest {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    /**
     * deep copy method with linked list
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node node = head;
        Map<Node, Node> map = new HashMap<>();
        while (node != null) {
            map.put(node, new Node(node.val, null, null));
            node = node.next;
        }
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }
}
