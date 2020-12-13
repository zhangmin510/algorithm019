/*
 * @lc app=leetcode id=32 lang=java
 *
 * [32] Longest Valid Parentheses
 */

// @lc code=start
class Solution {
    public int longestValidParentheses(String s) {
        int max = 0;
        int last = -1;
        Stack<Integer> stack = new Stack<Integer>();
        char c = ' ';
        for (int i = 0; i < s.length(); i++) {
        	c = s.charAt(i);
        	if (c == '(') stack.push(i);
        	else {
        		if (stack.isEmpty()) last = i; // no mathching left
        		else {
        			//find a matching pair
        			stack.pop();
        			if (stack.isEmpty()) max = Math.max(max, i - last);
        			else {
        				
        				max = Math.max(max, i - stack.peek());
        			}
        		}
        	}
        }
        return max; 
    }
}
// @lc code=end

