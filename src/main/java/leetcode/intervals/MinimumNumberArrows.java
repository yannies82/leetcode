package leetcode.intervals;

import java.util.Arrays;

public class MinimumNumberArrows {

	public static void main(String[] args) {
		check(new int[][] { { 2, 8 }, { 1, 6 }, { 7, 12 } }, 2);
		check(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } }, 2);
		check(new int[][] { { 1, 3 }, { 3, 4 }, { 4, 5 }, { 4, 8 }, { 4, 7 } }, 2);
		check(new int[][] { { 10, 16 }, { 2, 8 }, { 1, 6 }, { 7, 12 } }, 2);
		check(new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 } }, 4);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons.
	 * This solution sorts all baloons by end index. It then traverses all balloons
	 * and greedily sends arrows to burst baloons whenever the start of a new
	 * balloon is after the end of the previous one. Time complexity is O(n*logn)
	 * where n is the length of the points array.
	 * 
	 * @param points
	 * @return
	 */
	public static int findMinArrowShots(int[][] points) {
		int pointsCount = points.length;
		// sort the points array by end index of the balloons
		Arrays.sort(points, (o1, o2) -> Integer.compare(o1[1], o2[1]));
		// initialize arrow count, at least one arrow will be needed
		int arrowCount = 1;
		// initialize end index to the end of the first balloon
		int end = points[0][1];
		// traverse all balloons after the first one
		for (int i = 1; i < pointsCount; i++) {
			if (points[i][0] > end) {
				// if the next balloon starts after the previous end index, then it will need a
				// separate arrow to be burst
				// add to arrowCount and update the end index
				arrowCount++;
				end = points[i][1];
			}
		}
		return arrowCount;
	}

	private static void check(int[][] points, int expectedShots) {
		System.out.println("points is: ");
		for (int[] point : points) {
			System.out.println(Arrays.toString(point));
		}
		System.out.println("expectedShots is: " + expectedShots);
		int findMinArrowShots = findMinArrowShots(points); // Calls your implementation
		System.out.println("findMinArrowShots is: " + findMinArrowShots);
	}
}
