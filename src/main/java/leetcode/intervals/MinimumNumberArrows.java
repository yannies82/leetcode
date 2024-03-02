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

	public static int findMinArrowShots(int[][] points) {
		int pointsCount = points.length;
		Arrays.sort(points, (o1, o2) -> Integer.compare(o1[1], o2[1]));
		int arrowCount = 1;
		int end = points[0][1];
		for (int i = 1; i < pointsCount; i++) {
			if (points[i][0] > end) {
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
