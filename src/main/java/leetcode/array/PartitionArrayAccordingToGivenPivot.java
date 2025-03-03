package leetcode.array;

import java.util.Arrays;

public class PartitionArrayAccordingToGivenPivot {

	public static void main(String[] args) {
		check(new int[] { 9, 12, 5, 10, 14, 3, 10 }, 10, new int[] { 9, 5, 3, 10, 10, 12, 14 });
		check(new int[] { -3, 4, 3, 2 }, 2, new int[] { -3, 2, 4, 3 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/partition-array-according-to-given-pivot. Time
	 * complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param pivot
	 * @return
	 */
	public static int[] pivotArray(int[] nums, int pivot) {
		int startIndex = 0;
		int endIndex = nums.length - 1;
		int[] result = new int[nums.length];
		// populate the start of the results array with all elements less than pivot
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < pivot) {
				result[startIndex++] = nums[i];
			}
		}
		// populate the end of the results array with all elements greater than pivot
		for (int i = endIndex; i >= 0; i--) {
			if (nums[i] > pivot) {
				result[endIndex--] = nums[i];
			}
		}
		// populate the rest of the positions with pivot
		for (int i = startIndex; i <= endIndex; i++) {
			result[i] = pivot;
		}
		return result;
	}

	private static void check(int[] nums, int pivot, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("pivot is: " + pivot);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] pivotArray = pivotArray(nums, pivot); // Calls your implementation
		System.out.println("pivotArray is: " + Arrays.toString(pivotArray));
	}
}
