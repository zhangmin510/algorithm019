/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        int backs = -1, backp = -1;
    	int si = 0, pi = 0;
    	while (si < s.length()) {
    		if (pi < p.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')) {
    			si++;
    			pi++;
    		} else {
    			if (pi < p.length() && p.charAt(pi) == '*') {
    				// skip mutiply '*'
    				while (pi < p.length() && p.charAt(pi) == '*')
    					pi++;
    				if (pi == p.length()) return true;
    				backs = si;
    				backp = pi;
    			} else {
    				//if '*' appeared before
    				if (backp != -1) {
    					si = ++backs;
    					pi = backp;
    				} else return false;
    			}
    		}
    	}
    	while (pi < p.length() && p.charAt(pi) == '*') pi++;
    	return (si == s.length() && pi == p.length()); 
    }
}
// @lc code=end

