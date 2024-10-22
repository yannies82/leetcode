package leetcode.array;

import java.util.List;

public class MaximumDistanceInArrays {

	public static void main(String[] args) {
		check(List.of(List.of(1, 2, 3), List.of(4, 5), List.of(1, 2, 3)), 4);
		check(List.of(List.of(1), List.of(1)), 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/maximum-distance-in-arrays.
	 * This solution calculates the min, secondMin, max and secondMax values from
	 * all arrays, along with the index of the array for each value. Time complexity
	 * is O(n) where n is the length of the arrays list.
	 * 
	 * @param arrays
	 * @return
	 */
	public static int maxDistance(List<List<Integer>> arrays) {
		// initialize min, secondMin, max, secondMax arrays
		// first element of every array is the value, second element
		// is the index of the containing array
		int[] min = new int[] { Integer.MAX_VALUE, -1 };
		int[] secondMin = new int[] { Integer.MAX_VALUE, -1 };
		int[] max = new int[] { Integer.MIN_VALUE, -1 };
		int[] secondMax = new int[] { Integer.MIN_VALUE, -1 };
		int m = arrays.size();
		// iterate all arrays and populate min, secondMin, max, secondMax
		for (int i = 0; i < m; i++) {
			List<Integer> currentArray = arrays.get(i);
			int currentMin = currentArray.get(0);
			int currentMax = currentArray.get(currentArray.size() - 1);
			if (currentMin < min[0]) {
				secondMin[0] = min[0];
				secondMin[1] = min[1];
				min[0] = currentMin;
				min[1] = i;
			} else if (currentMin < secondMin[0]) {
				secondMin[0] = currentMin;
				secondMin[1] = i;
			}
			if (currentMax > max[0]) {
				secondMax[0] = max[0];
				secondMax[1] = max[1];
				max[0] = currentMax;
				max[1] = i;
			} else if (currentMax > secondMax[0]) {
				secondMax[0] = currentMax;
				secondMax[1] = i;
			}
		}
		if (min[1] != max[1]) {
			// min and max element belong to different arrays, return their diff
			return max[0] - min[0];
		}
		// min and max element belong to same array, therefore return either
		// max - secondMin or secondMax - min
		return Math.max(secondMax[0] - min[0], max[0] - secondMin[0]);
	}

	private static void check(List<List<Integer>> arrays, int expected) {
		System.out.println("arrays is: " + arrays);
		System.out.println("expected is: " + expected);
		int maxDistance = maxDistance(arrays); // Calls your implementation
		System.out.println("maxDistance is: " + maxDistance);
	}
}
