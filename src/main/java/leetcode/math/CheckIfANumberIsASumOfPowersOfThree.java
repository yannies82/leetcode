package leetcode.math;

public class CheckIfANumberIsASumOfPowersOfThree {

	public static void main(String[] args) {
		check(12, true);
		check(91, true);
		check(21, false);
	}

	private static final int[] POWERS_OF_3 = { 1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441,
			1594323, 4782969 };

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three.
	 * This solution precalculates all required powers of three, then uses them to
	 * construct the number n with their sum. Time complexity is O(1).
	 * 
	 * @param n
	 * @return
	 */
	public static boolean checkPowersOfThree(int n) {
		int sum = n;
		int lastIndex = POWERS_OF_3.length - 1;
		for (int i = lastIndex; i >= 0 && sum > 0; i--) {
			if (POWERS_OF_3[i] <= sum) {
				sum -= POWERS_OF_3[i];
			}
		}
		return sum == 0;
	}

	/**
	 * Alternative solution
	 * 
	 * @param n
	 * @return
	 */
	public static boolean checkPowersOfThree2(int n) {
		if (n == 1) {
			return true;
		}

		if (n % 3 == 0) {
			return checkPowersOfThree(n / 3);
		}

		if ((n - 1) % 3 == 0) {
			return checkPowersOfThree((n - 1) / 3);
		}

		return false;
	}

	private static void check(int n, boolean expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		boolean checkPowersOfThree = checkPowersOfThree(n); // Calls your implementation
		System.out.println("checkPowersOfThree is: " + checkPowersOfThree);
	}

}
