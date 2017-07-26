package cn.xidian.algorithm.leetcode;

import java.util.*;

/**
 * 文件描述：按照所给单词分类化
 * 创建作者：陈苗
 * 创建时间：2016/11/25 19:10
 */
public class GroupAnagramsTest {
    /**
     * 获取同构单词分类的外部调用方法
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return null;
        Arrays.sort(strs);
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<List<String>> result = new ArrayList<List<String>>();
        for (String info : strs) {
            char[] chars = info.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (map.containsKey(key)) {
                List<String> value = map.get(key);
                value.add(info);
            } else {
                List<String> value = new ArrayList<String>();
                value.add(info);
                map.put(key, value);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String[] data = {"eat", "tea", "tan", "", "", "ate", "nat", "bat"};
        List<List<String>> result = new GroupAnagramsTest().groupAnagrams(data);
        for (List<String> info : result)
            System.out.println(Arrays.toString(info.toArray()));
    }
}
