package leetcode.array;

import java.util.Arrays;

public class BuildArrayFromPermutation {

    public static void main(String[] args) {
        check(new int[]{0, 2, 1, 5, 3, 4}, new int[]{0, 1, 2, 4, 5, 3});
        check(new int[]{5, 0, 1, 2, 3, 4}, new int[]{4, 5, 0, 1, 2, 3});
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/build-array-from-permutation.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int[] buildArray(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[nums[i]];
        }
        return result;
    }

    private static void check(int[] nums, int[] expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + Arrays.toString(expected));
        int[] buildArray = buildArray(nums); // Calls your implementation
        System.out.println("buildArray is: " + Arrays.toString(buildArray));
    }

}
