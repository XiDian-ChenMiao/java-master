package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: TrieTest
 * @description: 208.前缀树
 * @date 2019-11-02 11:00
 */
public class TrieTest {
    private TrieTest[] child;
    private boolean isWord;/*word symbol*/
    /** Initialize your data structure here. */
    public TrieTest() {
        isWord = false;
        child = new TrieTest[26];
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieTest t = this;
        for (char c : word.toCharArray()) {
            if (t.child[c - 'a'] == null) {
                t.child[c - 'a'] = new TrieTest();
            }
            t = t.child[c - 'a'];
        }
        t.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieTest t = this;
        for (char c : word.toCharArray()) {
            if (t.child[c - 'a'] == null) {
                return false;
            }
            t = t.child[c - 'a'];
        }
        return t.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieTest t = this;
        for (char c : prefix.toCharArray()) {
            if (t.child[c - 'a'] == null) {
                return false;
            }
            t = t.child[c - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        TrieTest t = new TrieTest();
        t.insert("apple");
        System.out.println(t.search("apple"));
        System.out.println(t.search("app"));
        System.out.println(t.startsWith("app"));
    }
}
