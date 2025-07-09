package leetcode.intervals;

import java.util.Arrays;

public class RescheduleMeetingsForMaximumFreeTime {

    public static void main(String[] args) {
        check(5, 1, new int[]{1, 3}, new int[]{2, 5}, 2);
        check(10, 1, new int[]{0, 2, 9}, new int[]{1, 4, 10}, 6);
        check(5, 2, new int[]{0, 1, 2, 3, 4}, new int[]{1, 2, 3, 4, 5}, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i.
     * This solution calculates the free time between meetings and uses a sliding window to find
     * the largest sum between k+1 contiguous free times. Time complexity is O(n) where n is the length
     * of the start time array;
     *
     * @param eventTime
     * @param k
     * @param startTime
     * @param endTime
     * @return
     */
    public static int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        // calculate all free time intervals
        int[] freeTime = new int[startTime.length + 1];
        int prevEndTime = 0;
        for (int i = 0; i < startTime.length; i++) {
            freeTime[i] = startTime[i] - prevEndTime;
            prevEndTime = endTime[i];
        }
        freeTime[startTime.length] = eventTime - prevEndTime;
        // initialize sliding window of length k + 1
        int maxFreeTime = 0;
        for (int i = 0; i <= k; i++) {
            maxFreeTime += freeTime[i];
        }
        int currentFreeTime = maxFreeTime;
        int limit = k + 1;
        // move sliding window to find max free time
        for (int i = limit; i < freeTime.length; i++) {
            currentFreeTime -= freeTime[i - limit];
            currentFreeTime += freeTime[i];
            maxFreeTime = Math.max(currentFreeTime, maxFreeTime);
        }
        return maxFreeTime;
    }

    private static void check(int eventTime, int k, int[] startTime, int[] endTime, int expected) {
        System.out.println("eventTime is: " + eventTime);
        System.out.println("k is: " + k);
        System.out.println("startTime is: " + Arrays.toString(startTime));
        System.out.println("endTime is: " + Arrays.toString(endTime));
        System.out.println("expected is: " + expected);
        int maxFreeTime = maxFreeTime(eventTime, k, startTime, endTime); // Calls your implementation
        System.out.println("maxFreeTime is: " + maxFreeTime);
    }
}
