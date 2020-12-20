/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 */

// @lc code=start
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<String>();
    	genP1(ret, "", 0, 0, n);
    	return ret;
    }
    private void genP1(List<String> res, String tmp, int left, int right, int n) {
    	if (left < right) return;
    	if (left + right == 2 * n) {
    		if (left == right) res.add(tmp);
    		return;
    	}
    	genP1(res,tmp + "(", left + 1, right, n);
    	genP1(res,tmp + ")", left, right + 1, n);
    	
    }
}
// @lc code=end

