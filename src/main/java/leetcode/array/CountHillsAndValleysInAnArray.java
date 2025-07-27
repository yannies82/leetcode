package leetcode.array;

import java.util.Arrays;

public class CountHillsAndValleysInAnArray {

    public static void main(String[] args) {
        check(new int[]{2, 4, 1, 1, 6, 5}, 3);
        check(new int[]{6, 6, 5, 5, 4, 1}, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-hills-and-valleys-in-an-array.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int countHillValley(int[] nums) {
        int prev = nums[0];
        int prevDiff = 0;
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            int currentDiff = nums[i] - prev;
            if (currentDiff == 0) {
                continue;
            }
            if ((currentDiff > 0 && prevDiff < 0) || (currentDiff < 0 && prevDiff > 0)) {
                result++;
            }
            prevDiff = currentDiff;
            prev = nums[i];
        }
        return result;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int countHillValley = countHillValley(nums); // Calls your implementation
        System.out.println("countHillValley is: " + countHillValley);
    }
}
