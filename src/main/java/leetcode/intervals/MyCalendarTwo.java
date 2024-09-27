package leetcode.intervals;

import java.util.function.BiFunction;

public class MyCalendarTwo {

	public static void main(String[] args) {
		MyCalendar calendar = new MyCalendar();
		check(10, 20, calendar::book, true);
		check(50, 60, calendar::book, true);
		check(10, 40, calendar::book, true);
		check(5, 15, calendar::book, false);
		check(5, 10, calendar::book, true);
		check(25, 55, calendar::book, true);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/my-calendar-ii. This solution
	 * uses 2 arraylists, one to keep allEvents and the other one to keep only
	 * overlapping intervals. Time complexity for the book method is O(n) where n is
	 * the number of elements in the allEvents list.
	 * 
	 * @author yanni
	 *
	 */
	private static class MyCalendar {

		// use 2 lists, one keeps all events and the other keeps only overlapping
		// intervals
		int[][] allEvents = new int[1000][2];
		int[][] overlappedEvents = new int[1000][2];

		int allEventsIndex = 0;
		int overlappedEventsIndex = 0;

		public boolean book(int start, int end) {
			for (int i = 0; i < overlappedEventsIndex; i++) {
				// if any overlapping interval is overlapping with the new event then return
				// false
				if (Math.max(overlappedEvents[i][0], start) < Math.min(overlappedEvents[i][1], end)) {
					return false;
				}
			}
			for (int i = 0; i < allEventsIndex; i++) {
				// if any existing event is overlapping with the new one, add the overlapping
				// interval to the overlappedEvents array
				if (Math.max(allEvents[i][0], start) < Math.min(allEvents[i][1], end)) {
					overlappedEvents[overlappedEventsIndex][0] = Math.max(allEvents[i][0], start);
					overlappedEvents[overlappedEventsIndex++][1] = Math.min(allEvents[i][1], end);
				}
			}
			allEvents[allEventsIndex][0] = start;
			allEvents[allEventsIndex++][1] = end;
			return true;
		}
	}

	private static void check(int start, int end, BiFunction<Integer, Integer, Boolean> function, boolean expected) {
		System.out.println(
				"book [" + start + ", " + end + "] is: " + function.apply(start, end) + ", expected is: " + expected);
	}

}
