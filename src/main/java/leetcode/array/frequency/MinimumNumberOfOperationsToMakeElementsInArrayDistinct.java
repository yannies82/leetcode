package leetcode.array.frequency;

import java.util.Arrays;

public class MinimumNumberOfOperationsToMakeElementsInArrayDistinct {

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4, 2, 3, 3, 5, 7}, 2);
        check(new int[]{4, 5, 6, 4, 4}, 2);
        check(new int[]{6, 7, 8, 9}, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int minimumOperations(int[] nums) {
        int[] frequency = new int[101];
        int duplicateCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (++frequency[nums[i]] == 2) {
                duplicateCount++;
            }
        }
        int operations = 0;
        int index = 0;
        while (duplicateCount > 0) {
            int limit = Math.min(index + 3, nums.length);
            while (index < limit) {
                if (--frequency[nums[index]] == 1) {
                    duplicateCount--;
                }
                index++;
            }
            operations++;
        }
        return operations;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int minimumOperations = minimumOperations(nums); // Calls your implementation
        System.out.println("minimumOperations is: " + minimumOperations);
    }
}
