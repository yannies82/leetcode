package leetcode.binarysearch;

import java.util.Arrays;

public class HouseRobber4 {

    public static void main(String[] args) {
        check(new int[]{2, 3, 5, 9}, 2, 5);
        check(new int[]{2, 7, 9, 3, 1}, 2, 2);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/house-robber-iv.
     * This solution uses binary search to test the possible capabilities
     * of the robber and find the min one for which it is possible to rob all houses.
     * Time complexity is O(n*logm) where n is the length of the nums array and m
     * is the max value in nums.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int minCapability(int[] nums, int k) {
        // find the range of possible capabilities
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        int left = min;
        int right = max + 1;
        int result = max;
        // perform binary search on the possible range of the robber capabilities
        while (left < right) {
            int mid = (left + right) >>> 1;
            // check if it is possible to rob k houses if only stealing from houses with value <= mid
            int count = 0;
            int i = 0;
            while (i < nums.length && count < k) {
                if (nums[i] <= mid) {
                    count++;
                    i += 2;
                } else {
                    i++;
                }
            }
            if (count >= k) {
                // it is possible to rob k houses with value >= mid, update result and search in the lower half
                result = mid;
                right = mid;
            } else {
                // it is not possible to rob k houses with value >= mid, search in the upper half
                left = mid + 1;
            }
        }
        return result;
    }

    private static void check(int[] nums, int k, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int minCapability = minCapability(nums, k); // Calls your implementation
        System.out.println("minCapability is: " + minCapability);
    }

}
