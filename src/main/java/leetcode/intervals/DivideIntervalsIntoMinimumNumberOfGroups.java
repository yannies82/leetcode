package leetcode.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class DivideIntervalsIntoMinimumNumberOfGroups {

	public static void main(String[] args) {
		check(new int[][] { { 5, 10 }, { 6, 8 }, { 1, 5 }, { 2, 3 }, { 1, 10 } }, 3);
		check(new int[][] { { 1, 3 }, { 5, 6 }, { 8, 10 }, { 11, 13 } }, 1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups.
	 * This solution counts the number of maxConcurrentOccurrences for the
	 * intervals, i.e. the max number of intervals that overlap with each other.
	 * This is equal to the min number of separate groups which should be created.
	 * Time complexity is O(n+m) where n is the length of the intervals array and m
	 * is the range of numbers in the intervals array.
	 * 
	 * 
	 * @param intervals
	 * @return
	 */
	public static int minGroups(int[][] intervals) {
		// find the minimum and maximum value in the intervals
		int min = intervals[0][0];
		int max = intervals[0][1];
		for (int i = 1; i < intervals.length; i++) {
			min = Math.min(min, intervals[i][0]);
			max = Math.max(max, intervals[i][1]);
		}

		// Initialize the range array which will keep the occurrences of numbers
		int[] range = new int[max - min + 2];
		for (int i = 0; i < intervals.length; i++) {
			// increase occurrences at the start of the interval
			range[intervals[i][0] - min]++;
			// decrease occurrences right after the end of the interval
			range[intervals[i][1] - min + 1]--;
		}
		// iterate the range and count the concurrent occurrences
		// they increase by one when an interval starts and decrease by 1 after an
		// interval ends
		int concurrentOccurrences = 0;
		int maxConcurrentOccurrences = 0;
		for (int i = 0; i < range.length; i++) {
			concurrentOccurrences += range[i];
			// update maxConcurrentOccurrences if needed
			maxConcurrentOccurrences = Math.max(concurrentOccurrences, maxConcurrentOccurrences);
		}

		return maxConcurrentOccurrences;
	}

	/**
	 * Alternative solution which keeps two separate arrays of start and end values,
	 * sorts the arrays and iterates the start array comparing its values to the end
	 * values. Time complexity is O(nlogn) where n is the length of the intervals
	 * array.
	 * 
	 * @param intervals
	 * @return
	 */
	public static int minGroups2(int[][] intervals) {
		// keep two separate arrays with all start and end values respectively from the
		// intervals
		int[] start = new int[intervals.length];
		int[] end = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			start[i] = intervals[i][0];
			end[i] = intervals[i][1];
		}
		// sort the start and end arrays
		Arrays.sort(start);
		Arrays.sort(end);
		int endIndex = 0;
		// iterate the start array to find concurrentOccurrences by comparing to end
		// array values
		for (int i = 1; i < start.length; i++) {
			if (start[i] > end[endIndex]) {
				// the current start value is greater than the previous end value, increase
				// endIndex
				endIndex++;
			}
		}
		// the difference between the length of the array and the endIndex is the
		// count of intervals whose start was less than or equal to the end of a
		// previous interval, therefore the min number of distinct groups required
		return start.length - endIndex;
	}

	/**
	 * Alternate solution which sorts the input array and uses a priority queue.
	 * Time complexity is O(nlogn) where n is the length of the intervals array.
	 * 
	 * @param intervals
	 * @return
	 */
	public static int minGroups3(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
		// keeps the intervals sorted by their right value, is meant to keep the last
		// interval per group
		Queue<int[]> sortedByEndQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		sortedByEndQueue.offer(intervals[0]);
		// iterate intervals by left value
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] > sortedByEndQueue.peek()[1]) {
				// the start of the current interval is after the end of one of the previous
				// ones, they can exist in the same group, therefore remove the previous one
				// from the queue
				sortedByEndQueue.poll();
			}
			// add the current interval to the queue
			sortedByEndQueue.offer(intervals[i]);
		}
		return sortedByEndQueue.size();
	}

	private static void check(int[][] intervals, int expected) {
		System.out.println("intervals is: ");
		for (int[] interval : intervals) {
			System.out.println(Arrays.toString(interval));
		}
		System.out.println("expected is: " + expected);
		int minGroups = minGroups(intervals); // Calls your implementation
		System.out.println("minGroups is: " + minGroups);
	}
}
