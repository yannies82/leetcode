package leetcode.math;

public class FactorialTrailingZeroes {

	public static void main(String[] args) {
		check(13, 2);
		check(3, 0);
		check(5, 1);
		check(10, 2);
		check(625, 156);
	}

	/**
	 * Optimized solution which takes into consideration that trailing zeroes are
	 * added every time that we multiply with a factor of 5. Time complexity is
	 * O(logn) with a base of 5.
	 * 
	 * @param n
	 * @return
	 */
	public static int trailingZeroes(int n) {
		int factor = 1;
		int count = 0;
		do {
			factor *= 5;
			count += n / factor;
		} while (factor < n);
		return count;
	}

	/**
	 * Optimized solution which takes into consideration that trailing zeroes are
	 * added every time that we multiply with a factor of 5. Takes into account that
	 * n <= 10^4.
	 * 
	 * @param n
	 * @return
	 */
	public static int trailingZeroes2(int n) {
		return n / 5 + n / 25 + n / 125 + n / 625 + n / 3125;
	}

	/**
	 * Simple solution which calculates only the last digits of the factorial in
	 * order to avoid overflow and counts the zeroes.
	 * 
	 * @param n
	 * @return
	 */
	public static int trailingZeroes3(int n) {
		int count = 0;
		int mult = 1;
		for (int i = 2; i <= n; i++) {
			mult *= i;
			int res = mult;
			while (res % 10 == 0) {
				res /= 10;
				count++;
			}
			mult = res % 100000;
		}
		return count;
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int trailingZeroes = trailingZeroes(n); // Calls your implementation
		System.out.println("trailingZeroes is: " + trailingZeroes);
	}

}
