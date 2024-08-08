package leetcode.arraystring;

import java.util.Arrays;

public class MakeTwoArraysEqualByReversingSubarrays {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4 }, new int[] { 2, 4, 1, 3 }, true);
		check(new int[] { 7 }, new int[] { 7 }, true);
		check(new int[] { 3, 7, 9 }, new int[] { 3, 7, 11 }, false);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/make-two-arrays-equal-by-reversing-subarrays.
	 * This solution is built upon the fact that the two arrays can only become
	 * equal if they contain exactly the same elements, maybe in different order.
	 * Time complexity is O(n) where n is the length of the target array.
	 * 
	 * @param target
	 * @param arr
	 * @return
	 */
	public static boolean canBeEqual(int[] target, int[] arr) {
		int[] result = new int[1001];
		for (int i = 0; i < target.length; i++) {
			result[target[i]]++;
			result[arr[i]]--;
		}

		for (int i = 0; i < 1001; i++) {
			if (result[i] != 0) {
				// this means that number i appears a different number of times
				// in the two arrays
				return false;
			}
		}
		return true;
	}

	private static void check(int[] target, int[] arr, boolean expected) {
		System.out.println("target is: " + Arrays.toString(target));
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("expected is: " + expected);
		boolean canBeEqual = canBeEqual(target, arr); // Calls your implementation
		System.out.println("canBeEqual is: " + canBeEqual);
	}
}
