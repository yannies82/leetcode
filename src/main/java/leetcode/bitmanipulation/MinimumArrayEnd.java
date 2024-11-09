package leetcode.bitmanipulation;

public class MinimumArrayEnd {

	public static void main(String[] args) {
		check(3, 4, 6);
		check(2, 7, 15);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/minimum-array-end. This
	 * solution searches for non the non set bits of x (bits with value 0) and sets
	 * the number n-1 to them to achieve the desired result. Time complexity is
	 * O(1).
	 * 
	 * @param n
	 * @param x
	 * @return
	 */
	public static long minEnd(int n, int x) {
		// the array will start at x and the index of the last element will be n-1
		int target = n - 1;
		long result = x;
		int factor = 0;
		while (target > 0) {
			// in order for the AND between array numbers to be x, all numbers in the
			// array should contain the set bits of x (bits with value 1)
			// we should search for the non set bits of x and set the number n-1 to them
			// from right to left
			while (((result >>> factor) & 1) == 1) {
				// skip set bits of x
				factor++;
			}
			// set the bit of target to the appropriate factor of x
			result += (long) (target & 1) << factor;
			// get the next bit of target
			target >>>= 1;
			// increase bit position to search in x
			factor++;
		}
		return result;
	}

	private static void check(int n, int x, int expected) {
		System.out.println("n is: " + n);
		System.out.println("x is: " + x);
		System.out.println("expected is: " + expected);
		long minEnd = minEnd(n, x); // Calls your implementation
		System.out.println("minEnd is: " + minEnd);
	}
}
