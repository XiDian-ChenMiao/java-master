package cn.designpattern;

import java.util.Iterator;
import java.util.Vector;

/**
 * 容器接口
 */
interface IContainer {
    void add(Object obj);
    void remove(Object obj);
    @SuppressWarnings("rawtypes")
	Iterator iterator();
}

/**
 * 具体的迭代器类
 */
@SuppressWarnings("rawtypes")
class ConcreteIterator implements Iterator {
    private Vector<Object> vector = new Vector<Object>();
    private int cursor = 0;//默认当前的索引位置

    /**
     * 构造函数
     * @param vector
     */
    public ConcreteIterator(Vector<Object> vector) {
        this.vector = vector;
    }

    @Override
    public boolean hasNext() {
        return this.cursor == this.vector.size() ? false : true;
    }

    @Override
    public Object next() {
        Object result = null;
        if(this.hasNext())
            result = this.vector.get(this.cursor++);
        return result;
    }

    @Override
    public void remove() {
        this.vector.remove(this.cursor);
    }
}
/**
 * 具体的容器类
 */
class ConcreteContainer implements IContainer {
    private Vector<Object> vector = new Vector<Object>();
    @Override
    public void add(Object obj) {
        this.vector.add(obj);
    }

    @Override
    public void remove(Object obj) {
        this.vector.remove(obj);
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Iterator iterator() {
        return new ConcreteIterator(this.vector);
    }
}
/**
 * 迭代器模式客户端测试类
 * 创建作者：陈苗
 * 创建时间：2016年5月18日 22:39
 */
public class IteratorPatternTest {
    public static void main(String[] args) {
        IContainer container = new ConcreteContainer();
        container.add("金麟岂是池中物，一遇风云变化龙");
        container.add("九霄龙吟惊天变，风云际会浅水游");

        @SuppressWarnings("rawtypes")
		Iterator iterator = container.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
