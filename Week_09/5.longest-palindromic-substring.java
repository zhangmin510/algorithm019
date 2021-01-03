/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 */

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {
		int n = s.length();
        boolean[][] dp = new boolean[n][n];
		String ans = "";
		
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
				}
				
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }

    public String longestPalindrome0(String s) {
    	if (s == null || s.length() == 1) return s;
    	int max = Integer.MIN_VALUE;
    	int start = 0;
    	int left = 0, right = 0;
    	int cur = 0;
    	for (int m = 0; m < s.length() - 1; m++) {
    		cur = 0;
    		if (s.charAt(m) == s.charAt(m + 1)) {
    			int t = m;
    			while (t < s.length() && s.charAt(m) == s.charAt(t)) t++;
    			t--;
    			left = m - 1;
    			right = t + 1;
    			cur += t - m + 1;
    			m += t - m;
    		} else {
    			left = m - 1;
    			right = m + 1;
    			cur = 1;
    		}
    		while (right < s.length() && left >= 0) {
    			if (s.charAt(right) == s.charAt(left)) cur += 2;
    			else break;
    			right++;
    			left--;
    		}
    		if (cur > max) {
    			max = cur;
    			start = left + 1;
    		}
    	}
    	
        return s.substring(start, start + max);
    }
}
// @lc code=end

