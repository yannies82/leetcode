package leetcode.array.frequency;

import java.util.Arrays;

public class RelativeSortArray {

	public static void main(String[] args) {
		check(new int[] { 2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19 }, new int[] { 2, 1, 4, 3, 9, 6 },
				new int[] { 2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19 });
		check(new int[] { 28, 6, 22, 8, 44, 17 }, new int[] { 22, 28, 8, 6 }, new int[] { 22, 28, 8, 6, 17, 44 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/relative-sort-array. This
	 * solution counts the frequency of elements in arr1 and keeps track of whether
	 * they exist in arr2 or not, then updates arr1 accordingly. Time complexity is
	 * O(n) where n is the length of the arr1 array.
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static int[] relativeSortArray(int[] arr1, int[] arr2) {
		// count frequency of elements in arr1
		int[] arr1Frequency = new int[1001];
		for (int i = 0; i < arr1.length; i++) {
			arr1Frequency[arr1[i]]++;
		}
		// keep track of existing elements in arr2
		boolean[] arr2Existing = new boolean[1001];
		for (int i = 0; i < arr2.length; i++) {
			arr2Existing[arr2[i]] = true;
		}
		// iterate arr2 and add its elements to arr1
		// as many times as their counted frequency
		int index = 0;
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr1Frequency[arr2[i]]; j++) {
				arr1[index++] = arr2[i];
			}
		}
		// iterate the full range of numbers and add to arr1
		// as many times as their counted frequency
		// the elements that do not exist in arr2
		for (int i = 0; i < 1001 && index < arr1.length; i++) {
			if (!arr2Existing[i]) {
				for (int j = 0; j < arr1Frequency[i]; j++) {
					arr1[index++] = i;
				}
			}
		}
		return arr1;
	}

	private static void check(int[] arr1, int[] arr2, int[] expected) {
		System.out.println("arr1 is: " + Arrays.toString(arr1));
		System.out.println("arr2 is: " + Arrays.toString(arr2));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] relativeSortArray = relativeSortArray(arr1, arr2); // Calls your implementation
		System.out.println("relativeSortArray is: " + Arrays.toString(relativeSortArray));
	}
}
