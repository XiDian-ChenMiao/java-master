package cn.xidian.algorithm.course;

/**
 * 文件描述：字典树
 * 创建作者：陈苗
 * 创建时间：2017/9/21 22:20
 */
public class TrieTree {
    private final static int CHAR_LEN = 26;

    static class TrieNode {
        boolean isStr;
        TrieNode[] next;

        public TrieNode() {
            next = new TrieNode[CHAR_LEN];
        }
    }

    /**
     * 向字典树中插入一个单词
     *
     * @param root
     * @param s
     */
    public void insert(TrieNode root, final String s) {
        if (root == null || s == null || "".equals(s))
            return;
        int length = s.length();
        TrieNode p = root;
        for (int i = 0; i < length; i++) {
            if (p.next[s.charAt(i) - 'a'] == null) {
                TrieNode node = new TrieNode();
                node.isStr = false;
                p.next[s.charAt(i) - 'a'] = node;
                p = node;
            } else {
                p = p.next[s.charAt(i) - 'a'];
            }
        }
        p.isStr = true;/*到单词结束的地方标志此处可以构造成一个单词*/
    }

    /**
     * 在字典树中查找一个单词
     *
     * @param root
     * @param s
     * @return
     */
    public boolean search(TrieNode root, final String s) {
        if (root == null || s == null || "".equals(s))
            return false;
        TrieNode p = root;
        int i = 0, len = s.length();
        while (p != null && i < len) {
            p = p.next[s.charAt(i) - 'a'];
            ++i;
        }
        return p != null && p.isStr == true;
    }

    /**
     * 清除字典树
     *
     * @param root
     */
    public void remove(TrieNode root) {
        for (int i = 0; i < CHAR_LEN; ++i) {
            if (root.next[i] != null) {
                remove(root.next[i]);
                root.next[i] = null;
            }
        }
    }

    public static void main(String[] args) {
        TrieNode root = new TrieNode();

        TrieTree tree = new TrieTree();
        tree.insert(root, "daqinzhidi");
        tree.insert(root, "daqinzhi");

        System.out.println(tree.search(root, "daqinzhi"));
        tree.remove(root);
    }
}
