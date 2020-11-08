import java.util.Arrays;
import java.util.Map;

/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 */

// @lc code=start
class Solution {

    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int[] copy = Arrays.copyOf(nums, len);
        Arrays.sort(copy);

        int[] result = new int[2];
        int i = 0, j = len - 1;
        while (i < j) {
            int sum = copy[i] + copy[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                result[0] = i;
                result[1] = j;
                break;
            }
        }

        for (int k = 0; k < len; k++) {
            if (copy[result[0]] == nums[k]) {
                result[0] = k;
                break;
            }
        }

        for (int k = len - 1; k >= 0; k--) {
            if (copy[result[1]] == nums[k]) {
                result[1] = k;
                break;
            }
        }
        return result;
    }


    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            if (map.containsKey(other)) {
               return new int[]{map.get(other), i};
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }

    public int[] twoSum0(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("no such elements");
    }
}
// @lc code=end

