package leetcode.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {

	public static void main(String[] args) {
		check(new int[][] { { 1, 0 }, { 0, 0 } }, 2);
		check(new int[][] { { 1, 1 }, { 3, 2 }, { 5, 3 }, { 4, 1 }, { 2, 3 }, { 1, 4 } }, 4);
	}

	/**
	 * This solution calculates the tangent of the lines between every two distinct
	 * points and keeps a count of the lines which have the same tangent for each
	 * separate point. Time complexity is O(n^2) where n is the number of points.
	 * 
	 * @param points
	 * @return
	 */
	public static int maxPoints(int[][] points) {
		// early exit in case only 1 point exists
		if (points.length == 1) {
			return 1;
		}
		// keeps the count of the max lines with the same tangent
		int maxLineCount = 1;
		// iterate for every point as starting except for the last two
		for (int i = 0; i < points.length - 2; i++) {
			// initialize a map which keeps the count of lines with same tangent
			Map<Integer, Integer> tangentCount = new HashMap<>();
			// iterate for every point after the starting one
			for (int j = i + 1; j < points.length; j++) {
				// calculate the tangent between the 2 points
				int tangent = calculateTangent(points[i], points[j]);
				// update the tangent count of the map entry
				int newCount = tangentCount.getOrDefault(tangent, 0) + 1;
				tangentCount.put(tangent, newCount);
				// update the max count if the new count is greater
				if (newCount > maxLineCount) {
					maxLineCount = newCount;
				}
			}
		}
		// x lines imply x+1 points
		return maxLineCount + 1;
	}

	private static int calculateTangent(int[] point1, int[] point2) {
		int dx1 = point2[0] - point1[0];
		int dy1 = point2[1] - point1[1];
		// if dx1 is 0 set the tangent to a value representing infinite
		// otherwise multiply dy1 with 10000 before dividing with dx1
		// so that the tangent can be kept as an integer with
		// sufficient accuracy and can be used as a key in a map
		return dx1 == 0 ? Integer.MAX_VALUE : dy1 * 10000 / dx1;
	}

	private static void check(int[][] points, int expected) {
		System.out.println("points is: ");
		for (int i = 0; i < points.length; i++) {
			System.out.println(Arrays.toString(points[i]));
		}
		System.out.println("expected is: " + expected);
		int maxPoints = maxPoints(points); // Calls your implementation
		System.out.println("maxPoints is: " + maxPoints);
	}

}
