package leetcode.intervals;

import java.util.Arrays;

public class RescheduleMeetingsForMaximumFreeTime2 {

    public static void main(String[] args) {
        check(5, new int[]{1, 3}, new int[]{2, 5}, 2);
        check(10, new int[]{0, 7, 9}, new int[]{1, 8, 10}, 7);
        check(10, new int[]{0, 3, 7, 9}, new int[]{1, 4, 8, 10}, 6);
        check(41, new int[]{17, 24}, new int[]{19, 25}, 24);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i.
     * This solution calculates the free time between meetings checks for each free time pair if there is a big enough
     * free time interval on the left or on the right to fit the current meeting. Time complexity is O(n) where n is the length
     * of the start time array;
     *
     * @param eventTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int prevEndTime = 0;
        int maxFreeTimeLeft = 0;
        int maxFreeTime = 0;
        // calculate all free times
        int[] freeTime = new int[startTime.length + 1];
        for (int i = 0; i < startTime.length; i++) {
            freeTime[i] = startTime[i] - prevEndTime;
            prevEndTime = endTime[i];
        }
        freeTime[startTime.length] = eventTime - prevEndTime;
        // keep the max right free time for each position
        int[] maxFreeTimesRight = new int[startTime.length + 1];
        for (int i = startTime.length - 1; i >= 0; i--) {
            maxFreeTimesRight[i] = Math.max(maxFreeTimesRight[i + 1], freeTime[i + 1]);
        }
        // for each pair of free time intervals, if the meeting between them fits in a free time gap
        // on the left or on the right then the meeting's duration can be added to the max free time
        for (int i = 1; i <= startTime.length; i++) {
            int duration = endTime[i - 1] - startTime[i - 1];
            if (maxFreeTimeLeft >= duration || maxFreeTimesRight[i] >= duration) {
                maxFreeTime = Math.max(maxFreeTime, freeTime[i - 1] + duration + freeTime[i]);
            }
            maxFreeTime = Math.max(maxFreeTime, freeTime[i - 1] + freeTime[i]);
            maxFreeTimeLeft = Math.max(maxFreeTimeLeft, freeTime[i - 1]);
        }
        return maxFreeTime;
    }

    private static void check(int eventTime, int[] startTime, int[] endTime, int expected) {
        System.out.println("eventTime is: " + eventTime);
        System.out.println("startTime is: " + Arrays.toString(startTime));
        System.out.println("endTime is: " + Arrays.toString(endTime));
        System.out.println("expected is: " + expected);
        int maxFreeTime = maxFreeTime(eventTime, startTime, endTime); // Calls your implementation
        System.out.println("maxFreeTime is: " + maxFreeTime);
    }
}
