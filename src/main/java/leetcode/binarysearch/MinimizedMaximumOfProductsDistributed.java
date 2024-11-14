package leetcode.binarysearch;

import java.util.Arrays;

public class MinimizedMaximumOfProductsDistributed {

	public static void main(String[] args) {
		check(7, new int[] { 15, 10, 10 }, 5);
		check(6, new int[] { 11, 6 }, 3);
		check(1, new int[] { 100000 }, 100000);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store.
	 * This solution finds the max quantity and performs binary search to find the
	 * minimum max store value for which the quantities are distributable. Time
	 * complexity is O(mlogx) where m is the length of the quantities array and x is
	 * the max quantity.
	 * 
	 * 
	 * @param n
	 * @param quantities
	 * @return
	 */
	public static int minimizedMaximum(int n, int[] quantities) {
		// calculate the maxQuantity which will be the upper limit for the result
		int maxQuantity = quantities[0];
		for (int i = 1; i < quantities.length; i++) {
			maxQuantity = Math.max(maxQuantity, quantities[i]);
		}
		// perform binary search to find the lowest value for which the quantities are
		// distributable to n stores
		int low = 1;
		int high = maxQuantity + 1;
		int result = 0;
		while (low < high) {
			int mid = (low + high) / 2;
			if (canDistribute(n, quantities, mid)) {
				// the value mid is distributable, set it as the result and search lower values
				result = mid;
				high = mid;
			} else {
				// the value mid is not distributable, search higher values
				low = mid + 1;
			}
		}
		return result;
	}

	private static boolean canDistribute(int n, int[] quantities, int k) {
		int distributed = 0;
		int offset = k - 1;
		for (int i = 0; i < quantities.length && distributed <= n; i++) {
			distributed += (quantities[i] + offset) / k;
		}
		return distributed <= n;
	}

	private static void check(int n, int[] quantities, int expected) {
		System.out.println("n is: " + n);
		System.out.println("quantities is: " + Arrays.toString(quantities));
		System.out.println("expected is: " + expected);
		int minSteps = minimizedMaximum(n, quantities); // Calls your implementation
		System.out.println("minSteps is: " + minSteps);
	}
}
