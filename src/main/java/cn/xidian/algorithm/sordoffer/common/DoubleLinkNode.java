package cn.xidian.algorithm.sordoffer.common;

/**
 * 文件描述：双向链表的节点类
 * 创建作者：陈苗
 * 创建时间：2016年6月8日 16:23
 */
public class DoubleLinkNode<T> {
    private T data;
    private DoubleLinkNode<T> previous;
    private DoubleLinkNode<T> next;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DoubleLinkNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleLinkNode<T> previous) {
        this.previous = previous;
    }

    public DoubleLinkNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleLinkNode<T> next) {
        this.next = next;
    }
}
