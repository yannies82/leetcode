package leetcode.binarysearch;

import java.util.Arrays;

public class MagneticForceBetweenTwoBalls {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4, 7 }, 3, 3);
		check(new int[] { 5, 4, 3, 2, 1, 1000000000 }, 2, 999999999);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/magnetic-force-between-two-balls. This solution
	 * sorts the position array, then calculates the max possible distance between
	 * the elements. Finally, it uses binary search to find the maximum magnetic
	 * force for which m balls can be placed. Time complexity is O(klogn) where n is
	 * the length of the position array and k is the distance between the smallest
	 * and the largest position.
	 * 
	 * @param position
	 * @param m
	 * @return
	 */
	public static int maxDistance(int[] position, int m) {
		// sort the position array
		Arrays.sort(position);
		int min = position[0];
		int max = position[position.length - 1];
		if (m == 2) {
			// early exit if just two balls will be placed,
			// we know they will be placed at the first and last positions
			return max - min;
		}
		// calculate max possible distance
		int maxDistance = (max - min) / (m - 1);
		int low = 1;
		int high = maxDistance;
		int result = -1;
		// perform binary search to find the max distance between
		// 1 and maxDistance for which at least m balls can be placed
		while (low <= high) {
			int mid = (low + high) / 2;
			int count = 1;
			int prev = position[0];
			for (int i = 1; i < position.length && count < m; i++) {
				if (position[i] - prev >= mid) {
					// the next ball must have at least mid distance from the previous one
					prev = position[i];
					count++;
				}
			}
			if (count >= m) {
				// update result in this case, since at least m balls were placed
				result = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return result;
	}

	private static void check(int[] position, int m, int expected) {
		System.out.println("position is: " + Arrays.toString(position));
		System.out.println("m is: " + m);
		System.out.println("expected is: " + expected);
		int maxDistance = maxDistance(position, m); // Calls your implementation
		System.out.println("maxDistance is: " + maxDistance);
	}
}
