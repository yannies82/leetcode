package leetcode.array;

import java.util.Arrays;

public class CountSubarraysOfLengthThreeWithACondition {

    public static void main(String[] args) {
        check(new int[]{1, 2, 1, 4, 1}, 1);
        check(new int[]{1, 1, 1}, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-subarrays-of-length-three-with-a-condition.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int countSubarrays(int[] nums) {
        int result = 0;
        for (int i = 2; i < nums.length; i++) {
            if ((nums[i] + nums[i - 2]) << 1 == nums[i - 1]) {
                result++;
            }
        }
        return result;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int countSubarrays = countSubarrays(nums); // Calls your implementation
        System.out.println("countSubarrays is: " + countSubarrays);
    }
}
