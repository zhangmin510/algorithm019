/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 */

// @lc code=start
class Solution {
    public int numIslands(char[][] grid) {
       if (grid == null || grid.length == 0) {
           return 0;
       } 

       int m = grid.length;
       int n = grid[0].length;

       int result = 0;
       for (int i = 0; i < m; i ++) {
           for (int j = 0; j < n; j++) {
               if (grid[i][j] == '1') {
                   result++;
                   dfs(grid, i, j);
               }
           }
       }
       return result;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
// @lc code=end

