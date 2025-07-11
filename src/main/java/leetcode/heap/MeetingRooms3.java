package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MeetingRooms3 {

    public static void main(String[] args) {
        check(2, new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}}, 0);
        check(3, new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}}, 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/meeting-rooms-iii.
     * Time complexity is O(nlogn) where n is the length of the meetings array.
     *
     * @param n
     * @param meetings
     * @return
     */
    public static int mostBooked(int n, int[][] meetings) {
        // sort meetings by start date
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        // keeps count of meetings per room
        int[] count = new int[n];
        // keep free rooms in a min heap
        Queue<Integer> free = new PriorityQueue<>();
        Queue<long[]> busy = new PriorityQueue<>((a, b) -> {
            int result = Long.compare(a[0], b[0]);
            return result == 0 ? Long.compare(a[1], b[1]) : result;
        });

        for (int i = 0; i < n; i++) {
            free.offer(i);
        }

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                free.offer((int)busy.poll()[1]);
            }
            if (!free.isEmpty()) {
                int room = free.poll();
                busy.offer(new long[]{end, room});
                count[room]++;
            } else {
                long[] next = busy.poll();
                long newEnd = next[0] + (end - start);
                busy.offer(new long[]{newEnd, next[1]});
                count[(int)next[1]]++;
            }
        }

        int max = 0;
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] > max) {
                max = count[i];
                result = i;
            }
        }
        return result;
    }

    private static void check(int n, int[][] meetings, int expected) {
        System.out.println("n is: " + n);
        System.out.println("meetings is: ");
        for (int[] meeting : meetings) {
            System.out.println(Arrays.toString(meeting));
        }
        System.out.println("expected is: " + expected);
        int mostBooked = mostBooked(n, meetings); // Calls your implementation
        System.out.println("mostBooked is: " + mostBooked);
    }
}
