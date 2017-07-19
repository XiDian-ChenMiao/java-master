package cn.algorithm.leetcode;

/**
 * 文件描述：正则表达式匹配
 * 创建作者：陈苗
 * 创建时间：2016/7/13 17:52
 */
public class RegularExpressionTest {
    /**
     * 判断是否匹配的外部调用接口
     * @param string 给定字符串
     * @param pattern 给定的匹配
     * @return 是否成功匹配结果
     */
    public boolean isMatch(String string, String pattern) {

        return false;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        /**
         * isMatch("aa","a") → false
         * isMatch("aa","aa") → true
         * isMatch("aaa","aa") → false
         * isMatch("aa", "a*") → true
         * isMatch("aa", ".*") → true
         * isMatch("ab", ".*") → true
         * isMatch("aab", "c*a*b") → true
         */
    }
}
