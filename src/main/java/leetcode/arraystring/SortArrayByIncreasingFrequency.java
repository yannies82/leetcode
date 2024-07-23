package leetcode.arraystring;

import java.util.Arrays;

public class SortArrayByIncreasingFrequency {

	public static void main(String[] args) {
		check(new int[] { 1, 1, 2, 2, 2, 3 }, new int[] { 3, 1, 1, 2, 2, 2 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/sort-array-by-increasing-frequency. This
	 * solution uses a 2D array to keep the frequency of each number, then sorts the
	 * array and iterates it to produce the final result. Time complexity is O(n)
	 * where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] frequencySort(int[] nums) {
		int[][] frequencies = new int[201][2];
		for (int i = 0; i < nums.length; i++) {
			int index = nums[i] + 100;
			frequencies[index][0] = nums[i];
			frequencies[index][1]++;
		}
		Arrays.sort(frequencies, (a, b) -> {
			int diff = a[1] - b[1];
			return diff == 0 ? b[0] - a[0] : diff;
		});
		int resultIndex = 0;
		int[] result = new int[nums.length];
		for (int i = 0; i < frequencies.length; i++) {
			if (frequencies[i][1] > 0) {
				for (int j = resultIndex; j < resultIndex + frequencies[i][1]; j++) {
					result[j] = frequencies[i][0];
				}
				resultIndex += frequencies[i][1];
			}
		}
		return result;
	}

	private static void check(int[] nums, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] frequencySort = frequencySort(nums); // Calls your implementation
		System.out.println("frequencySort is: " + Arrays.toString(frequencySort));
	}
}
