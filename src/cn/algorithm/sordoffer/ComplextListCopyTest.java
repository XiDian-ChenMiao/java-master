package cn.algorithm.sordoffer;

/**
 * 文件描述：复杂链表复制类
 * 创建作者：陈苗
 * 创建时间：2016年6月7日 16:34
 */
public class ComplextListCopyTest {
    /**
     * 复杂链表节点类
     * @param <T> 节点数据域类型
     */
    final static class ComplextListNode<T> {
        private T data;
        private ComplextListNode<T> next;//指向下一个节点的引用
        private ComplextListNode<T> sibling;//指向任意一个节点或者NULL的引用

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public ComplextListNode<T> getNext() {
            return next;
        }

        public void setNext(ComplextListNode<T> next) {
            this.next = next;
        }

        public ComplextListNode<T> getSibling() {
            return sibling;
        }

        public void setSibling(ComplextListNode<T> sibling) {
            this.sibling = sibling;
        }
    }

    /**
     * 获取拷贝副本的接口函数
     * @param head
     * @return
     */
    public ComplextListNode<Integer> getClone(ComplextListNode<Integer> head) {
        cloneNodes(head);
        connectNodes(head);
        return reconnectNodes(head);
    }

    /**
     * 拷贝出副本节点
     * @param head
     */
    private void cloneNodes(ComplextListNode<Integer> head) {
        ComplextListNode<Integer> node = head;
        while (node != null) {
            ComplextListNode<Integer> clone = new ComplextListNode<Integer>();
            clone.setData(node.getData());
            clone.setNext(node.getNext());
            clone.setSibling(null);
            node.setNext(clone);
            node = clone.getNext();
        }
    }

    /**
     * 连接副本节点
     * @param head
     */
    private void connectNodes(ComplextListNode<Integer> head) {
        ComplextListNode<Integer> node = head;
        while (node != null) {
            ComplextListNode<Integer> clone = node.getNext();
            if (node.getSibling() != null)
                clone.setSibling(node.getSibling().getNext());
            node = clone.getNext();
        }
    }

    /**
     * 重新拆分出副本链表
     * @param head
     * @return
     */
    private ComplextListNode<Integer> reconnectNodes(ComplextListNode<Integer> head) {
        ComplextListNode<Integer> node = head;
        ComplextListNode<Integer> cloneHead = null;
        ComplextListNode<Integer> cloneNode = null;
        if (node != null) {
            cloneHead = node.getNext();
            cloneNode = node.getNext();
            node.setNext(cloneNode.getNext());
            node = node.getNext();
        }
        while (node != null) {//如果链表中至少存在两个节点，则考虑拆分的问题
            cloneNode.setNext(node.getNext());
            cloneNode = cloneNode.getNext();
            node.setNext(cloneNode.getNext());
            node = node.getNext();
        }
        return cloneHead;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        ComplextListNode<Integer> node1 = new ComplextListNode<Integer>();
        node1.setData(1);
        ComplextListNode<Integer> node3 = new ComplextListNode<Integer>();
        node3.setData(3);
        ComplextListNode<Integer> node5 = new ComplextListNode<Integer>();
        node5.setData(5);
        ComplextListNode<Integer> node7 = new ComplextListNode<Integer>();
        node7.setData(7);

        node1.setNext(node3);
        node3.setNext(node5);
        node5.setNext(node7);

        node1.setSibling(node5);
        node3.setSibling(node7);

        ComplextListNode<Integer> node = new ComplextListCopyTest().getClone(node1);
        while (node != null) {
            System.out.print("当前节点值为：" + node.getData());
            if (node.getSibling() != null)
                System.out.println("，其连接的任意节点的值为：" + node.getSibling().getData());
            else System.out.println();
            node = node.getNext();
        }
    }
}
