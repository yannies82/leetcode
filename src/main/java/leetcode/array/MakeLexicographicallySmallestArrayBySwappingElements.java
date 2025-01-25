package leetcode.array;

import java.util.Arrays;

public class MakeLexicographicallySmallestArrayBySwappingElements {

	public static void main(String[] args) {
		check(new int[] { 1, 5, 3, 9, 8 }, 2, new int[] { 1, 3, 5, 8, 9 });
		check(new int[] { 1, 7, 6, 18, 2, 1 }, 3, new int[] { 1, 6, 7, 18, 1, 2 });
		check(new int[] { 1, 7, 28, 19, 10 }, 3, new int[] { 1, 7, 28, 19, 10 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements.
	 * This solution sorts numbers while keeping their initial indexes, then splits
	 * them in groups as long as they are within the limit, then it assigns values
	 * to the result according to the sorted group indexes. Time complexity is
	 * O(nlogn) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param limit
	 * @return
	 */
	public static int[] lexicographicallySmallestArray(int[] nums, int limit) {
		int n = nums.length;

		// this array keeps each number along with its index
		int[][] sortedPairs = new int[n][2];
		for (int i = 0; i < n; i++) {
			sortedPairs[i][0] = nums[i];
			sortedPairs[i][1] = i;
		}

		// sort number - index pairs by the numbers
		Arrays.sort(sortedPairs, (a, b) -> Integer.compare(a[0], b[0]));

		int[] result = new int[n];
		int groupStart = 0;
		int[] groupIndices = new int[n];
		for (int i = 0; i < n; i++) {
			// add index to the current group
			groupIndices[i] = sortedPairs[i][1];
			// if the next number is within the limit then it is contained in the
			// current group and we continue to the next number
			if (i == n - 1 || sortedPairs[i + 1][0] - sortedPairs[i][0] > limit) {
				// the current group has ended, accumulate sort indexes within the group range
				Arrays.sort(groupIndices, groupStart, i + 1);

				// assign values to the result according to the sorted indices
				for (int j = groupStart; j <= i; j++) {
					result[groupIndices[j]] = sortedPairs[j][0];
				}
				groupStart = i + 1; // Next group
			}
		}
		return result;
	}

	/**
	 * Similar solution, has an inner loop for filling the group indices. Time
	 * complexity is O(nlogn) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param limit
	 * @return
	 */
	public static int[] lexicographicallySmallestArray2(int[] nums, int limit) {
		int n = nums.length;

		// this array keeps each number along with its index
		int[][] sortedPairs = new int[n][2];
		for (int i = 0; i < n; i++) {
			sortedPairs[i][0] = nums[i];
			sortedPairs[i][1] = i;
		}

		// sort number - index pairs by the numbers
		Arrays.sort(sortedPairs, (a, b) -> Integer.compare(a[0], b[0]));

		int[] result = new int[n];
		int groupStart = 0;

		for (int i = 0; i < n; i++) {
			// if the next number is within the limit then it is contained in the
			// current group and we continue to the next number
			if (i == n - 1 || sortedPairs[i + 1][0] - sortedPairs[i][0] > limit) {
				// the current group has ended, accumulate and sort indexes
				int groupLength = i - groupStart + 1;
				int[] groupIndices = new int[groupLength];
				for (int j = 0; j < groupLength; j++) {
					groupIndices[j] = sortedPairs[groupStart + j][1];
				}
				Arrays.sort(groupIndices);

				// assign values to the result according to the sorted indices
				for (int j = 0; j < groupLength; j++) {
					result[groupIndices[j]] = sortedPairs[groupStart + j][0];
				}
				groupStart = i + 1; // Next group
			}
		}
		return result;
	}

	private static void check(int[] nums, int limit, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("limit is: " + limit);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] lexicographicallySmallestArray = lexicographicallySmallestArray(nums, limit); // Calls your implementation
		System.out.println("lexicographicallySmallestArray is: " + Arrays.toString(lexicographicallySmallestArray));
	}
}
