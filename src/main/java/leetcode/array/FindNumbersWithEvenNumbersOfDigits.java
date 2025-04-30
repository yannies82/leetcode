package leetcode.array;

import java.util.Arrays;

public class FindNumbersWithEvenNumbersOfDigits {

    public static void main(String[] args) {
        check(new int[]{12, 345, 2, 6, 7896}, 2);
        check(new int[]{555, 901, 482, 1771}, 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-numbers-with-even-number-of-digits.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int findNumbers(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int length = findLength(nums[i]);
            result += 1 - (length & 1);
        }
        return result;
    }

    private static int findLength(int num) {
        int result = 0;
        do {
            num /= 10;
            result++;
        } while (num > 0);
        return result;
    }

    public static int findNumbers2(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += ((int) Math.floor(Math.log10(nums[i]))) & 1;
        }
        return result;
    }

    private static void check(int[] nums, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        int findNumbers = findNumbers(nums); // Calls your implementation
        System.out.println("findNumbers is: " + findNumbers);
    }
}
