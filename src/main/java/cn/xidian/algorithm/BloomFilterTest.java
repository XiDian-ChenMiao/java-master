package cn.xidian.algorithm;

import org.junit.Assert;

import java.net.URL;
import java.util.BitSet;

/**
 * 文件描述：布隆过滤器
 * 创建作者：陈苗
 * 创建时间：2017/7/28 9:43
 */
public class BloomFilterTest {
    private final static int DEFAULT_SIZE = 1 << 25;
    private final static int[] SEEDS = {5, 7, 11, 13, 31, 37, 61};

    private BitSet bits = new BitSet(DEFAULT_SIZE);

    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    /**
     * 静态内部类
     */
    private static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String str) {
            int result = 0;
            int len = str.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + str.charAt(i);
            }
            return (cap - 1) & result;
        }
    }

    public BloomFilterTest() {
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加字符串
     * @param str
     */
    public void add(String str) {
        for (SimpleHash f : func)
            bits.set(f.hash(str), true);
    }

    /**
     * 是否包含字符串判断
     * @param str
     * @return
     */
    public boolean contains(String str) {
        if (str == null)
            return false;
        boolean result = true;
        for (SimpleHash f : func) {
            result = result && bits.get(f.hash(str));
        }
        return result;
    }

    /**
     * 主函数调用
     * @param args
     */
    public static void main(String[] args) {
        final String[] URLS = {
                "http://www.csdn.net/",
                "http://www.baidu.com/",
                "http://www.google.com.hk",
                "http://www.cnblogs.com/",
                "http://www.zhihu.com/",
                "https://www.shiyanlou.com/",
                "http://www.google.com.hk",
                "https://www.shiyanlou.com/",
                "http://www.csdn.net/"
        };

        BloomFilterTest filter = new BloomFilterTest();
        for (int i = 0; i < URLS.length; i++) {
            if (filter.contains(URLS[i])) {
                System.out.println("contains: " + URLS[i]);
                continue;
            }
            filter.add(URLS[i]);
        }
    }
}
