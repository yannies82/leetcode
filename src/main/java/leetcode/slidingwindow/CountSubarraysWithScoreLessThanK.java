package leetcode.slidingwindow;

import java.util.Arrays;

public class CountSubarraysWithScoreLessThanK {

    public static void main(String[] args) {
        check(new int[]{2, 1, 4, 3, 5}, 10, 6);
        check(new int[]{1, 1, 1}, 5, 5);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-subarrays-with-score-less-than-k.
     * Instead of counting all subarrays with score strictly less than k, it is easier to count
     * all subarrays with score k or more using the sliding window technique. Then we subtract
     * this count from the total number of possible subarrays to find the result. Time complexity
     * is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @param k
     * @return
     */
    public static long countSubarrays(int[] nums, long k) {
        long n = nums.length;
        return ((n * (n + 1)) >>> 1) - numberOfSubarraysScoreKOrMore(nums, k);
    }

    public static long numberOfSubarraysScoreKOrMore(int[] nums, long k) {
        int start = 0;
        long count = 0;
        long sum = 0;
        // iterate all numbers, each time increasing the window size
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            long score = sum * (end - start + 1);
            // decrease the window size until the score is less than k
            while (score >= k && start <= end) {
                // increase count with all subarrays starting from start and having score >= k
                count += nums.length - end;
                sum -= nums[start];
                score = sum * (end - start);
                start++;
            }
        }
        return count;
    }

    private static void check(int[] nums, long k, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        long countSubarrays = countSubarrays(nums, k); // Calls your implementation
        System.out.println("countSubarrays is: " + countSubarrays);
    }
}
