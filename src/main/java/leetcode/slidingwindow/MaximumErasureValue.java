package leetcode.slidingwindow;

import java.util.Arrays;

public class MaximumErasureValue {

    public static void main(String[] args) {
        check(new int[]{4, 2, 4, 5, 6}, 17);
        check(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5}, 8);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-erasure-value.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int maximumUniqueSubarray(int[] nums) {
        int[] frequency = new int[10001];
        int maxSum = 0;
        int currentSum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            frequency[nums[right]]++;
            currentSum += nums[right];
            if (frequency[nums[right]] > 1) {
                while (frequency[nums[right]] > 1) {
                    frequency[nums[left]]--;
                    currentSum -= nums[left];
                    left++;
                }
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int maximumUniqueSubarray = maximumUniqueSubarray(nums); // Calls your implementation
        System.out.println("maximumUniqueSubarray is: " + maximumUniqueSubarray);
    }
}
