package leetcode.greedy;

import java.util.Arrays;

public class MaximumNumberOfIntegersToChooseFromARangeI {

	public static void main(String[] args) {
		check(new int[] { 1, 6, 5 }, 5, 6, 2);
		check(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 8, 1, 0);
		check(new int[] { 11 }, 7, 50, 7);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-number-of-integers-to-choose-from-a-range-i.
	 * This solution marks the banned numbers then goes on to greedily add as much
	 * numbers from 1 to n as possible until maxSum is exceeded. Time complexity is
	 * O(m+n) where m is the length of the banned array.
	 * 
	 * @param banned
	 * @param n
	 * @param maxSum
	 * @return
	 */
	public static int maxCount(int[] banned, int n, int maxSum) {
		boolean[] existsInBanned = new boolean[10001];
		for (int i = 0; i < banned.length; i++) {
			existsInBanned[banned[i]] = true;
		}
		int sum = 0;
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (!existsInBanned[i]) {
				sum += i;
				if (sum > maxSum) {
					break;
				}
				count++;
			}
		}
		return count;
	}

	/**
	 * Similar solution but branchless. Time complexity is O(m+n) where m is the
	 * length of the banned array.
	 * 
	 * @param banned
	 * @param n
	 * @param maxSum
	 * @return
	 */
	public static int maxCount2(int[] banned, int n, int maxSum) {
		byte[] existsInBanned = new byte[10001];
		for (int i = 0; i < banned.length; i++) {
			existsInBanned[banned[i]] = 1;
		}
		int sum = 0;
		int count = 0;
		// terminate when sun exceeds maxSum or when i > n, use hacky side effect
		// in for loop condition to reduce count
		for (int i = 1; (sum <= maxSum || count-- < -1) && i <= n; i++) {
			// diff is always 0 or -1
			int diff = existsInBanned[i] - 1;
			sum += i & diff;
			count -= diff;
		}
		return count;
	}

	/**
	 * Similar solution but branchless, trades off execution time for space
	 * efficiency. Time complexity is O(m+n) where m is the length of the banned
	 * array.
	 * 
	 * @param banned
	 * @param n
	 * @param maxSum
	 * @return
	 */
	public static int maxCount3(int[] banned, int n, int maxSum) {
		int limit = n + 1;
		byte[] existsInBanned = new byte[limit];
		for (int i = 0; i < banned.length; i++) {
			// if banned[i] is greater than n then existsInBanned[0] = 1
			// otherwise existsInBanned[banned[i]] = 1
			existsInBanned[((banned[i] - limit) >>> 31) & banned[i]] = 1;
		}
		int sum = 0;
		int count = 0;
		// terminate when sun exceeds maxSum or when i > n, use hacky side effect
		// in for loop condition to reduce count
		for (int i = 1; (sum <= maxSum || count-- < -1) && i <= n; i++) {
			// diff is always 0 or -1
			int diff = existsInBanned[i] - 1;
			sum += i & diff;
			count -= diff;
		}
		return count;
	}

	private static void check(int[] banned, int n, int maxSum, int expected) {
		System.out.println("banned is: " + Arrays.toString(banned));
		System.out.println("n is: " + n);
		System.out.println("maxSum is: " + maxSum);
		System.out.println("expected is: " + expected);
		int maxCount = maxCount(banned, n, maxSum); // Calls your implementation
		System.out.println("maxCount is: " + maxCount);
	}
}
