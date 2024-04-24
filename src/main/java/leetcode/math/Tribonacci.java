package leetcode.math;

public class Tribonacci {

	public static void main(String[] args) {
		check(2, 1);
		check(4, 4);
		check(7, 24);
		check(15, 3136);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/n-th-tribonacci-number.
	 * Iterative solution, space efficient. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int tribonacci(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		if (n == 2) {
			return 1;
		}
		int first = 0, second = 1, third = 1;
		for (int i = 3; i <= n; i++) {
			int next = first + second + third;
			first = second;
			second = third;
			third = next;
		}
		return third;
	}

	/**
	 * Recursive solution. Time complexity is O(3^n).
	 * 
	 * @param n
	 * @return
	 */
	public static int tribonacciRecursive(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		if (n == 2) {
			return 1;
		}
		return tribonacciRecursive(n - 1) + tribonacciRecursive(n - 2) + tribonacciRecursive(n - 3);
	}

	/**
	 * This solution uses bottom up dynamic programming. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int tribonacciBottomUpDp(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		if (n == 2) {
			return 1;
		}
		int[] dpArray = new int[n + 1];
		dpArray[1] = dpArray[2] = 1;
		for (int i = 3; i <= n; i++) {
			dpArray[i] = dpArray[i - 1] + dpArray[i - 2] + dpArray[i - 3];
		}
		return dpArray[n];
	}

	/**
	 * This solution uses top down dynamic programming. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int tribonacciTopDownDp(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		if (n == 2) {
			return 1;
		}
		int[] dpArray = new int[n + 1];
		dpArray[1] = dpArray[2] = 1;
		return dp(n, dpArray);
	}

	private static int dp(int i, int[] dpArray) {
		if (i == 0 || dpArray[i] != 0) {
			return dpArray[i];
		}
		return dp(i - 1, dpArray) + dp(i - 2, dpArray) + dp(i - 3, dpArray);
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int tribonacci = tribonacci(n); // Calls your implementation
		System.out.println("tribonacci is: " + tribonacci);
		int tribonacciRecursive = tribonacciRecursive(n); // Calls your implementation
		System.out.println("tribonacciRecursive is: " + tribonacciRecursive);
		int tribonacciBottomUpDp = tribonacciBottomUpDp(n); // Calls your implementation
		System.out.println("tribonacciBottomUpDp is: " + tribonacciBottomUpDp);
		int tribonacciTopDownDp = tribonacciTopDownDp(n); // Calls your implementation
		System.out.println("tribonacciTopDownDp is: " + tribonacciTopDownDp);
	}
}
