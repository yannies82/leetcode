package leetcode.dynamicprogramming;

import java.util.Arrays;

public class FindTheMaximumLengthOfValidSubsequence2 {

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4, 5}, 2, 5);
        check(new int[]{1, 4, 2, 3, 1, 4}, 3, 4);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii.
     * Time complexity is O(n*k) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int maximumLength(int[] nums, int k) {
        if (k == 1) {
            return nums.length;
        }
        int result = 2;
        int[] mods = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            mods[i] = nums[i] % k;
        }
        for (int i = 0; i < k; i++) {
            int[] dpArray = new int[k];
            int offset = i + k;
            for (int j = 0; j < nums.length; j++) {
                dpArray[mods[j]] = dpArray[(offset - mods[j]) % k] + 1;
                result = Math.max(result, dpArray[mods[j]]);
            }
        }
        return result;
    }

    private static void check(int[] nums, int k, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int maximumLength = maximumLength(nums, k); // Calls your implementation
        System.out.println("maximumLength is: " + maximumLength);
    }
}
