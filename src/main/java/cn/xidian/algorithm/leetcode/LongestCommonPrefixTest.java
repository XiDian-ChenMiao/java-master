package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：求所有给定字符串的公共前缀
 * 创建作者：陈苗
 * 创建时间：2016/10/17 15:49
 */
public class LongestCommonPrefixTest {
    /**
     * 最长公共前缀外部调用函数
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null)
            return null;
        if (strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        int shortest_length = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < strs.length; i++) {
            int length = strs[i].length();
            if (length == 0)
                return "";
            if (shortest_length < length) {
                shortest_length = length;
                index = i;
            }
        }
        boolean stop_flag = false;
        String result = "";
        int start = 0, end = 1, limit = strs[index].length();
        while (end <= limit) {
            String sub = strs[index].substring(start, end);
            for (String data : strs) {
                if (!data.startsWith(sub)) {
                    stop_flag = true;
                    break;
                }
            }
            if (!stop_flag)
                result = sub;
            ++end;
        }
        return result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String[] data = {"a", "cb", "ac"};
        System.out.println("公共前缀为：" + new LongestCommonPrefixTest().longestCommonPrefix(data));
    }
}
