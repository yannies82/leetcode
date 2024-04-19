package leetcode.math;

public class Fibonacci {

	public static void main(String[] args) {
		check(2, 1);
		check(7, 13);
		check(15, 610);
	}

	/**
	 * Iterative solution, space efficient. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int calculateFibonacci(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		int first = 0, second = 1;
		for (int i = 2; i <= n; i++) {
			int sum = first + second;
			first = second;
			second = sum;
		}
		return second;
	}

	/**
	 * Recursive solution. Time complexity is O(2^n).
	 * 
	 * @param n
	 * @return
	 */
	public static int calculateFibonacciRecursive(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		return calculateFibonacciRecursive(n - 1) + calculateFibonacciRecursive(n - 2);
	}

	/**
	 * This solution uses bottom up dynamic programming. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int calculateFibonacciBottomUpDp(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		int[] dpArray = new int[n + 1];
		dpArray[1] = 1;
		for (int i = 2; i <= n; i++) {
			dpArray[i] = dpArray[i - 1] + dpArray[i - 2];
		}
		return dpArray[n];
	}

	/**
	 * This solution uses top down dynamic programming. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int calculateFibonacciTopDownDp(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		int[] dpArray = new int[n + 1];
		dpArray[1] = 1;
		return dp(n, dpArray);
	}

	private static int dp(int i, int[] dpArray) {
		if (i == 0 || dpArray[i] != 0) {
			return dpArray[i];
		}
		return dp(i - 1, dpArray) + dp(i - 2, dpArray);
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int calculateFibonacci = calculateFibonacci(n); // Calls your implementation
		System.out.println("calculateFibonacci is: " + calculateFibonacci);
		int calculateFibonacciRecursive = calculateFibonacciRecursive(n); // Calls your implementation
		System.out.println("calculateFibonacciRecursive is: " + calculateFibonacciRecursive);
		int calculateFibonacciBottomUpDp = calculateFibonacciBottomUpDp(n); // Calls your implementation
		System.out.println("calculateFibonacciBottomUpDp is: " + calculateFibonacciBottomUpDp);
		int calculateFibonacciTopDownDp = calculateFibonacciTopDownDp(n); // Calls your implementation
		System.out.println("calculateFibonacciTopDownDp is: " + calculateFibonacciTopDownDp);
	}
}
