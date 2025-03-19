package leetcode.bitmanipulation;

import java.util.Arrays;

public class MinimumOperationsToMakeBinaryArrayElementsEqualTo1 {

    public static void main(String[] args) {
        check(new int[]{0, 1, 1, 1, 0, 0}, 3);
        check(new int[]{0, 1, 1, 1}, -1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i.
     * This solution iterates all nums except for the last 2 and flips them greedily, so that all of them become 1.
     * Finally it checks the last 2 numbers. This solution is branchless. Time complexity is O(n) where n is the length
     * of the nums array.
     *
     * @param nums
     * @return
     */
    public static int minOperations(int[] nums) {
        int count = 0;
        int limit = nums.length - 2;
        for (int i = 0; i < limit; i++) {
            // if nums[i] == 0 add to count because an operation will be needed
            int val = 1 - nums[i];
            count += val;
            // the three numbers will be flipped if nums[i] == 0, otherwise they will remain the same
            nums[i] = val ^ nums[i];
            nums[i + 1] = val ^ nums[i + 1];
            nums[i + 2] = val ^ nums[i + 2];
        }
        int lastPositions = nums[limit] & nums[limit + 1];
        // return count if lastPositions == 1, else return -1
        return lastPositions * count + lastPositions - 1;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int minOperations = minOperations(nums); // Calls your implementation
        System.out.println("minOperations is: " + minOperations);
    }
}
