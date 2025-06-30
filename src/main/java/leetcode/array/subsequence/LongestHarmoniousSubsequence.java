package leetcode.array.subsequence;

import java.util.Arrays;

public class LongestHarmoniousSubsequence {

    public static void main(String[] args) {
        check(new int[]{1, 3, 2, 2, 5, 2, 3, 7}, 5);
        check(new int[]{1, 2, 3, 4}, 2);
        check(new int[]{1, 1, 1, 1}, 0);
        check(new int[]{1, 4, 1, 3, 1, -14, 1, -13}, 2);
        check(new int[]{1, 2, 2, 1}, 4);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/longest-harmonious-subsequence.
     * Time complexity is O(nlogn) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int findLHS(int[] nums) {
        Arrays.sort(nums);
        int maxLength = 0;
        int left = 0;
        int mid = 0;
        for (int i = 1; i < nums.length; i++) {
            int leftDiff = nums[i] - nums[left];
            int midDiff = nums[i] - nums[mid];
            if (leftDiff > 1) {
                if (left < mid) {
                    maxLength = Math.max(maxLength, i - left);
                }
                if (midDiff > 1) {
                    left = i;
                } else {
                    left = mid;
                }
                mid = i;
            } else if (leftDiff == 1 && midDiff == 1) {
                mid = i;
            }
        }
        if (nums[nums.length - 1] - nums[left] == 1) {
            maxLength = Math.max(maxLength, nums.length - left);
        }
        return maxLength;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int findLHS = findLHS(nums); // Calls your implementation
        System.out.println("findLHS is: " + findLHS);
    }
}
