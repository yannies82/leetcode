package leetcode.array;

import java.util.Arrays;

public class SortColors {

	public static void main(String[] args) {
		check(new int[] { 2, 0, 2, 1, 1, 0 }, new int[] { 0, 0, 1, 1, 2, 2 });
		check(new int[] { 2, 0, 1 }, new int[] { 0, 1, 2 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/sort-colors. This solution
	 * counts the frequency of each color, then overwrites the input array. Time
	 * complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 */
	public static void sortColors(int[] nums) {
		int[] frequency = new int[3];
		for (int i = 0; i < nums.length; i++) {
			frequency[nums[i]]++;
		}
		int index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < frequency[i]; j++) {
				nums[index++] = i;
			}
		}
	}

	/**
	 * One pass solution. Time complexity is O(n) where n is the length of the nums
	 * array.
	 * 
	 * @param nums
	 */
	public static void sortColors2(int[] nums) {
		int start = 0;
		int end = nums.length - 1;
		int i = 0;
		while (i < nums.length) {
			if (nums[i] == 0 && i > start) {
				// swap 0s to the start of the array
				int temp = nums[i];
				nums[i] = nums[start];
				nums[start] = temp;
				start++;
			} else if (nums[i] == 2 && i < end) {
				// swap 2s to the end of the array
				int temp = nums[i];
				nums[i] = nums[end];
				nums[end] = temp;
				end--;
			} else {
				// ignore 1s and proceed to next number
				i++;
			}
		}
	}

	private static void check(int[] nums, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + Arrays.toString(expected));
		sortColors(nums); // Calls your implementation
		System.out.println("sortColors is: " + Arrays.toString(nums));
	}
}
