package leetcode.array.frequency;

import java.util.Arrays;

public class DivideArrayIntoEqualPairs {

    public static void main(String[] args) {
        check(new int[]{3, 2, 3, 2, 2, 2}, true);
        check(new int[]{1, 2, 3, 4}, false);
    }

    /**
     * Leetcode problem:
     * https://leetcode.com/problems/divide-array-into-equal-pairs. Time complexity is O(n) where n is the
     * length of the nums array.
     *
     * @param nums
     * @return
     */
    public static boolean divideArray(int[] nums) {
        int[] frequency = new int[501];
        for (int i = 0; i < nums.length; i++) {
            frequency[nums[i]]++;
        }
        for (int i = 0; i < frequency.length; i++) {
            if ((frequency[i] & 1) == 1) {
                return false;
            }
        }
        return true;
    }

    private static void check(int[] nums, boolean expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        boolean divideArray = divideArray(nums); // Calls your implementation
        System.out.println("divideArray is: " + divideArray);
    }
}
