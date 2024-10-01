package leetcode.arraystring;

import java.util.Arrays;

public class CheckIfArrayPairsAreDivisibleByK {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4, 5, 10, 6, 7, 8, 9 }, 5, true);
		check(new int[] { 1, 2, 3, 4, 5, 6 }, 7, true);
		check(new int[] { 1, 2, 3, 4, 5, 6 }, 10, false);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k. This
	 * solution calculates the frequency of the absolute modulo by k of each number.
	 * Time complexity is O(n+k) where n is the length of the array.
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public static boolean canArrange(int[] arr, int k) {
		// calculate the frequency of every modulo k value
		int[] frequency = new int[k];
		for (int i = 0; i < arr.length; i++) {
			// calculate the modulo as the index, compensate for the fact that
			// arr[i] can be less than 0
			int index = ((arr[i] % k) + k) % k;
			frequency[index]++;
		}
		if (frequency[0] % 2 != 0) {
			return false;
		}
		int limit = k / 2;
		for (int i = 1; i <= limit; i++) {
			if (frequency[i] != frequency[k - i]) {
				return false;
			}
		}
		return true;
	}

	private static void check(int[] arr, int k, boolean expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		boolean canArrange = canArrange(arr, k); // Calls your implementation
		System.out.println("canArrange is: " + canArrange);
	}
}
