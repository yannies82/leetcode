package leetcode.binarysearch;

import java.util.Arrays;
import java.util.TreeMap;

public class TwoBestNonOverlappingEvents {

	public static void main(String[] args) {
		check(new int[][] { { 1, 3, 2 }, { 4, 5, 2 }, { 2, 4, 3 } }, 4);
		check(new int[][] { { 1, 3, 2 }, { 4, 5, 2 }, { 1, 5, 5 } }, 5);
		check(new int[][] { { 1, 5, 3 }, { 1, 5, 1 }, { 6, 6, 5 } }, 8);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/two-best-non-overlapping-events. This solution
	 * calculates the best values for each event, then uses binary search for each
	 * event to add the best value of non overlapping events to its value and
	 * calculate the max. Time complexity is O(nlogn) where n is the length of the
	 * events array.
	 * 
	 * @param events
	 * @return
	 */
	public static int maxTwoEvents2(int[][] events) {
		Arrays.sort(events, (a, b) -> a[0] - b[0]);
		// this array keeps the max value for each event
		int[] maxValuesByEvent = new int[events.length + 1];
		int currentStartMaxValue = 0;
		for (int i = events.length - 1; i >= 0; i--) {
			currentStartMaxValue = Math.max(currentStartMaxValue, events[i][2]);
			maxValuesByEvent[i] = currentStartMaxValue;
		}
		int totalMaxValue = 0;
		for (int i = events.length - 1; i >= 0; i--) {
			// calculate the best value for this event by finding the next non overlapping
			// event and adding its max value
			int nextEventIndex = binarySearch(events, i + 1, events[i][1]);
			int currentMaxValue = events[i][2] + maxValuesByEvent[nextEventIndex];
			totalMaxValue = Math.max(totalMaxValue, currentMaxValue);
		}
		return totalMaxValue;
	}

	private static int binarySearch(int[][] events, int startIndex, int value) {
		int end = events.length;
		while (startIndex < end) {
			int mid = (startIndex + end) / 2;
			if (events[mid][0] > value) {
				end = mid;
			} else {
				startIndex = mid + 1;
			}
		}
		return startIndex;
	}

	/**
	 * This solution keeps two separate arrays with the start and end times along
	 * with the respective indexes. It then sorts these arrays and iterates them at
	 * the same time to calculate the result. Time complexity is O(nlogn) where n is
	 * the length of the events array.
	 * 
	 * @param events
	 * @return
	 */
	public static int maxTwoEvents(int[][] events) {
		// populate startTimes and endTimes arrays
		int[][] startTimes = new int[events.length][2];
		int[][] endTimes = new int[events.length][2];
		for (int i = 0; i < events.length; i++) {
			startTimes[i][0] = events[i][0];
			startTimes[i][1] = i;
			endTimes[i][0] = events[i][1] + 1;
			endTimes[i][1] = i;
		}
		// sort the arrays by their time values
		Arrays.sort(startTimes, (a, b) -> a[0] - b[0]);
		Arrays.sort(endTimes, (a, b) -> a[0] - b[0]);
		int prevSum = 0;
		int maxSum = 0;
		for (int i = 0, j = 0; i < events.length && j < events.length;) {
			if (startTimes[i][0] < endTimes[j][0]) {
				maxSum = Math.max(maxSum, prevSum + events[startTimes[i++][1]][2]);
			} else {
				prevSum = Math.max(prevSum, events[endTimes[j++][1]][2]);
			}
		}
		return maxSum;
	}

	/**
	 * This solution calculates the best values for each startTime, then uses a tree
	 * map to search for each event and add the best value of non overlapping start
	 * times to its value and calculate the max. Time complexity is O(nlogn) where n
	 * is the length of the events array.
	 * 
	 * @param events
	 * @return
	 */
	public static int maxTwoEvents3(int[][] events) {
		Arrays.sort(events, (a, b) -> a[0] - b[0]);
		// this treemap keeps the max value for each startTime
		TreeMap<Integer, Integer> maxValuesByStartTime = new TreeMap<>();
		int currentStartMaxValue = 0;
		for (int i = events.length - 1; i >= 0; i--) {
			currentStartMaxValue = Math.max(currentStartMaxValue, events[i][2]);
			maxValuesByStartTime.put(events[i][0], currentStartMaxValue);
		}
		// put a key as an upper limit with value 0
		maxValuesByStartTime.put(Integer.MAX_VALUE, 0);
		int totalMaxValue = 0;
		for (int i = 0; i < events.length; i++) {
			// calculate the best value for this event by finding the next startTime in
			// the treeMap which does not overlap with this event and adding its max value
			Integer nextKey = maxValuesByStartTime.ceilingKey(events[i][1] + 1);
			int currentMaxValue = events[i][2] + maxValuesByStartTime.get(nextKey);
			// update total max value
			totalMaxValue = Math.max(totalMaxValue, currentMaxValue);
		}
		return totalMaxValue;
	}

	private static void check(int[][] events, int expected) {
		System.out.println("points is: ");
		for (int[] event : events) {
			System.out.println(Arrays.toString(event));
		}
		System.out.println("expected is: " + expected);
		int maxTwoEvents = maxTwoEvents2(events); // Calls your implementation
		System.out.println("maxTwoEvents is: " + maxTwoEvents);
	}
}
