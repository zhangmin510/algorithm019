/*
 * @lc app=leetcode id=115 lang=java
 *
 * [115] Distinct Subsequences
 */

// @lc code=start
class Solution {
    public int numDistinct(String s, String t) {
        int[] dp = new int[t.length() + 1];
        dp[0] = 1;
        
    	for (int i = 0; i < s.length(); i++)
    		for (int j = t.length() - 1; j >= 0; j--)
                dp[j + 1] += s.charAt(i) == t.charAt(j) ? dp[j] : 0;
        
        return dp[t.length()];
    }
}
// @lc code=end

