package cn.xidian.thread;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 文件描述：通过读写锁实现简单的缓存系统
 * 创建作者：陈苗
 * 创建时间：2016年6月5日 20:37
 */
public class CacheByLockTest {
    private CacheByLockTest() {}
    private final static CacheByLockTest cacheContainer = new CacheByLockTest();
    private Map<String,Object> cache = Collections.synchronizedMap(new Hashtable<String, Object>());
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 获取对象的单例方法
     * @return
     */
    public final static CacheByLockTest getInstance() {
        return cacheContainer;
    }

    /**
     * 获取数据的方法
     * @param key
     * @return
     */
    public Object get(String key) {
        lock.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
            if (value == null) {
                lock.readLock().unlock();
                lock.writeLock().lock();
                try {
                    if (value == null)//如果第一个线程的读锁解锁（说明已经有数据），但是第二三个线程又想写，所以需要再次判断
                        value = "这是数据";//需要从其他的存储介质中获取
                    cache.put(key, value);
                } finally {
                    lock.writeLock().unlock();
                }
                lock.readLock().lock();
            }
        } finally {
            lock.readLock().unlock();
        }
        return value;
    }
}
