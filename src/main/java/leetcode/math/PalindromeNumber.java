package leetcode.math;

public class PalindromeNumber {

	public static void main(String[] args) {
		check(121, true);
		check(2254, false);
		check(12344321, true);
	}

	/**
	 * Finds the solution by constructing the sum of the digits of x with reversed
	 * significance. Time complexity is O(X) where X is the number of digits of x.
	 * 
	 * @param x
	 * @return
	 */
	public static boolean isPalindrome(int x) {
		int sum = 0;
		int current = x;
		while (current > 0) {
			sum *= 10;
			sum += current % 10;
			current /= 10;
		}
		// x is palindrome only if it is equal to sum
		return x == sum;
	}

	private static void check(int x, boolean expected) {
		System.out.println("x is: " + x);
		System.out.println("expected is: " + expected);
		boolean isPalindrome = isPalindrome(x); // Calls your implementation
		System.out.println("isPalindrome is: " + isPalindrome);
	}

}
