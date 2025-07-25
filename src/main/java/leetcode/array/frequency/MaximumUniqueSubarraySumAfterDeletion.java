package leetcode.array.frequency;

import java.util.Arrays;

public class MaximumUniqueSubarraySumAfterDeletion {

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4, 5}, 15);
        check(new int[]{1, 1, 0, 1, 1}, 1);
        check(new int[]{1, 2, -1, -2, 1, 0, -1}, 3);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int maxSum(int[] nums) {
        int[] exists = new int[101];
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && exists[nums[i]] == 0) {
                sum += nums[i];
                exists[nums[i]] = 1;
            }
            max = Math.max(max, nums[i]);
        }
        if (max < 0) {
            return max;
        }
        return sum;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int maxSum = maxSum(nums); // Calls your implementation
        System.out.println("maxSum is: " + maxSum);
    }
}
