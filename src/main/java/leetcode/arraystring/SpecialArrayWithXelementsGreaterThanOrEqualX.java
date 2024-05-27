package leetcode.arraystring;

import java.util.Arrays;

public class SpecialArrayWithXelementsGreaterThanOrEqualX {

	public static void main(String[] args) {
		check(new int[] { 3, 5 }, 2);
		check(new int[] { 0, 0 }, -1);
		check(new int[] { 0, 4, 3, 0, 4 }, 3);

	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x.
	 * This solutions keeps the frequency of all numbers in an array, then traverses
	 * the numbers from nums.length to 0 in order to find the one which is less than
	 * or equal to exactly x elements. Time complexity is O(n) where n is the length
	 * of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int specialArray(int[] nums) {
		// generate and populate the frequency array
		int[] frequency = new int[1001];
		for (int i = 0; i < nums.length; i++) {
			// if nums[i] is greater than nums.length, aggregate the value to nums.length
			frequency[Math.min(nums[i], nums.length)]++;
		}

		// iterate the frequency array and accumulate the frequencies in order to
		// keep the count of all numbers greater than or equal to i
		int countSum = 0;
		for (int i = nums.length; i >= 0; i--) {
			countSum += frequency[i];
			if (i == countSum) {
				// this is the result since the count of numbers >= i is i
				return i;
			}
		}
		return -1;
	}

	/**
	 * This solution sorts the input array, then tries to find an element greater
	 * than or equal to the count of elements greater than itself, with a previous
	 * element less than the count of elements greater than itself. Time complexity
	 * is O(nlogn) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int specialArray2(int[] nums) {
		Arrays.sort(nums);
		int prev = -1;
		for (int i = 0; i < nums.length; i++) {
			int left = nums.length - i;
			if (prev < left && nums[i] >= left) {
				return left;
			}
			prev = nums[i];
		}
		return -1;
	}

	/**
	 * This solution tries all numbers from 0 to nums.length in order to find the
	 * result. Worst case time complexity is O(n^2), although time complexity is
	 * less in the common case.
	 * 
	 * @param nums
	 * @return
	 */
	public static int specialArray3(int[] nums) {
		for (int i = 0; i <= nums.length; i++) {
			int count = 0;
			for (int j = 0; j < nums.length; j++) {
				if (nums[j] >= i) {
					if (++count > i) {
						break;
					}
				}
			}
			if (count == i) {
				return i;
			}
		}
		return -1;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("ratings is: " + Arrays.toString(nums));
		System.out.println("expectedCandies is: " + expected);
		int specialArray = specialArray(nums); // Calls your implementation
		System.out.println("specialArray is: " + specialArray);
	}
}
