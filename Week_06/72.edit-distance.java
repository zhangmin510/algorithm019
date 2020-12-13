/*
 * @lc app=leetcode id=72 lang=java
 *
 * [72] Edit Distance
 */

// @lc code=start
class Solution {
    public int minDistance(String word1, String word2) {
        int s = word1.length() + 1;
        int t = word2.length() + 1;

        int[][] d = new int[s][t];

        for (int i = 0; i < t; i++)
            d[0][i] = i;
        
        for (int i = 0; i < s; i++)
            d[i][0] = i;
        
        for (int i = 1; i < s; i++)
        	for (int j = 1; j < t; j++) {
        		if (word1.charAt(i - 1) == word2.charAt(j - 1)) d[i][j] = d[i - 1][j - 1];
        		else {
        			d[i][j] = Math.min(d[i - 1][j - 1], Math.min(d[i - 1][j], d[i][j - 1])) + 1;
        		}
            }
        
        return d[s - 1][t - 1]; 
    }
}
// @lc code=end

