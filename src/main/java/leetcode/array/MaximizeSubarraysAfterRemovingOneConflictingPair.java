package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximizeSubarraysAfterRemovingOneConflictingPair {

    public static void main(String[] args) {
        check(4, new int[][]{{2, 3}, {1, 4}}, 9);
        check(5, new int[][]{{1, 2}, {2, 5}, {3, 5}}, 12);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximize-subarrays-after-removing-one-conflicting-pair.
     * Time complexity is O(m+n) where m is the length of the conflicting pairs array.
     *
     * @param n
     * @param conflictingPairs
     * @return
     */
    public static long maxSubarrays(int n, int[][] conflictingPairs) {
        List<Integer>[] right = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            right[i] = new ArrayList<>();
        }
        for (int[] pair : conflictingPairs) {
            right[Math.max(pair[0], pair[1])].add(Math.min(pair[0], pair[1]));
        }

        long result = 0;
        long[] left = {0, 0}; // left[0] is top1, left[1] is top2
        long[] bonus = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int l_val : right[i]) {
                // Manually update top two values
                if (l_val > left[0]) {
                    left[1] = left[0];
                    left[0] = l_val;
                } else if (l_val > left[1]) {
                    left[1] = l_val;
                }
            }

            result += i - left[0];

            if (left[0] > 0) {
                bonus[(int) left[0]] += left[0] - left[1];
            }
        }

        long maxBonus = 0;
        for (long b : bonus) {
            maxBonus = Math.max(maxBonus, b);
        }

        return result + maxBonus;
    }

    private static void check(int n, int[][] conflictingPairs, long expected) {
        System.out.println("n is: " + n);
        System.out.println("conflictingPairs is: ");
        for (int[] conflictingPair : conflictingPairs) {
            System.out.println(Arrays.toString(conflictingPair));
        }
        System.out.println("expected is: " + expected);
        long maxSubarrays = maxSubarrays(n, conflictingPairs); // Calls your implementation
        System.out.println("maxSubarrays is: " + maxSubarrays);
    }
}
