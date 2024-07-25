package leetcode.arraystring;

import java.util.Arrays;

public class SortAnArray {

	public static void main(String[] args) {
		check(new int[] { 5, 2, 3, 1 }, new int[] { 1, 2, 3, 5 });
		check(new int[] { 5, 1, 1, 2, 0, 0 }, new int[] { 0, 0, 1, 1, 2, 5 });
	}

	/**
	 * Leetcod problem: https://leetcode.com/problems/sort-an-array. This solution
	 * calculates the range of numbers and their frequency to build the output
	 * array. Time complexity is O(n) where n is the length of the nums array. Space
	 * requirements are O(max - min) where max is the max number and min is the min
	 * number in the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] sortArray(int[] nums) {
		// find the minimum and maximum number
		int min = nums[0];
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > max) {
				max = nums[i];
			}
			if (nums[i] < min) {
				min = nums[i];
			}
		}
		// calculate the range of numbers, create and populate intermediate frequency
		// array
		int range = max - min + 1;
		int[] count = new int[range];
		for (int i = 0; i < nums.length; i++) {
			count[nums[i] - min]++;
		}
		// populate the nums array from the intermediate frequency array
		int index = 0;
		for (int i = 0; i < count.length; i++) {
			while (count[i] > 0) {
				nums[index++] = i + min;
				count[i]--;
			}
		}
		return nums;
	}

	/**
	 * This solution uses radix sort. Time complexity is O(n) where n is the length
	 * of the nums array. Space requirements are O(n).
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] sortArray2(int[] nums) {
		int n = nums.length;
		// add value of minimum negative number to all numbers to make sure that no
		// number is negative
		for (int i = 0; i < n; i++) {
			nums[i] += 50000;
		}
		// find the maximum number to know number of digits
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > max) {
				max = nums[i];
			}
		}
		// Do counting sort for every digit. Note that
		// instead of passing digit number, exp is passed.
		// exp is 10^i where i is current digit number
		// the sort is stable between passes
		for (int exp = 1; max >= exp; exp *= 10) {
			countSort(nums, exp);
		}
		// restore all numbers to their initial values
		for (int i = 0; i < n; i++) {
			nums[i] -= 50000;
		}
		return nums;
	}

	// A function to do counting sort of arr[] according to
	// the digit represented by exp.
	static void countSort(int nums[], int exp) {
		int n = nums.length;
		int output[] = new int[n]; // output array
		int count[] = new int[10];

		// Store count of occurrences in count[]
		for (int i = 0; i < n; i++) {
			count[(nums[i] / exp) % 10]++;
		}

		// Change count[i] so that count[i] now contains
		// actual position of this digit in output[]
		for (int i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}

		// Build the output array, iterate reverse to fill higher positions first
		// and keep the sort stable
		for (int i = n - 1; i >= 0; i--) {
			output[--count[(nums[i] / exp) % 10]] = nums[i];
		}

		// Copy the output array to nums[], so that nums[] now
		// contains sorted numbers according to current
		// digit
		for (int i = 0; i < n; i++) {
			nums[i] = output[i];
		}
	}

	private static void check(int[] nums, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] sortArray = sortArray(nums); // Calls your implementation
		System.out.println("sortArray is: " + Arrays.toString(sortArray));
	}
}
