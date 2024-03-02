package leetcode.dynamicprogramming;

public class ClimbingStairs {

	public static void main(String[] args) {
		check(2, 2);
		check(3, 3);
	}

	/**
	 * This optimized version of the recursive solution uses dynamic programming to
	 * cache the results of intermediate calculations and avoid calculating many
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
		int[] dpArray = new int[n];
		// set the known values for n = 1 and n = 2
		dpArray[1] = 1;
		dpArray[2] = 2;
		return dp(n - 1, dpArray) + dp(n - 2, dpArray);
	}

	private static int dp(int target, int[] dpArray) {
		if (dpArray[target] == 0) {
			// return cached value, calculate and cache it if it is not calculated yet
			dpArray[target] = dp(target - 1, dpArray) + dp(target - 2, dpArray);
		}
		return dpArray[target];
	}

	/**
	 * This simple solution uses recursion in order to traverse the stairs in all
	 * possible ways with steps of 1 or 2. It is slow because we the climbStairs2
	 * function is invoked many times for the same arguments and performs the same
	 * calculations again and again. Time complexity is O(2^n).
	 * 
	 * @param n
	 * @return
	 */
	public static int climbStairs2(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		return climbStairs2(n - 1) + climbStairs2(n - 2);
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int climbStairs = climbStairs(n); // Calls your implementation
		System.out.println("climbStairs is: " + climbStairs);
	}

}
