package leetcode.math;

public class Factorial {

	public static void main(String[] args) {
		check(2, 2);
		check(7, 5040);
		check(15, 2004310016);
	}

	/**
	 * Iterative solution, time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int calculateFactorial(int n) {
		int mult = 1;
		for (int i = 2; i <= n; i++) {
			mult *= i;
		}
		return mult;
	}

	/**
	 * Recursive solution, time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int calculateFactorialRecursive(int n) {
		if (n == 0) {
			return 1;
		}
		return n * calculateFactorialRecursive(n - 1);
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int calculateFactorial = calculateFactorial(n); // Calls your implementation
		System.out.println("calculateFactorial is: " + calculateFactorial);
		int calculateFactorialRecursive = calculateFactorialRecursive(n); // Calls your implementation
		System.out.println("calculateFactorialRecursive is: " + calculateFactorialRecursive);
	}
}
