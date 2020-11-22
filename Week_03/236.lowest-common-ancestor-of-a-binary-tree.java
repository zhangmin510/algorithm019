/*
 * @lc app=leetcode id=236 lang=java
 *
 * [236] Lowest Common Ancestor of a Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private TreeNode ans = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }

        boolean isLeft = dfs(root.left, p, q);
        boolean isRight = dfs(root.right, p, q);
        if ((isLeft && isRight) || ((root.val == p.val || root.val == q.val) && (isLeft || isRight))) {
            ans = root;
        }

        return isLeft || isRight || (root.val == p.val || root.val == q.val);
    }  
}
// @lc code=end

