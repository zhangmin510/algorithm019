/*
 * @lc app=leetcode id=70 lang=java
 *
 * [70] Climbing Stairs
 */

// @lc code=start
class Solution {

    public int climbStairs(int n) {
        if (n <= 2) return n;
		int[] step = new int[n];
		step[0] = 1;
		step[1] = 2;
		for (int i = 2; i < n; i++) {
			step[i] = step[i - 1] + step[i - 2];
		}
		return step[n - 1];
    }

    public int climbStairs0(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
// @lc code=end

