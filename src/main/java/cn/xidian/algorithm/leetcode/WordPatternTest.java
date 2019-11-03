package cn.xidian.algorithm.leetcode;

import java.util.*;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: WordPatternTest
 * @description: 290. Word Pattern
 * @date 2019-11-03 19:22
 */
public class WordPatternTest {

    /**
     * check str whether it is satisfied with pattern or not
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern == null || "".equals(pattern)) {
            return false;
        }
        if (pattern.length() == 1 && words.length == 1) {
            return true;
        }
        Set<Character> patternC = new HashSet<>();
        for (char c : pattern.toCharArray()) {
            patternC.add(c);
        }
        if (patternC.size() > words.length) {
            return false;
        }
        Map<Character, List<Integer>> position = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (position.get(pattern.charAt(i)) == null) {
                List<Integer> set = new ArrayList<>();
                set.add(i);
                position.put(pattern.charAt(i), set);
                continue;
            }
            position.get(pattern.charAt(i)).add(i);
        }
        List<Integer> noDuplicate = new ArrayList<>();
        for (Map.Entry<Character, List<Integer>> entry : position.entrySet()) {
            List<Integer> value = entry.getValue();
            if (value.size() > str.length() || !isAllEqual(value, words)) {
                return false;
            }
            noDuplicate.add(value.get(0));
        }
        if (isAllEqual(noDuplicate, words)) {
            return false;
        }
        return true;
    }

    private boolean isAllEqual(List<Integer> positions, String[] words) {
        if (positions.size() > words.length) {
            return false;
        }
        Set<String> noDuplicate = new HashSet<>();
        for (Integer pos : positions) {
            noDuplicate.add(words[pos]);
        }
        return noDuplicate.size() == 1;
    }

    public static void main(String[] args) {
        System.out.println(new WordPatternTest().wordPattern("jquery", "jquery"));
    }
}
