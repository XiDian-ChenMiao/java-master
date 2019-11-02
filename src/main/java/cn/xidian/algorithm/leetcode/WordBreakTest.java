package cn.xidian.algorithm.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: WordBreakTest
 * @description: 139. Word Break
 * @date 2019-11-02 14:25
 */
public class WordBreakTest {
    /**
     * word break
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        /*canBreak[i]表示s中以i-1位置结尾的字符串是否可被切分*/
        boolean[] canBreak = new boolean[n + 1];
        canBreak[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (canBreak[j] && wordDict.contains(s.substring(j, i))) {
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[n];
    }

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        WordBreakTest t = new WordBreakTest();
        System.out.println(t.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
    }
}
