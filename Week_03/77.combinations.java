import java.awt.List;

/*
 * @lc app=leetcode id=77 lang=java
 *
 * [77] Combinations
 */

// @lc code=start
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> tmp = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(1, n, k, tmp, result);
        return result;
    }

    public void dfs(int cur, int n, int k, List<Integer> tmp, List<List<Integer>> result) {
        if (tmp.size() + (n - cur + 1) < k) {
            return;
        }

        if (tmp.size() == k) {
            result.add(new ArrayList<Integer>(tmp));
            return;
        }

        tmp.add(cur);
        dfs(cur + 1, n, k, tmp, result);
        tmp.remove(tmp.size() - 1);
        dfs(cur + 1, n, k, tmp, result);
    }
}
// @lc code=end

