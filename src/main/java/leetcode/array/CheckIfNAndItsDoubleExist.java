package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CheckIfNAndItsDoubleExist {

	public static void main(String[] args) {
		check(new int[] { 10, 2, 5, 3 }, true);
		check(new int[] { 3, 1, 7, 11 }, false);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/check-if-n-and-its-double-exist. Time optimized
	 * solution which uses a range array to keep the numbers. Time complexity is
	 * O(n) and space complexity is O(k), where n is the length of the arr array and
	 * k is the range of the numbers in the array.
	 * 
	 * 
	 * @param arr
	 * @return
	 */
	public static boolean checkIfExist(int[] arr) {
		boolean[] range = new boolean[4001];
		int offset = 2000;
		for (int i = 0; i < arr.length; i++) {
			int offsetedNumber = arr[i] + offset;
			int doubleOffsetedNumber = (arr[i] << 1) + offset;
			if (range[doubleOffsetedNumber] || (((arr[i] & 1) == 0)) && range[(arr[i] >> 1) + offset]) {
				return true;
			}
			range[offsetedNumber] = true;
		}
		return false;
	}

	/**
	 * Time efficient solution which uses a set to store the numbers. Time
	 * complexity is O(n) and space complexity is O(n), where n is the length of the
	 * arr array.
	 * 
	 * @param arr
	 * @return
	 */
	public static boolean checkIfExist2(int[] arr) {
		Set<Integer> existing = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			if (existing.contains(arr[i] << 1) || (((arr[i] & 1) == 0)) && existing.contains(arr[i] >> 1)) {
				return true;
			}
			existing.add(arr[i]);
		}
		return false;
	}

	/**
	 * Space efficient solution which checks all pairs. Time complexity is O(n^2)
	 * and space complexity is O(1), where n is the length of the arr array.
	 * 
	 * @param arr
	 * @return
	 */
	public static boolean checkIfExist3(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int doubleValue = arr[i] << 1;
			for (int j = i + 1; j < arr.length; j++) {
				if (doubleValue == arr[j] || arr[i] == arr[j] << 1) {
					return true;
				}
			}
		}
		return false;
	}

	private static void check(int[] arr, boolean expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("expected is: " + expected);
		boolean checkIfExist = checkIfExist(arr); // Calls your implementation
		System.out.println("checkIfExist is: " + checkIfExist);
	}
}
