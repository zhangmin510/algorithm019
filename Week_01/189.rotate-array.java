import java.util.Arrays;

/*
 * @lc app=leetcode id=189 lang=java
 *
 * [189] Rotate Array
 */

// @lc code=start
class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    public void rotate0(int[] nums, int k) {
        int tmp, pre;
        int len = nums.length;
        for (int i = 0; i < k; i++) {
            pre = nums[len- 1];
            for (int j = 0; j < len; j++) {
                tmp = nums[j];
                nums[j] = pre;
                pre = tmp;
            }
        }
    }

    public void rotate2(int[] nums, int k) {
       int len = nums.length;
       int[] copy = new int[len];
       
       for (int i = 0; i < len; i++) {
           copy[(i + k) % len] = nums[i];
       }

       for (int i = 0; i < len; i++) {
           nums[i] = copy[i];
       }
    }

}
// @lc code=end

