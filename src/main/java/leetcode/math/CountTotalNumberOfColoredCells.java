package leetcode.math;

public class CountTotalNumberOfColoredCells {

	public static void main(String[] args) {
		check(1, 1);
		check(2, 5);
		check(3, 13);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-total-number-of-colored-cells. This
	 * solution uses mathematical induction to find the formula which calculates the
	 * result. Time complexity is O(1).
	 * 
	 * @param n
	 * @return
	 */
	public static long coloredCells(int n) {
		return ((long) n * (n - 1) << 1) + 1;
	}

	/**
	 * This solution calculates all intermediate results using the equation f(n) =
	 * f(n-1) + 4*(n-1). Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static long coloredCells2(int n) {
		long current = 1;
		for (int i = 1; i <= n; i++) {
			current += (i - 1) << 2;
		}
		return current;
	}

	private static void check(int n, long expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		long coloredCells = coloredCells(n); // Calls your implementation
		System.out.println("coloredCells is: " + coloredCells);
	}
}
