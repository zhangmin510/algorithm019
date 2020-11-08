/*
 * @lc app=leetcode id=283 lang=java
 *
 * [283] Move Zeroes
 */

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
       int i = 0, j = 0;
       while (j < nums.length) {
           if (nums[j] == 0) {
               j++;
           } else {
               nums[i] = nums[j];
               if (i != j) {
                nums[j] = 0;
               }
               i++;
               j++;
           }
       }
    }
}
// @lc code=end

