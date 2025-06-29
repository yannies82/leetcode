package leetcode.twopointers;

import java.util.*;

public class NumberOfSubsequencesThatSatisfyTheGivenSumCondition {

    public static void main(String[] args) {
        check(new int[]{3, 5, 6, 7}, 9, 4);
        check(new int[]{3, 3, 6, 8}, 10, 6);
        check(new int[]{2, 3, 3, 4, 6, 7}, 12, 61);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition.
     * This solution uses the two pointers approach. Time complexity is O(nlogn) where n is the length of the nums
     * array.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int mod = 1000000007;
        int left = 0;
        int right = nums.length - 1;
        // precalculate all possible powers of two that may be needed to count number of subsequences
        int[] power = new int[nums.length];
        power[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            power[i] = (power[i - 1] << 1) % mod;
        }
        int count = 0;
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                count = (count + power[right - left]) % mod;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    /**
     * Alternative solution which does not use precalculation for the power of two.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int numSubseq2(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left <= right) {
            int current = nums[left] + nums[right];
            if (current <= target) {
                count = (count + fastPow(2, right - left)) % 1000000007;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    public static int fastPow(int num, int pow) {
        long base = num;
        long result = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                result = (result * base) % 1000000007;
            }
            base = (base * base) % 1000000007;
            pow >>>= 1;
        }
        return (int) result;
    }

    private static void check(int[] nums, int target, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("target is: " + target);
        System.out.println("expected is: " + expected);
        int numSubseq = numSubseq(nums, target); // Calls your implementation
        System.out.println("numSubseq is: " + numSubseq);
    }
}
