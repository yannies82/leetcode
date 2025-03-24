package leetcode.intervals;

import java.util.*;

public class CountDaysWithoutMeetings {

    public static void main(String[] args) {
        check(10, new int[][]{{5, 7}, {1, 3}, {9, 10}}, 2);
        check(5, new int[][]{{2, 4}, {1, 3}}, 1);
        check(6, new int[][]{{1, 6}}, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-days-without-meetings. This solution
     * sorts the meetings array in order to find missing intervals. Time complexity is O(nlogn)
     * where n is the length of the meetings array.
     *
     * @param days
     * @param meetings
     * @return
     */
    public static int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int freeDays = 0;
        int previousEndDay = 0;
        int i = 0;
        while (i < meetings.length) {
            freeDays = freeDays + meetings[i][0] - previousEndDay - 1;
            int endDay = meetings[i][1];
            while (++i < meetings.length && meetings[i][0] <= endDay) {
                endDay = Math.max(endDay, meetings[i][1]);
            }
            previousEndDay = endDay;
        }
        freeDays = freeDays + days - previousEndDay;
        return freeDays;
    }

    /**
     * This solution uses a range array to calculate the free days. Time complexity is O(m+n) where m is the number
     * of days and n is the length of the meetings array.
     *
     * @param days
     * @param meetings
     * @return
     */
    public static int countDays2(int days, int[][] meetings) {
        int[] range = new int[days + 2];
        for (int i = 0; i < meetings.length; i++) {
            range[meetings[i][0]]++;
            range[meetings[i][1] + 1]--;
        }
        int meetingsCount = 0;
        int freeDays = 0;
        for (int i = 1; i <= days; i++) {
            meetingsCount += range[i];
            // add 1 to freeDays if meetingsCount == 0
            freeDays += 1 - ((-meetingsCount >>> 31) & 1);
        }
        return freeDays;
    }

    public static int countDays3(int days, int[][] meetings) {
        Map<Integer, Integer> range = new TreeMap<>();
        for (int i = 0; i < meetings.length; i++) {
            range.put(meetings[i][0], range.getOrDefault(meetings[i][0], 0) + 1);
            int endDay = meetings[i][1] + 1;
            range.put(endDay, range.getOrDefault(endDay, 0) - 1);
        }
        int meetingsCount = 0;
        int freeDays = 0;
        int prevMeetingsCount = 0;
        int prevDay = 0;
        for (Map.Entry<Integer, Integer> entry : range.entrySet()) {
            if (prevMeetingsCount == 0) {
                freeDays += entry.getKey() - prevDay;
            }
            meetingsCount += entry.getValue();
            prevMeetingsCount = meetingsCount;
            prevDay = entry.getKey();
        }
        if (prevMeetingsCount == 0) {
            freeDays += days - prevDay;
        }
        return freeDays;
    }

    private static void check(int days, int[][] meetings, int expected) {
        System.out.println("days is: " + days);
        System.out.println("meetings is: ");
        for (int[] meeting : meetings) {
            System.out.println(Arrays.toString(meeting));
        }
        System.out.println("expected is: " + expected);
        int countDays = countDays(days, meetings); // Calls your implementation
        System.out.println("countDays is: " + countDays);
    }
}
