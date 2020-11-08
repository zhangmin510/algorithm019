import java.util.Deque;

/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 */

// @lc code=start
class Solution {

    public int trap(int[] height) {
        int ans = 0, cur = 0;
        Deque<Integer> stack = new LinkedList<>();
        while (cur < height.length) {
            while(!stack.isEmpty() && height[cur] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                int distance = cur - stack.peek() - 1;
                int bounded_height = Math.min(height[cur], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(cur++);
        }
        return ans;
    }

    public int trap0(int[] height) {
       int len = height.length;
       int ans = 0;
       for (int i = 0; i < len - 1; i++) {
            int maxLeft = 0, maxRight = 0;
           for (int j = i; j >=0; j--) {
               maxLeft = Math.max(maxLeft, height[j]);
           }
           for (int j = i; j < len; j++) {
               maxRight = Math.max(maxRight, height[j]);
           }
           ans += Math.min(maxLeft, maxRight) - height[i];
       }
       return ans;
    }
}
// @lc code=end

