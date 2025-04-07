package leetcode.dynamicprogramming;

import java.util.Arrays;

public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        check(new int[]{1, 5, 11, 5}, true);
        check(new int[]{1, 2, 3, 5}, false);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/partition-equal-subset-sum.
     * This solution uses top down dynamic programming. Time complexity is O(m * n) where m is the length
     * of the nums array and n is the sum of the elements of the nums array.
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }
        if ((totalSum & 1) == 1) {
            return false;
        }
        int halfSum = totalSum >>> 1;
        Boolean[] dpArray = new Boolean[halfSum + 1];
        return canPartition(nums, dpArray, halfSum, nums.length - 1);
    }

    private static boolean canPartition(int[] nums, Boolean[] dpArray, int targetSum, int i) {
        if (targetSum == 0) {
            return true;
        }
        if (targetSum < 0) {
            return false;
        }
        if (i == 0) {
            return targetSum == nums[0];
        }
        if (dpArray[targetSum] != null) {
            return dpArray[targetSum];
        }
        return dpArray[targetSum] = canPartition(nums, dpArray, targetSum - nums[i], i - 1)
                || canPartition(nums, dpArray, targetSum, i - 1);
    }

    /**
     * This solution uses bottom up dynamic programming. Time complexity is O(m * n) where m is the length
     * of the nums array and n is the sum of the elements of the nums array.
     *
     * @param nums
     * @return
     */
    public static boolean canPartition2(int[] nums) {
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }
        if ((totalSum & 1) == 1) {
            return false;
        }
        int halfSum = totalSum >>> 1;
        boolean[] dpArray = new boolean[halfSum + 1];
        dpArray[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = halfSum; j >= nums[i]; j--) {
                dpArray[j] |= dpArray[j - nums[i]];
            }
        }
        return dpArray[halfSum];
    }

    private static void check(int[] nums, boolean expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        boolean canPartition = canPartition(nums); // Calls your implementation
        System.out.println("canPartition is: " + canPartition);
    }
}
