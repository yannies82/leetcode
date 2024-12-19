package leetcode.array;

import java.util.Arrays;

public class MaxChunksToMakeSorted {

	public static void main(String[] args) {
		check(new int[] { 4, 3, 2, 1, 0 }, 1);
		check(new int[] { 1, 0, 2, 3, 4 }, 4);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/max-chunks-to-make-sorted.
	 * This solution checks if the max up to index i is equal to index i. If this is
	 * the case it means that all numbers up to i are contained in this chunk and it
	 * can be sorted independently. Time complexity is O(n) where n is the length of
	 * arr.
	 * 
	 * @param arr
	 * @return
	 */
	public static int maxChunksToSorted(int[] arr) {
		int chunks = 0;
		int currentMax = 0;
		for (int i = 0; i < arr.length; i++) {
			currentMax = Math.max(currentMax, arr[i]);
			if (currentMax == i) {
				chunks++;
			}
		}
		return chunks;
	}

	private static void check(int[] arr, int expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("expected is: " + expected);
		int maxChunksToSorted = maxChunksToSorted(arr); // Calls your implementation
		System.out.println("maxChunksToSorted is: " + maxChunksToSorted);
	}
}
