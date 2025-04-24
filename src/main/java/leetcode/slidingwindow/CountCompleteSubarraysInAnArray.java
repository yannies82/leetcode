package leetcode.slidingwindow;

import java.util.Arrays;

public class CountCompleteSubarraysInAnArray {

    public static void main(String[] args) {
        check(new int[]{1, 3, 1, 2, 2}, 4);
        check(new int[]{5, 5, 5, 5}, 10);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-complete-subarrays-in-an-array.
     * This solution uses a sliding window. Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int countCompleteSubarrays(int[] nums) {
        // count all distinct numbers in array
        boolean[] exists = new boolean[2001];
        int totalDistinctCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!exists[nums[i]]) {
                totalDistinctCount++;
                exists[nums[i]] = true;
            }
        }
        int[] frequency = new int[2001];
        int start = 0;
        int distinctCount = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            // use a sliding window and increase it until it contains all distinct numbers
            if (++frequency[nums[i]] == 1) {
                distinctCount++;
            }
            int currentStart = start;
            // once the window contains all distinct numbers, decrease its size until it does not
            while (distinctCount == totalDistinctCount) {
                if (--frequency[nums[start++]] == 0) {
                    distinctCount--;
                }
            }
            // add to the result all possible subarrays starting between currentStart and start
            // and ending between i and nums.length
            result += (start - currentStart) * (nums.length - i);
        }
        return result;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int countCompleteSubarrays = countCompleteSubarrays(nums); // Calls your implementation
        System.out.println("countCompleteSubarrays is: " + countCompleteSubarrays);
    }
}
