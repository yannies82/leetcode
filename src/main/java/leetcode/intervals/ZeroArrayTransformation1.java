package leetcode.intervals;

import java.util.Arrays;

public class ZeroArrayTransformation1 {

    public static void main(String[] args) {
        check(new int[]{1, 0, 1}, new int[][]{{0, 2}}, true);
        check(new int[]{4, 3, 2, 1}, new int[][]{{1, 3}, {0, 2}}, false);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/zero-array-transformation-i.
     * Time complexity is O(m+n) where m is the length of the nums array and n is the length of
     * the queries array.
     *
     * @param nums
     * @param queries
     * @return
     */
    public static boolean isZeroArray(int[] nums, int[][] queries) {
        int[] range = new int[nums.length + 1];
        for (int[] query : queries) {
            range[query[0]]++;
            range[query[1] + 1]--;
        }
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            current += range[i];
            if (nums[i] > current) {
                return false;
            }
        }
        return true;
    }

    private static void check(int[] nums, int[][] queries, boolean expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("queries is: ");
        for (int[] query : queries) {
            System.out.println(Arrays.toString(query));
        }
        System.out.println("expected is: " + expected);
        boolean isZeroArray = isZeroArray(nums, queries); // Calls your implementation
        System.out.println("isZeroArray is: " + isZeroArray);
    }
}
