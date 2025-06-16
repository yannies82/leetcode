package leetcode.array;

import java.util.Arrays;

public class MaximumDifferenceBetweenIncreasingElements {

    public static void main(String[] args) {
        check(new int[]{7, 1, 5, 4}, 4);
        check(new int[]{9, 4, 3, 2}, -1);
        check(new int[]{1, 5, 2, 10}, 9);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-difference-between-increasing-elements.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int maximumDifference(int[] nums) {
        int min = nums[0];
        int maxDiff = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > min) {
                maxDiff = Math.max(maxDiff, nums[i] - min);
            }
            min = Math.min(min, nums[i]);
        }
        return maxDiff;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int minMaxDifference = maximumDifference(nums); // Calls your implementation
        System.out.println("minMaxDifference is: " + minMaxDifference);
    }
}
