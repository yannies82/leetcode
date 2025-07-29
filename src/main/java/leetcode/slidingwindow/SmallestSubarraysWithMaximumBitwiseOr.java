package leetcode.slidingwindow;

import java.util.Arrays;

public class SmallestSubarraysWithMaximumBitwiseOr {

    public static void main(String[] args) {
        check(new int[]{1, 0}, new int[]{1, 1});
        check(new int[]{0}, new int[]{1});
        check(new int[]{1, 0, 2, 1, 3}, new int[]{3, 3, 2, 2, 1});
        check(new int[]{1, 2}, new int[]{2, 1});
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or.
     * Time complexity is O(n^2) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] result = new int[nums.length];
        for (int i = 0; i < n; i++)
        {
            int current = nums[i];
            result[i] = 1;
            for (int j = i - 1; j >= 0 && (nums[j] | current) != nums[j]; j--)
            {
                nums[j] |= current;
                result[j] = i - j + 1;
            }
        }
        return result;
    }

    /**
     * This solution uses a sliding window. Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int[] smallestSubarrays2(int[] nums) {
        int[] maxOr = new int[nums.length];
        int or = 0;
        // calculate the maxOr for the subarray starting at i
        for (int i = nums.length - 1; i >= 0; i--) {
            or |= nums[i];
            maxOr[i] = or;
        }
        // maintain the frequency of bits in the sliding window
        int[] frequency = new int[32];
        int end = 0;
        int[] currentOr = {0};
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // increase sliding window size until maxOr starting at i is reached
            while (end <= i || currentOr[0] < maxOr[i]) {
                currentOr[0] |= nums[end];
                increaseFrequency(frequency, nums[end]);
                end++;
            }
            result[i] = end - i;
            // decrease window size by removing nums[i], adjust currentOr if needed
            decreaseFrequency(frequency, nums[i], currentOr);
        }
        return result;
    }

    private static void increaseFrequency(int[] frequency, int num) {
        for (int i = 0; i < 32; i++) {
            frequency[i] += (num >>> i) & 1;
        }
    }

    private static void decreaseFrequency(int[] frequency, int num, int[] currentOr) {
        for (int i = 0; i < 32; i++) {
            int current = (num >>> i) & 1;
            if (current > 0) {
                frequency[i]--;
                if (frequency[i] == 0) {
                    currentOr[0] ^= 1 << i;
                }
            }
        }
    }

    private static void check(int[] nums, int[] expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + Arrays.toString(expected));
        int[] smallestSubarrays = smallestSubarrays(nums); // Calls your implementation
        System.out.println("smallestSubarrays is: " + Arrays.toString(smallestSubarrays));
    }

}
