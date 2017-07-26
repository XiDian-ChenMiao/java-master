package cn.xidian.algorithm;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 文件描述：不带虚拟节点的一致性Hash算法实现
 * 创建作者：陈苗
 * 创建时间：2017/4/27 21:29
 */
public class ConsistentHashingWithoutVirtualNode {

    /**
     * 待添加入Hash环的服务器列表
     */
    private static String[] servers = {
            "192.168.0.0:111",
            "192.168.0.1:111",
            "192.168.0.2:111",
            "192.168.0.3:111",
    };

    /*key表示服务器的hash值，value表示服务器的名称*/
    private static SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();
    static {
        for (int i = 0; i < servers.length; ++i) {
            int hash = getHash(servers[i]);
            System.out.println("[" + servers[i] + "]加入到集合中，其Hash值为：" + hash);
            sortedMap.put(hash, servers[i]);
        }
    }

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     * @param server
     * @return
     */
    private static int getHash(String server) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < server.length(); ++i)
            hash = (hash ^ server.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    /**
     * 获取应当路由到的节点
     * @param node
     * @return
     */
    private static String getServer(String node) {
        int hash = getHash(node);/*获取该路由的节点hash值*/
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);/*获取大于该key的所有映射*/
        Integer i = subMap.firstKey();/*第一个key就是顺时针过去离node最近的那个节点*/
        return subMap.get(i);/*返回对应的服务器名称*/
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String[] nodes = {
                "127.0.0.1:100",
                "221.226.0.1:101",
                "10.211.0.1:102"
        };

        for (int i = 0; i < nodes.length; i++) {
            System.out.println("[" + nodes[i] + "]的hash值为：" + getHash(nodes[i]) + "，被路由到节点" + getServer(nodes[i]) + "]");
        }
    }
}
