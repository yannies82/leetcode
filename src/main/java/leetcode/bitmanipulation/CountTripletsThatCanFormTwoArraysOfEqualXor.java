package leetcode.bitmanipulation;

import java.util.Arrays;

public class CountTripletsThatCanFormTwoArraysOfEqualXor {

	public static void main(String[] args) {
		check(new int[] { 2, 3, 1, 6, 7 }, 4);
		check(new int[] { 1, 1, 1, 1, 1 }, 10);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor.
	 * The problem can be simplified if we consider that if a == b then a^b == 0.
	 * This means that it is sufficient for us to find every subarray of arr for
	 * which the xor of its elements equals 0. For each such subarray we should add
	 * subarray length - 1 to the result. Time complexity is O(n^2) where n is the
	 * length of the arr array.
	 * 
	 * @param arr
	 * @return
	 */
	public static int countTriplets(int[] arr) {
		int lastIndex = arr.length - 1;
		int result = 0;
		for (int i = 0; i < lastIndex; i++) {
			int total = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				total ^= arr[j];
				if (total == 0) {
					result += j - i;
				}
			}
		}
		return result;
	}

	private static void check(int[] arr, int expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("expected is: " + expected);
		int countTriplets = countTriplets(arr); // Calls your implementation
		System.out.println("countTriplets is: " + countTriplets);
	}
}
