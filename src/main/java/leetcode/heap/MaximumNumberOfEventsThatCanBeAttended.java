package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumNumberOfEventsThatCanBeAttended {

    public static void main(String[] args) {
        check(new int[][]{{1, 2}, {2, 3}, {3, 4}}, 3);
        check(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 2}}, 4);
        check(new int[][]{{1, 2}, {1, 2}, {3, 3}, {1, 5}, {1, 5}}, 5);

    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended.
     * This solution sorts the events by start day and iterates them, keeping a min heap of the end
     * days, so that eligible events with smaller end dates are selected first. Time complexity is O(nlogn)
     * where n is the length of the events array.
     *
     * @param events
     * @return
     */
    public static int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int count = 0;
        int currentDay = events[0][0];
        int index = 0;
        while (index < events.length || !queue.isEmpty()) {
            while (index < events.length && events[index][0] <= currentDay) {
                queue.offer(events[index]);
                index++;
            }
            while (!queue.isEmpty()) {
                int[] event = queue.poll();
                if (event[0] <= currentDay && currentDay <= event[1]) {
                    count++;
                    break;
                }
            }
            currentDay++;
        }
        return count;
    }

    public static int maxEvents2(int[][] events) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int currentDay = 0;
        int index = 0;
        int count = 0;
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        while (!priorityQueue.isEmpty() || index < events.length) {
            if (priorityQueue.isEmpty()) {
                // update the current day if no end day exists in the queue
                currentDay = events[index][0];
            }
            while (index < events.length && events[index][0] <= currentDay) {
                // add to the queue the end days of all events with start day less than or equal to the current day
                // so that we can remove each time the one with the smallest end day
                priorityQueue.offer(events[index][1]);
                index++;
            }
            // remove the event with the smallest end day and increase count
            priorityQueue.poll();
            count++;
            // advance current day
            currentDay++;
            // remove all events with past end days from the queue, they can't be attended
            while (!priorityQueue.isEmpty() && priorityQueue.peek() < currentDay) {
                priorityQueue.poll();
            }
        }
        return count;
    }

    private static void check(int[][] events, int expected) {
        System.out.println("events is: ");
        for (int[] event : events) {
            System.out.println(Arrays.toString(event));
        }
        System.out.println("expected is: " + expected);
        int maxEvents = maxEvents(events); // Calls your implementation
        System.out.println("maxEvents is: " + maxEvents);
    }
}
