package cn.xidian.other;

import java.util.HashMap;

/**
 * 文件描述：LRU的实现
 * 创建作者：陈苗
 * 创建时间：2017/4/10 17:46
 */
public class LRUCacheTest<K, V> {
    /**
     * 静态内部类
     * @param <K>
     * @param <V>
     */
    private static class Entry<K, V> {
        Entry pre;
        Entry next;
        K key;
        V value;
    }
    private final int MAX_CACHE_SIZE;/*最大缓存个数*/

    private Entry first;/*第一个元素的指针*/

    private Entry last;/*最后一个元素的指针*/

    private HashMap<K, Entry<K, V>> hashMap;

    /**
     * 构造函数
     * @param cacheSize
     */
    public LRUCacheTest(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        hashMap = new HashMap<K, Entry<K, V>>();
    }

    public void put(K key, V value) {
        Entry entry = getEntry(key);
        if (entry == null) {
            if (hashMap.size() >= MAX_CACHE_SIZE) {
                hashMap.remove(last.key);
                removeLast();
            }
            entry = new Entry();
            entry.key = key;
        }
        entry.value = value;
        moveToFirst(entry);
        hashMap.put(key, entry);
    }

    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) return null;
        moveToFirst(entry);
        return entry.value;
    }

    public void remove(K key) {
        Entry entry = getEntry(key);
        if (entry != null) {
            if (entry.pre != null) entry.pre.next = entry.next;
            if (entry.next != null) entry.next.pre = entry.pre;
            if (entry == first) first = entry.next;
            if (entry == last) last = entry.pre;
        }
        hashMap.remove(key);
    }

    private void moveToFirst(Entry entry) {
        if (entry == first) return;
        if (entry.pre != null) entry.pre.next = entry.next;
        if (entry.next != null) entry.next.pre = entry.pre;
        if (entry == last) last = last.pre;

        if (first == null || last == null) {
            first = last = entry;
            return;
        }

        entry.next = first;
        first.pre = entry;
        first = entry;
        entry.pre = null;
    }

    private void removeLast() {
        if (last != null) {
            last = last.pre;
            if (last == null) first = null;
            else last.next = null;
        }
    }

    private Entry<K, V> getEntry(K key) {
        return hashMap.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Entry entry = first;
        while (entry != null) {
            sb.append(String.format("%s:%s ", entry.key, entry.value));
            entry = entry.next;
        }
        return sb.toString();
    }

    /**
     * 主函数调用
     * @param args
     */
    public static void main(String[] args) {
        LRUCacheTest<Integer, String> test = new LRUCacheTest<Integer, String>(5);
        test.put(1, "西电");
        test.put(2, "西交");
        test.put(3, "西工大");
        test.put(4, "西大");
        System.out.println(test.toString());
        test.put(5, "西外");
        test.get(2);
        test.put(6, "西北政法");
        test.get(4);
        System.out.println(test.toString());
    }
}
