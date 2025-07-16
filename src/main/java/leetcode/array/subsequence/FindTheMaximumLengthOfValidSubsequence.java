package leetcode.array.subsequence;

import java.util.Arrays;

public class FindTheMaximumLengthOfValidSubsequence {

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4}, 4);
        check(new int[]{1, 3}, 2);
        check(new int[]{8, 8, 1}, 2);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i.
     * This solution iterates all numbers in the array and keeps count of odd numbers, even numbers
     * and the number of switches between odd and even numbers. Time complexity is O(n) where n is the
     * length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int maximumLength(int[] nums) {
        int oddCount = 0;
        int evenCount = 0;
        int switchCount = 0;
        int prevMod = 1 - (nums[0] & 1);
        for (int i = 0; i < nums.length; i++) {
            int mod = nums[i] & 1;
            oddCount += mod;
            evenCount += 1 - mod;
            switchCount += mod ^ prevMod;
            prevMod = mod;
        }
        return Math.max(Math.max(oddCount, evenCount), switchCount);
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int maximumLength = maximumLength(nums); // Calls your implementation
        System.out.println("maximumLength is: " + maximumLength);
    }
}
