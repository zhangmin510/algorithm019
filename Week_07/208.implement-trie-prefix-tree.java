/*
 * @lc app=leetcode id=208 lang=java
 *
 * [208] Implement Trie (Prefix Tree)
 */

// @lc code=start
class Trie {
    private TreeNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode p = root;
        for (int i = 0, len = word.length(), ch; i < len; i++) {
            ch = word.charAt(i) - 'a';
            if (p.next[ch] == null) {
                p.next[ch] = new TreeNode();
            }
            p = p.next[ch];
        }
        p.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode p = root;
        for (int i = 0, len = word.length(), ch; i < len; i++) {
            ch = word.charAt(i) - 'a';
            if (p.next[ch] == null) {
                return false;
            }
            p = p.next[ch];
        }
        return p.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode p = root;
        for (int i = 0, len = prefix.length(), ch; i < len; i++) {
            ch = prefix.charAt(i) - 'a';
            if (p.next[ch] == null) {
                return false;
            }
            p = p.next[ch];
        }
        return true;
    }

    private class TreeNode {
        private boolean isEnd;
        private TreeNode[] next;

        public TreeNode() {
            isEnd = false;
            next = new TreeNode[26];
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
// @lc code=end

