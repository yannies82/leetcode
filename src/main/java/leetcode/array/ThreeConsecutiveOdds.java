package leetcode.array;

import java.util.Arrays;

public class ThreeConsecutiveOdds {

	public static void main(String[] args) {
		check(new int[] { 2, 6, 4, 1 }, false);
		check(new int[] { 1, 2, 34, 3, 4, 5, 7, 23, 12 }, true);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/three-consecutive-odds. Time
	 * complexity is O(n) where n is the length of the arr array.
	 * 
	 * @param arr
	 * @return
	 */
	public static boolean threeConsecutiveOdds(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length && count < 3; i++) {
			int lastDigit = arr[i] & 1;
			count = ((-lastDigit) & count) + lastDigit;
		}
		return count == 3;
	}

	public static boolean threeConsecutiveOdds2(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 1) {
				if (count == 2) {
					return true;
				}
				count++;
			} else {
				count = 0;
			}
		}
		return false;
	}

	private static void check(int[] arr, boolean expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("expectedCandies is: " + expected);
		boolean threeConsecutiveOdds = threeConsecutiveOdds(arr); // Calls your implementation
		System.out.println("threeConsecutiveOdds is: " + threeConsecutiveOdds);
	}
}
