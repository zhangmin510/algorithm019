/*
 * @lc app=leetcode id=8 lang=java
 *
 * [8] String to Integer (atoi)
 */

// @lc code=start
class Solution {
   public int myAtoi(String str) {
        if (null == str || str.equals("")) return 0;
           
		char c;
		boolean negative = false;
		int len = str.length();
		int i = 0;
		int ret = 0;
        while (i < len && Character.isWhitespace(str.charAt(i))) i++;
        
		c = str.charAt(i);
		if (c == '+' || c == '-') {
			if (c == '-') {
				negative = true;
			}
			i += 1;
        }
        
		if (Character.isDigit(str.charAt(i))) {
			while (i < len) {
				c = str.charAt(i++);
				if (Character.isDigit(c)) {
					if (ret > Integer.MAX_VALUE / 10 ||
							(ret == Integer.MAX_VALUE / 10 
							&& (c - '0') > Integer.MAX_VALUE % 10)) {
						return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
					}
					ret = ret * 10 + c - '0';
				} else {
					return negative ? -ret : ret;
				}
			}
			
		} else {
			return 0;
        }
        
		return negative ? -ret : ret;
    }
}
// @lc code=end

