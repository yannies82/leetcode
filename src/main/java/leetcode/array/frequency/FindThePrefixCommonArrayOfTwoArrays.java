package leetcode.array.frequency;

import java.util.Arrays;

public class FindThePrefixCommonArrayOfTwoArrays {

	public static void main(String[] args) {
		check(new int[] { 1, 3, 2, 4 }, new int[] { 3, 1, 2, 4 }, new int[] { 0, 2, 3, 4 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays.
	 * Time complexity is O(n) where n is the length of arrays A,B.
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static int[] findThePrefixCommonArray(int[] A, int[] B) {
		int[] frequency = new int[A.length + 1];
		int[] result = new int[A.length];
		int current = 0;
		for (int i = 0; i < A.length; i++) {
			current += (++frequency[A[i]] & 2) >> 1;
			current += (++frequency[B[i]] & 2) >> 1;
			result[i] = current;
		}
		return result;
	}

	private static void check(int[] A, int[] B, int[] expected) {
		System.out.println("A is: " + Arrays.toString(A));
		System.out.println("B is: " + Arrays.toString(B));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] findThePrefixCommonArray = findThePrefixCommonArray(A, B); // Calls your implementation
		System.out.println("findThePrefixCommonArray is: " + Arrays.toString(findThePrefixCommonArray));
	}
}
