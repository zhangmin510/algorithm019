/*
 * @lc app=leetcode id=74 lang=java
 *
 * [74] Search a 2D Matrix
 */

// @lc code=start
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length;
    	int n = matrix[0].length;
    	int i = 0;
    	int j = 0;
    	while (i < m && j < n) {
    		if (i < m - 1 && matrix[i + 1][j] <= target) {
                i++;
            } else {
    		    if (matrix[i][j] < target) {
                    j++;
                } else if (matrix[i][j] > target) {
                    return false;
                } else {
                    return true;
                }
    	    }
    	}
    	return false;
    }
}
// @lc code=end

