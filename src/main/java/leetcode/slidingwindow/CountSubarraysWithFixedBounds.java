package leetcode.slidingwindow;

import java.util.Arrays;

public class CountSubarraysWithFixedBounds {

    public static void main(String[] args) {
        check(new int[]{1, 3, 5, 2, 7, 5}, 1, 5, 2);
        check(new int[]{1, 1, 1, 1}, 1, 1, 10);
        check(new int[]{934372, 927845, 479424, 49441, 17167, 17167, 65553, 927845, 17167, 927845, 17167, 425106,
                17167, 927845, 17167, 927845, 251338, 17167}, 17167, 927845, 118);
    }

    /**
     * Leetcode problem:
     * https://leetcode.com/problems/count-subarrays-with-fixed-bounds. This
     * solution uses a sliding window which should contain ate least one minK
     * number, one maxK number and no numbers greater than maxK or less than minK.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @param k
     * @return
     */
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        int left = -1;
        int min = -1;
        int max = -1;
        long count = 0L;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                // num is out of range, no subarray may contain it
                // reset indexes
                left = min = max = i;
            } else {
                // the subarray has to contain both minK and maxK in order to be counted
                min = nums[i] == minK ? i : min;
                max = nums[i] == maxK ? i : max;
                count += Math.min(min, max) - left;
            }
        }
        return count;
    }

    public static long countSubarrays2(int[] nums, int minK, int maxK) {
        long result = 0;
        int start = 0;
        int outOfRangeIndex = -1;
        int minCount = 0;
        int maxCount = 0;
        // iterate all numbers
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                // if the current number is out of range, no subarray may contain it
                // reset start index and counts so that only subarrays starting from i+1
                // are taken into consideration from now on
                outOfRangeIndex = i;
                start = i + 1;
                minCount = 0;
                maxCount = 0;
            } else {
                if (nums[i] == minK) {
                    // increase count of minK numbers
                    minCount++;
                }
                if (nums[i] == maxK) {
                    // increase count of maxK numbers
                    maxCount++;
                }
            }
            // try to shrink the window until it does not contain any minK or maxK numbers
            while (minCount > 0 && maxCount > 0 && start <= i) {
                if (nums[start] == minK) {
                    minCount--;
                }
                if (nums[start] == maxK) {
                    maxCount--;
                }
                start++;
            }
            // all subarrays starting at (outOfRangeIndex + 1 to start - 1) and ending at i
            // contain at least one minK, one maxK and no out of range numbers
            result += start - (outOfRangeIndex + 1);
        }
        return result;
    }

    private static void check(int[] nums, int minK, int maxK, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("minK is: " + minK);
        System.out.println("maxK is: " + maxK);
        System.out.println("expected is: " + expected);
        long countSubarrays = countSubarrays(nums, minK, maxK); // Calls your implementation
        System.out.println("countSubarrays is: " + countSubarrays);
    }
}
