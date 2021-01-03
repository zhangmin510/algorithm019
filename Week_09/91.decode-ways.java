/*
 * @lc app=leetcode id=91 lang=java
 *
 * [91] Decode Ways
 */

// @lc code=start
class Solution {
    public int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') return 0;
        int pre = 0;
        int cur = 1;
        int i = cur;
        while (i <= s.length()) {
        	if (s.charAt(i-1) == '0') cur = 0;
        	if (i < 2 || !(s.charAt(i-2) == '1' || (s.charAt(i-2) == '2' && s.charAt(i-1) <= '6'))) pre = 0;
        	int tmp = cur;
        	cur = pre + cur;
        	pre = tmp;
        	i++;
        }
        return cur; 
    }
}
// @lc code=end

