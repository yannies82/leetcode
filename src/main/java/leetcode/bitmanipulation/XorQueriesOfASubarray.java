package leetcode.bitmanipulation;

import java.util.Arrays;

public class XorQueriesOfASubarray {

	public static void main(String[] args) {
		check(new int[] { 1, 3, 4, 8 }, new int[][] { { 0, 1 }, { 1, 2 }, { 0, 3 }, { 3, 3 } },
				new int[] { 2, 7, 14, 8 });
		check(new int[] { 4, 8, 2, 10 }, new int[][] { { 2, 3 }, { 1, 3 }, { 0, 0 }, { 0, 3 } },
				new int[] { 8, 0, 4, 4 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/xor-queries-of-a-subarray.
	 * This solution calculates the prefixXor values in a separate array. Time
	 * complexity is O(m+n) where m is the length of arr and n is the length of the
	 * queries array.
	 * 
	 * @param arr
	 * @param queries
	 * @return
	 */
	public static int[] xorQueries(int[] arr, int[][] queries) {
		int m = arr.length;
		// calculate prefixXor array values
		// each element with index i in prefixXor has the xor value
		// of all arr elements from 0 to i - 1
		int[] prefixXor = new int[m + 1];
		for (int i = 1; i <= m; i++) {
			prefixXor[i] = prefixXor[i - 1] ^ arr[i - 1];
		}
		int n = queries.length;
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			int[] query = queries[i];
			// use prefixXor values to calculate the result
			// c^d = a^b^c^d^a^b
			result[i] = prefixXor[query[1] + 1] ^ prefixXor[query[0]];
		}
		return result;
	}

	/**
	 * Simple, straightforward solution with time complexity of O(m*n) where m is
	 * the length of arr and n is the length of the queries array.
	 * 
	 * @param arr
	 * @param queries
	 * @return
	 */
	public static int[] xorQueries2(int[] arr, int[][] queries) {
		int n = queries.length;
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			int[] query = queries[i];
			for (int j = query[0]; j <= query[1]; j++) {
				result[i] ^= arr[j];
			}
		}
		return result;
	}

	private static void check(int[] arr, int[][] queries, int[] expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("queries is: ");
		for (int[] query : queries) {
			System.out.println(Arrays.toString(query));
		}
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] xorQueries = xorQueries(arr, queries); // Calls your implementation
		System.out.println("xorQueries is: " + Arrays.toString(xorQueries));
	}

}
