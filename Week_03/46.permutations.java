/*
 * @lc app=leetcode id=46 lang=java
 *
 * [46] Permutations
 */

// @lc code=start
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> tmp = new ArrayList<>();

        for (int num : nums) {
            tmp.add(num);
        }

        dfs(nums.length, tmp, result, 0);

        return result;
    }

    private void dfs(int n, List<Integer> tmp, List<List<Integer>> result, int level) {
        if (level == n) {
            result.add(new ArrayList<Integer>(tmp));
        }

        for (int i = level; i < n; i++) {
            Collections.swap(tmp, level, i);
            dfs(n, tmp, result, level + 1);
            Collections.swap(tmp, i, level);
        }
    }
}
// @lc code=end

