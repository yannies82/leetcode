package leetcode.multidimensionaldynamicprogramming;

import java.util.Arrays;

public class LengthOfLongestFibonacciSubsequence {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 5);
		check(new int[] { 1, 3, 7, 11, 12, 14, 18 }, 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/length-of-longest-fibonacci-subsequence. This
	 * solution uses dynamic programming and two pointers approach. Time complexity
	 * is O(n^2) where n is the length of the arr array.
	 * 
	 * @param arr
	 * @return
	 */
	public static int lenLongestFibSubseq2(int[] arr) {
		int n = arr.length;
		int result = 0;
		// this array keeps the intermediate solutions
		int[][] dpArray = new int[n][n];

		for (int i = 2; i < n; i++) {
			// use two pointers to find if there are two numbers less than arr[i] with sum
			// equaling arr[i]
			int left = 0;
			int right = i - 1;
			while (left < right) {
				int sum = arr[left] + arr[right];
				if (sum > arr[i]) {
					right--;
				} else if (sum < arr[i]) {
					left++;
				} else {
					dpArray[right][i] = dpArray[left][right] + 1;
					result = Math.max(result, dpArray[right][i]);
					left++;
					right--;
				}
			}
		}
		return result == 0 ? 0 : result + 2;
	}

	/**
	 * Simple solution. Time complexity is O(n^3) where n is the length of the arr
	 * array.
	 * 
	 * @param arr
	 * @return
	 */
	public static int lenLongestFibSubseq(int[] arr) {
		int maxMatches = 0;
		for (int i = 0; i < arr.length - 2; i++) {
			for (int j = i + 1; j < arr.length - 1; j++) {
				int matches = 0;
				int target = arr[i] + arr[j];
				int prev = arr[j];
				for (int k = j + 1; k < arr.length; k++) {
					if (arr[k] == target) {
						matches++;
						target = prev + arr[k];
						prev = arr[k];
					} else if (arr[k] > target) {
						break;
					}
				}
				maxMatches = Math.max(matches, maxMatches);
			}
		}
		return maxMatches == 0 ? 0 : maxMatches + 2;
	}

	private static void check(int[] arr, int expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("expected is: " + expected);
		int lenLongestFibSubseq = lenLongestFibSubseq(arr); // Calls your implementation
		System.out.println("lenLongestFibSubseq is: " + lenLongestFibSubseq);
	}
}
