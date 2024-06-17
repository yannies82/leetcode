package leetcode.math;

public class SumOfSquareNumbers {

	public static void main(String[] args) {
		check(2147483600, true);
		check(2, true);
		check(4, true);
		check(5, true);
		check(3, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/sum-of-square-numbers. This
	 * solution leverages Fermat's theorem for
	 * 
	 * @param c
	 * @return
	 */
	public static boolean judgeSquareSum(int c) {
		int limit = (int) Math.sqrt(c);
		// iterate to find factors of c
		for (int i = 2; i <= limit; i++) {
			if (c % i == 0) {
				// i is a factor of c
				int exponentCount = 0;
				while (c % i == 0) {
					exponentCount++;
					c /= i;
				}
				if (i % 4 == 3 && exponentCount % 2 != 0) {
					// by fermat theorem if i is a factor of c an odd number of times
					// and i % 4 == 3 then c cannot be expressed as a^2 + b^2
					return false;
				}
			}
		}
		return c % 4 != 3;
	}

	private static void check(int c, boolean expected) {
		System.out.println("c is: " + c);
		System.out.println("expected is: " + expected);
		boolean judgeSquareSum = judgeSquareSum(c); // Calls your implementation
		System.out.println("judgeSquareSum is: " + judgeSquareSum);
	}
}
