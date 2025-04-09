package leetcode.array.frequency;

import java.util.Arrays;

public class MinimumOperationsToMakeArrayValuesEqualToK {

    public static void main(String[] args) {
        check(new int[]{5, 2, 5, 4, 5}, 2, 2);
        check(new int[]{2, 1, 2}, 2, -1);
        check(new int[]{9, 7, 5, 3}, 1, 4);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int minOperations(int[] nums, int k) {
        int[] frequency = new int[101];
        for (int i = 0; i < nums.length; i++) {
            frequency[nums[i]]++;
        }
        // if a number less than k exists then it is impossible to make all numbers equal to k
        for (int i = 0; i < k; i++) {
            if (frequency[i] > 0) {
                return -1;
            }
        }
        // count how many distinct numbers greater than k exist
        int result = 0;
        for (int i = k + 1; i < frequency.length; i++) {
            result += ((-frequency[i]) >>> 31) & 1;
        }
        return result;
    }

    private static void check(int[] nums, int k, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int minOperations = minOperations(nums, k); // Calls your implementation
        System.out.println("minOperations is: " + minOperations);
    }
}
