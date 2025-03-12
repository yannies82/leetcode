package leetcode.binarysearch;

import java.util.Arrays;

public class MaximumCountOfPositiveAndNegativeInteger {

    public static void main(String[] args) {
        check(new int[]{-2, -1, -1, 1, 2, 3}, 3);
        check(new int[]{-3, -2, -1, 0, 0, 1, 2}, 3);
        check(new int[]{5, 20, 66, 1314}, 4);
    }

    /**
     * Leetcode problem:
     * https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer.
     * This solution performs two binary searches, one to find the index of largest element less than 0
     * and one to find the index of the smallest element greater than 0. Time complexity is O(nlogn)
     * where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int maximumCount(int[] nums) {
        int firstIndex = searchMin(nums, 0, nums.length);
        int secondIndex = searchMax(nums, firstIndex + 1, nums.length);
        return Math.max(firstIndex + 1, nums.length - secondIndex);
    }

    private static int searchMin(int[] nums, int start, int end) {
        int result = -1;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (nums[mid] < 0) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return result;
    }

    private static int searchMax(int[] nums, int start, int end) {
        int result = nums.length;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (nums[mid] > 0) {
                result = mid;
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int maximumCount = maximumCount(nums); // Calls your implementation
        System.out.println("maximumCount is: " + maximumCount);
    }
}
