package leetcode.multidimensionaldynamicprogramming;

import java.util.Arrays;

public class MaximumNumberOfEventsThatCanBeAttended2 {

    public static void main(String[] args) {
        check(new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 1}}, 2, 7);
        check(new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 10}}, 2, 10);
        check(new int[][]{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4}}, 3, 9);

    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii.
     * This solution uses multidimensional dynamic programming where one dimension is the total number
     * of events and the other dimension is the number of selected events. Solving all subproblems bottom
     * up leads to the final solution. Time complexity is O(n*k*logn) where n is the length of the events
     * array.
     *
     * @param events
     * @param k
     * @return
     */
    public static int maxValue(int[][] events, int k) {
        // sort events by end date
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        // keeps solution to subproblems, selecting up to k events from up to events.length total events
        int[][] dpArray = new int[events.length + 1][k + 1];
        // iterate all events
        for (int i = 1; i <= events.length; i++) {
            int[] event = events[i - 1];
            // find number of events ending before the start of this one
            int prev = binarySearch(events, event[0]);
            for (int j = 1; j <= k; j++) {
                // update dpArray by selecting from 1 up to k events from i events
                // this is equal to the max between selecting j events from i - 1 events
                // and selecting all previous events as well as the current one
                dpArray[i][j] = Math.max(dpArray[i - 1][j], dpArray[prev + 1][j - 1] + event[2]);
            }
        }
        return dpArray[events.length][k];
    }

    private static int binarySearch(int[][] events, int currentStart) {
        int left = 0, right = events.length;
        int result = -1;

        while (left < right) {
            int mid = (left + right) >>> 1;
            if (events[mid][1] < currentStart) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return result;
    }

    private static void check(int[][] events, int k, int expected) {
        System.out.println("events is: ");
        for (int[] event : events) {
            System.out.println(Arrays.toString(event));
        }
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int maxValue = maxValue(events, k); // Calls your implementation
        System.out.println("maxValue is: " + maxValue);
    }
}
