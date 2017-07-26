package cn.xidian.algorithm;

import cn.xidian.algorithm.sordoffer.common.LinkNode;

/**
 * 文件描述：判断给定的链表中是否出现环，若出现，则找出环的入口节点
 * 创建作者：陈苗
 * 创建时间：2016年6月14日 9:19
 */
public class CircleInListTest {
    /**
     * 判断给定链表中是否出现环的接口调用函数
     * @param head 链表的头结点
     * @return 如果找到，则返回入口节点；如果未找到，则返回null
     */
    public LinkNode<Integer> hasCircle(LinkNode<Integer> head) {
        if (head == null)
            return null;
        LinkNode<Integer> slow = head,fast = head;//设置快慢指针用于搜索单链表中的环
        while (fast != null) {
            if (fast.getNext() != null)
                fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (slow == fast)
                break;//如果快慢指针出现了碰撞，则说明存在环
        }
        if (fast == null)
            return null;
        else {
            LinkNode<Integer> collisionNode = fast;//找到了快慢指针的碰撞节点
            LinkNode<Integer> node = head;
            while (node != collisionNode){
                node = node.getNext();
                collisionNode = collisionNode.getNext();
            }
            return node;//根据定理得知，碰撞节点到连接点的距离与链表头结点到连接点的距离相等
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        LinkNode<Integer> node1 = new LinkNode<Integer>(1,null);
        LinkNode<Integer> node3 = new LinkNode<Integer>(3,null);
        LinkNode<Integer> node5 = new LinkNode<Integer>(5,null);
        LinkNode<Integer> node7 = new LinkNode<Integer>(7,null);
        LinkNode<Integer> node6 = new LinkNode<Integer>(6,null);
        LinkNode<Integer> node8 = new LinkNode<Integer>(8,null);
        LinkNode<Integer> node11 = new LinkNode<Integer>(11,null);
        node1.setNext(node3);
        node3.setNext(node5);
        node5.setNext(node7);
        node7.setNext(node6);
        node6.setNext(node8);
        node8.setNext(node11);
        node11.setNext(node6);

        System.out.println(new CircleInListTest().hasCircle(node1).getData());
    }
}
