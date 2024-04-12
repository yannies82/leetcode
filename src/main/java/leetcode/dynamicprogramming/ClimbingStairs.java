package leetcode.dynamicprogramming;

public class ClimbingStairs {

	public static void main(String[] args) {
		check(2, 2);
		check(3, 3);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/climbing-stairs. This
	 * optimized version of the recursive solution uses top down dynamic programming
	 * to cache the results of intermediate calculations and avoid calculating many
	 * times for the same arguments. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int climbStairs(int n) {
		// early exit for n == 1 or n == 2
		if (n == 1 || n == 2) {
			return n;
		}
		// this array caches the intermediate results
		int[] dpArray = new int[n + 1];
		// set the known values for n = 1 and n = 2
		dpArray[1] = 1;
		dpArray[2] = 2;
		return dp(n, dpArray);
	}

	private static int dp(int target, int[] dpArray) {
		if (dpArray[target] == 0) {
			// return cached value, calculate and cache it if it is not calculated yet
			dpArray[target] = dp(target - 1, dpArray) + dp(target - 2, dpArray);
		}
		return dpArray[target];
	}

	/**
	 * Similar solution, however, this one uses bottom up dynamic programming. Time
	 * complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int climbStairs2(int n) {
		// early exit for n == 1 or n == 2
		if (n == 1 || n == 2) {
			return n;
		}
		// this array caches the intermediate results
		int[] dpArray = new int[n + 1];
		// set the known values for n = 1 and n = 2
		dpArray[1] = 1;
		dpArray[2] = 2;
		// calculate all dpArray values bottom up, up to n
		for (int i = 3; i <= n; i++) {
			dpArray[i] = dpArray[i - 1] + dpArray[i - 2];
		}
		return dpArray[n];
	}

	/**
	 * This simple solution uses recursion in order to traverse the stairs in all
	 * possible ways with steps of 1 or 2. It is slow because the climbStairs3
	 * function is invoked many times for the same arguments and performs the same
	 * calculations again and again. Time complexity is O(2^n).
	 * 
	 * @param n
	 * @return
	 */
	public static int climbStairs3(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		return climbStairs3(n - 1) + climbStairs3(n - 2);
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int climbStairs = climbStairs(n); // Calls your implementation
		System.out.println("climbStairs is: " + climbStairs);
	}

}
