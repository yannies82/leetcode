package leetcode.array.prefix;

import java.util.Arrays;

public class MaximumValueOfAnOrderedTriplet1 {

    public static void main(String[] args) {
        check(new int[]{12, 6, 1, 2, 7}, 77);
        check(new int[]{1, 10, 3, 4, 19}, 133);
        check(new int[]{1, 2, 3}, 0);
        check(new int[]{6, 11, 12, 12, 7, 9, 2, 11, 12, 4, 19, 14, 16, 8, 16}, 190);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i.
     * This solution calculates the max value for each position, updating the diff on each step.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static long maximumTripletValue(int[] nums) {
        long result = 0;
        int firstMax = 0;
        int diffMax = 0;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, (long) diffMax * nums[i]);
            diffMax = Math.max(diffMax, firstMax - nums[i]);
            firstMax = Math.max(firstMax, nums[i]);
        }
        return result;
    }

    /**
     * This solution calculates the max left and right values for each position in order to
     * calculate the final result. Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static long maximumTripletValue2(int[] nums) {
        // keeps the max left and right value for each position
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int maxLeft = 0;
        int maxRight = 0;
        int limit = nums.length - 1;
        // calculate the max left and right value for each position
        for (int i = 1; i < limit; i++) {
            left[i] = maxLeft = Math.max(maxLeft, nums[i - 1]);
            right[limit - i] = maxRight = Math.max(maxRight, nums[limit - i + 1]);
        }
        long result = Integer.MIN_VALUE;
        for (int i = 1; i < limit; i++) {
            result = Math.max(result, ((long) left[i] - nums[i]) * right[i]);
        }
        // return 0 if result < 0, otherwise return result
        return ((result >>> 63) - 1) & result;
    }

    private static void check(int[] nums, long expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        long maximumTripletValue = maximumTripletValue(nums); // Calls your implementation
        System.out.println("maximumTripletValue is: " + maximumTripletValue);
    }

}
