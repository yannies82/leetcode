package leetcode.twopointers;

import java.util.Arrays;

public class CountFairPairs {

    public static void main(String[] args) {
        check(new int[]{0, 1, 7, 4, 4, 5}, 3, 6, 6);
        check(new int[]{1, 7, 9, 2, 5}, 11, 11, 1);
        check(new int[]{0, 0, 0, 0, 0, 0}, 0, 0, 15);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-the-number-of-fair-pairs.
     * This solution uses the two pointers method after sorting the input array. It
     * counts the sums which are greater than lower - 1 and subtracts them
     * from the sums which are greater than upper. Time complexity is O(nlogn) where n is
     * the length of the nums array.
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public static long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return count(nums, upper) - count(nums, lower - 1);
    }

    private static long count(int[] nums, int target) {
        long result = 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                result += right - left++;
            }
        }
        return result;
    }

    /**
     * This solution uses binary search. Time complexity is O(nlogn) where n is
     * the length of the nums array.
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public static long countFairPairs2(int[] nums, int lower, int upper) {
        long result = 0;
        Arrays.sort(nums);
        int limit = nums.length - 1;
        for (int i = 0; i < limit; i++) {
            int lowerLimit = lower - nums[i];
            int upperLimit = upper - nums[i] + 1;
            int lowerIndex = binarySearch(nums, i + 1, nums.length, lowerLimit);
            if (lowerIndex < 0) {
                lowerIndex = -lowerIndex - 1;
            }
            int upperIndex = binarySearch(nums, i + 1, nums.length, upperLimit);
            if (upperIndex < 0) {
                upperIndex = -upperIndex - 1;
            }
            result += upperIndex - lowerIndex;
        }
        return result;
    }

    private static int binarySearch(int[] nums, int start, int end, int key) {
        int result = -1;
        int mid = -1;
        while (start < end) {
            mid = (start + end) >>> 1;
            if (nums[mid] > key) {
                end = mid;
            } else if (nums[mid] < key) {
                start = mid + 1;
            } else {
                result = mid;
                end = mid;
            }
        }
        if (result > 0) {
            return result;
        }
        return -start - 1;
    }

    private static void check(int[] nums, int lower, int upper, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("lower is: " + lower);
        System.out.println("upper is: " + upper);
        System.out.println("expected is: " + expected);
        long countFairPairs = countFairPairs(nums, lower, upper); // Calls your implementation
        System.out.println("countFairPairs is: " + countFairPairs);
    }
}
