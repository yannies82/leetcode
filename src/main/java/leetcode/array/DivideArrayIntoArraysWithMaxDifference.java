package leetcode.array;

import java.util.Arrays;

public class DivideArrayIntoArraysWithMaxDifference {

    public static void main(String[] args) {
        check(new int[]{1, 3, 4, 8, 7, 9, 3, 5, 1}, 2, new int[][]{{1, 1, 3}, {3, 4, 5}, {7, 8, 9}});
        check(new int[]{2, 4, 2, 2, 5, 2}, 2, new int[][]{});
        check(new int[]{4, 2, 9, 8, 2, 12, 7, 12, 10, 5, 8, 5, 5, 7, 9, 2, 5, 11}, 14,
                new int[][]{{2, 2, 2}, {4, 5, 5}, {5, 5, 7}, {7, 8, 8}, {9, 9, 10}, {11, 12, 12}});
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/divide-array-into-arrays-with-max-difference.
     * This solution sorts the input array and greedily selects the array elements to create the subarrays.
     * Time complexity is O(nlogn) where n is the length of the nums array.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int[][] result = new int[nums.length / 3][3];
        for (int i = 0, j = 0; i < result.length; i++, j += 3) {
            if (nums[j + 2] - nums[j] > k) {
                // condition cannot be satisfied
                return new int[][]{};
            }
            result[i] = new int[]{nums[j], nums[j + 1], nums[j + 2]};
        }
        return result;
    }

    private static void check(int[] nums, int k, int[][] expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("k is: " + k);
        System.out.println("expected is: ");
        for (int[] row : expected) {
            System.out.println(Arrays.toString(row));
        }
        int[][] divideArray = divideArray(nums, k); // Calls your implementation
        System.out.println("divideArray is: ");
        for (int[] row : divideArray) {
            System.out.println(Arrays.toString(row));
        }
    }
}
