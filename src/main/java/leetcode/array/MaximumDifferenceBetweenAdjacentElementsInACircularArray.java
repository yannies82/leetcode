package leetcode.array;

import java.util.Arrays;

public class MaximumDifferenceBetweenAdjacentElementsInACircularArray {

    public static void main(String[] args) {
        check(new int[]{1, 2, 4}, 3);
        check(new int[]{-5, -10, -5}, 5);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int maxAdjacentDistance(int[] nums) {
        int maxDiff = 0;
        for (int i = 1; i < nums.length; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums[i] - nums[i - 1]));
        }
        return Math.max(maxDiff, Math.abs(nums[0] - nums[nums.length - 1]));
    }

    public static int maxAdjacentDistance2(int[] nums) {
        int maxDiff = 0;
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums[i] - prev));
            prev = nums[i];
        }
        return Math.max(maxDiff, Math.abs(nums[0] - prev));
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int maxAdjacentDistance = maxAdjacentDistance(nums); // Calls your implementation
        System.out.println("maxAdjacentDistance is: " + maxAdjacentDistance);
    }
}
