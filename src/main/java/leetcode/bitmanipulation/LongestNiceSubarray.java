package leetcode.bitmanipulation;

import java.util.Arrays;

public class LongestNiceSubarray {

    public static void main(String[] args) {
        check(new int[]{1, 3, 8, 48, 10}, 3);
        check(new int[]{3, 1, 5, 11, 13}, 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/longest-nice-subarray. This solution
     * uses a sliding window and bit manipulation in order to keep the current nice subarray
     * and calculate the one with the max length. Time complexity is O(n) where n is the length
     * of the nums array.
     *
     * @param nums
     * @return
     */
    public static int longestNiceSubarray(int[] nums) {
        int current = nums[0];
        int maxLength = 1;
        int start = 0;
        for (int i = 1; i < nums.length; i++) {
            if ((current & nums[i]) == 0) {
                // nums[i] does not have any common digit with the previous numbers, the subarray is nice
                maxLength = Math.max(i - start + 1, maxLength);
            } else {
                // nums[i] has a common digit with one of the previous numbers
                // decrease the window until it does not have a common digit
                do {
                    current ^= nums[start++];
                } while ((current & nums[i]) > 0);
            }
            // update the current xor
            current ^= nums[i];
        }
        return maxLength;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int longestNiceSubarray = longestNiceSubarray(nums); // Calls your implementation
        System.out.println("longestNiceSubarray is: " + longestNiceSubarray);
    }
}
