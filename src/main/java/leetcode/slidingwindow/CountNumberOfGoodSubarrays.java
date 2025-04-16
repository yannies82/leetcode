package leetcode.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountNumberOfGoodSubarrays {

    public static void main(String[] args) {
        check(new int[]{1, 1, 1, 1, 1}, 10, 1);
        check(new int[]{3, 1, 4, 3, 2, 2, 4}, 2, 4);
        check(new int[]{2, 1, 3, 1, 2, 2, 3, 3, 2, 2, 1, 1, 1, 3, 1}, 11, 21);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-the-number-of-good-subarrays.
     * This solution uses a sliding window. Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @param k
     * @return
     */
    public static long countGood(int[] nums, int k) {
        int n = nums.length;
        long allCombinations = ((long) n * (n - 1)) >>> 1;
        if (allCombinations < k) {
            return 0;
        }
        long result = 0;
        int current = 0;
        int start = 0;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int newFrequency = 1;
            Integer freq = frequencyMap.get(nums[i]);
            if (freq != null) {
                newFrequency += freq;
                current += newFrequency - 1;
            }
            frequencyMap.put(nums[i], newFrequency);
            if (current >= k) {
                int currentStart = start;
                while (current >= k) {
                    newFrequency = frequencyMap.get(nums[start]) - 1;
                    frequencyMap.put(nums[start], newFrequency);
                    current -= newFrequency;
                    start++;
                }
                result += (long) (start - currentStart) * (n - i);
            }
        }
        return result;
    }

    private static void check(int[] nums, int k, long expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        long countGood = countGood(nums, k); // Calls your implementation
        System.out.println("countGood is: " + countGood);
    }
}
