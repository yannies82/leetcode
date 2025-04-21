package leetcode.array;

import java.util.Arrays;

public class CountTheHiddenSequences {

    public static void main(String[] args) {
        check(new int[]{1, -3, 4}, 1, 6, 2);
        check(new int[]{3, -4, 5, 1, -2}, -4, 5, 4);
        check(new int[]{4, -7, 2}, 3, 6, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-the-hidden-sequences.
     * Time complexity is O(n) where n is the length of the differences array.
     *
     * @param differences
     * @param lower
     * @param upper
     * @return
     */
    public static int numberOfArrays(int[] differences, int lower, int upper) {
        int min = 0;
        int max = 0;
        int sum = 0;
        int range = upper - lower;
        for (int i = 0; i < differences.length; i++) {
            sum += differences[i];
            if (sum < min) {
                min = sum;
                if (max - min > range) {
                    return 0;
                }
            } else if (sum > max) {
                max = sum;
                if (max - min > range) {
                    return 0;
                }
            }
        }
        int result = upper - lower - max + min + 1;
        return result;
    }

    private static void check(int[] differences, int lower, int upper, int expected) {
        System.out.println("differences is: " + Arrays.toString(differences));
        System.out.println("lower is: " + lower);
        System.out.println("upper is: " + upper);
        System.out.println("expected is: " + expected);
        int numberOfArrays = numberOfArrays(differences, lower, upper); // Calls your implementation
        System.out.println("numberOfArrays is: " + numberOfArrays);
    }
}
