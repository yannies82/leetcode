package leetcode.array;

import java.util.Arrays;

public class MinimumEqualSumOfTwoArraysAfterReplacingZeroes {

    public static void main(String[] args) {
        check(new int[]{3, 2, 0, 1, 0}, new int[]{6, 5, 0}, 12);
        check(new int[]{2, 0, 2, 0}, new int[]{1, 4}, -1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros.
     * Time complexity is O(m+n) where m is the length of nums1 and n is the length of nums2.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static long minSum(int[] nums1, int[] nums2) {
        int nums1Zeroes = 0;
        long nums1Sum = 0;
        for (int num : nums1) {
            nums1Zeroes += (num - 1) >>> 31;
            nums1Sum += num;
        }
        int nums2Zeroes = 0;
        long nums2Sum = 0;
        for (int num : nums2) {
            nums2Zeroes += (num - 1) >>> 31;
            nums2Sum += num;
        }
        long nums1MinSum = nums1Sum + nums1Zeroes;
        long nums2MinSum = nums2Sum + nums2Zeroes;
        if ((nums1MinSum > nums2MinSum && nums2Zeroes == 0) || (nums2MinSum > nums1MinSum && nums1Zeroes == 0)) {
            return -1;
        }
        return Math.max(nums1MinSum, nums2MinSum);
    }

    private static void check(int[] nums1, int[] nums2, long expected) {
        System.out.println("nums1 is: " + Arrays.toString(nums1));
        System.out.println("nums2 is: " + Arrays.toString(nums2));
        System.out.println("expected is: " + expected);
        long minSum = minSum(nums1, nums2); // Calls your implementation
        System.out.println("minSum is: " + minSum);
    }
}
