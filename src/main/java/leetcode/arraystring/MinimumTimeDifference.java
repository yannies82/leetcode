package leetcode.arraystring;

import java.util.Arrays;
import java.util.List;

public class MinimumTimeDifference {

	public static void main(String[] args) {
		check(List.of("23:59", "00:00"), 1);
		check(List.of("00:00", "23:59", "00:00"), 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/minimum-time-difference. This
	 * solution converts the string times to minutes, sorts them and calculates the
	 * min diff. Time complexity is O(nlogn) where n is the size of the timePoints
	 * list.
	 * 
	 * @param timePoints
	 * @return
	 */
	public static int findMinDifference(List<String> timePoints) {
		int fixedOffset = 671 * '0';
		int size = timePoints.size();
		int[] timePointsArr = new int[size];
		boolean[] exists = new boolean[1440];
		for (int i = 0; i < size; i++) {
			String timePoint = timePoints.get(i);
			// convert string time to minutes, could have omitted subtracting the
			// fixedOffset but
			// then we would need a larger exists array
			timePointsArr[i] = 600 * timePoint.charAt(0) + 60 * timePoint.charAt(1) + 10 * timePoint.charAt(3)
					+ timePoint.charAt(4) - fixedOffset;
			// check if a time point exists twice in the array, early exit if so
			if (exists[timePointsArr[i]]) {
				return 0;
			}
			exists[timePointsArr[i]] = true;
		}
		// sort the array
		Arrays.sort(timePointsArr);
		// find the min diff
		int minDiff = Integer.MAX_VALUE;
		for (int i = 1; i < size; i++) {
			int diff = timePointsArr[i] - timePointsArr[i - 1];
			if (diff < minDiff) {
				minDiff = diff;
			}
		}
		// compare the smallest time with the largest time after adding the 24 hours
		// offset
		int firstTimeWithOffset = 1440 + timePointsArr[0];
		int firstDiff = firstTimeWithOffset - timePointsArr[size - 1];
		if (firstDiff < minDiff) {
			minDiff = firstDiff;
		}
		return minDiff;
	}

	private static void check(List<String> timePoints, int expected) {
		System.out.println("timePoints is: " + timePoints);
		System.out.println("expected is: " + expected);
		int findMinDifference = findMinDifference(timePoints); // Calls your implementation
		System.out.println("findMinDifference is: " + findMinDifference);
	}
}
