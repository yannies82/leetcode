package leetcode.arraystring;

import java.util.Arrays;

public class SortTheJumbledNumbers {

	public static void main(String[] args) {
		check(new int[] { 8, 9, 4, 0, 2, 1, 3, 5, 7, 6 }, new int[] { 991, 338, 38 }, new int[] { 338, 38, 991 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/sort-the-jumbled-numbers.
	 * This solution uses an intermediate array to stored the values paired with
	 * their mapped value and sorts it by the mapped value. Time complexity is
	 * O(nlogn) where n is the length of the nums array.
	 * 
	 * @param mapping
	 * @param nums
	 * @return
	 */
	public static int[] sortJumbled(int[] mapping, int[] nums) {
		int[][] mappedValues = new int[nums.length][2];
		for (int i = 0; i < nums.length; i++) {
			mappedValues[i][0] = nums[i];
			int mappedValue = 0;
			int value = nums[i];
			int factor = 1;
			do {
				mappedValue += mapping[(value % 10)] * factor;
				factor *= 10;
			} while ((value = value / 10) > 0);
			mappedValues[i][1] = mappedValue;
		}
		Arrays.sort(mappedValues, (a, b) -> a[1] - b[1]);
		int[] result = new int[nums.length];
		for (int i = 0; i < mappedValues.length; i++) {
			result[i] = mappedValues[i][0];
		}
		return result;
	}

	private static void check(int[] mapping, int[] nums, int[] expected) {
		System.out.println("mapping is: " + Arrays.toString(mapping));
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] sortJumbled = sortJumbled(mapping, nums); // Calls your implementation
		System.out.println("sortJumbled is: " + Arrays.toString(sortJumbled));
	}
}
