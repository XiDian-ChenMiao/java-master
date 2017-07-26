package cn.xidian.algorithm.sordoffer.common;

/**
 * 文件描述：建立单向链表节点的泛型类
 * 创建作者：陈苗
 * 创建时间：2016年5月31日 15:34
 */
public class LinkNode<T> {
    private T data;
    private LinkNode<T> next;

    /**
     * 构造函数
     * @param data 节点信息
     * @param next 下一个节点引用
     */
    public LinkNode(T data, LinkNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkNode<T> getNext() {
        return next;
    }

    public void setNext(LinkNode<T> next) {
        this.next = next;
    }
}
