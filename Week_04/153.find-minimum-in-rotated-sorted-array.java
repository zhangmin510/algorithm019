/*
 * @lc app=leetcode id=153 lang=java
 *
 * [153] Find Minimum in Rotated Sorted Array
 */

// @lc code=start
class Solution {
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        int mid = 0;
        while (lo < hi) {
            mid = lo + ((hi - lo) >> 1);
            if (nums[mid] < nums[hi]) hi = mid;
            else lo = mid + 1;
        }
        return nums[lo]; 
    }
}
// @lc code=end

