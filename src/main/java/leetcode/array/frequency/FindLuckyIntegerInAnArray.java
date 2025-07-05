package leetcode.array.frequency;

import java.util.Arrays;

public class FindLuckyIntegerInAnArray {

    public static void main(String[] args) {
        check(new int[]{2, 2, 3, 4}, 2);
        check(new int[]{1, 2, 2, 3, 3, 3}, 3);
        check(new int[]{2, 2, 2, 3, 3}, -1);
    }

    /**
     * Leetcode problem:
     * https://leetcode.com/problems/find-lucky-integer-in-an-array. Time complexity is O(n)
     * where n is the length of the arr array.
     *
     * @param arr
     * @return
     */
    public static int findLucky(int[] arr) {
        int[] frequency = new int[501];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            frequency[arr[i]]++;
        }
        for (int i = max; i > 0; i--) {
            if (frequency[i] == i) {
                return i;
            }
        }
        return -1;
    }

    private static void check(int[] arr, int expected) {
        System.out.println("arr is: " + Arrays.toString(arr));
        System.out.println("expected is: " + expected);
        int findLucky = findLucky(arr); // Calls your implementation
        System.out.println("findLucky is: " + findLucky);
    }
}
