package leetcode.intervals;

import java.util.Arrays;

public class DetermineIfTwoEventsHaveConflict {

	public static void main(String[] args) {
		check(new String[] { "01:15", "02:00" }, new String[] { "02:00", "03:00" }, true);
		check(new String[] { "01:00", "02:00" }, new String[] { "01:20", "03:00" }, true);
		check(new String[] { "10:00", "11:00" }, new String[] { "14:00", "15:00" }, false);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/determine-if-two-events-have-conflict. Time
	 * complexity is O(1).
	 * 
	 * @param event1
	 * @param event2
	 * @return
	 */
	public static boolean haveConflict(String[] event1, String[] event2) {
		int start1 = ((event1[0].charAt(0) - '0') * 10 + event1[0].charAt(1) - '0') * 60
				+ (event1[0].charAt(3) - '0') * 10 + event1[0].charAt(4) - '0';
		int end1 = ((event1[1].charAt(0) - '0') * 10 + event1[1].charAt(1) - '0') * 60
				+ (event1[1].charAt(3) - '0') * 10 + event1[1].charAt(4) - '0';
		int start2 = ((event2[0].charAt(0) - '0') * 10 + event2[0].charAt(1) - '0') * 60
				+ (event2[0].charAt(3) - '0') * 10 + event2[0].charAt(4) - '0';
		int end2 = ((event2[1].charAt(0) - '0') * 10 + event2[1].charAt(1) - '0') * 60
				+ (event2[1].charAt(3) - '0') * 10 + event2[1].charAt(4) - '0';
		return Math.max(start1, start2) <= Math.min(end1, end2);
	}

	private static void check(String[] event1, String[] event2, boolean expected) {
		System.out.println("event1 is: " + Arrays.toString(event1));
		System.out.println("event2 is: " + Arrays.toString(event2));
		System.out.println("expected is: " + expected);
		boolean haveConflict = haveConflict(event1, event2); // Calls your implementation
		System.out.println("haveConflict is: " + haveConflict);
	}
}
