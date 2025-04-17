package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountEqualAndDivisiblePairsInAnArray {

    public static void main(String[] args) {
        check(new int[]{3, 1, 2, 2, 2, 1, 3}, 2, 4);
        check(new int[]{1, 2, 3, 4}, 1, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int countPairs(int[] nums, int k) {
        int result = 0;
        int limit = nums.length - 1;
        for (int i = 0; i < limit; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    private static void check(int[] nums, int k, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int countPairs = countPairs(nums, k); // Calls your implementation
        System.out.println("countPairs is: " + countPairs);
    }
}
