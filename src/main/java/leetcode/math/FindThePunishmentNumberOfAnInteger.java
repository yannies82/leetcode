package leetcode.math;

public class FindThePunishmentNumberOfAnInteger {

	public static void main(String[] args) {
		check(10, 182);
		check(37, 1478);
	}

	private static int[] RESULTS = new int[1001];
	private static int[] DIGITS = new int[10];

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-punishment-number-of-an-integer. This
	 * solution uses an array to cache results of previous executions and reuse
	 * them. For each calculation it performs backtracking to calculate all sum
	 * combinations of the squared number's digits and compare them to the number.
	 * Time complexity is amortized O(1), since results are being cached.
	 * 
	 * @param n
	 * @return
	 */
	public static int punishmentNumber(int n) {
		if (RESULTS[n] > 0) {
			// cache and return results
			return RESULTS[n];
		}
		// find the greatest index of the result that has not been calculated yet
		int start = n;
		while (start >= 1 && RESULTS[start] == 0) {
			start--;
		}
		// calculate and cache all results from index start + 1 up to n
		for (int i = start + 1; i <= n; i++) {
			int squareI = i * i;
			int diff = 0;
			// check if the number is eligible and add its square if this is the case
			if (checkI(i, squareI)) {
				diff = squareI;
			} else {
				RESULTS[i] = RESULTS[i - 1];
			}
			RESULTS[i] = RESULTS[i - 1] + diff;
		}
		return RESULTS[n];
	}

	private static boolean checkI(int i, int squareI) {
		// find all digits of the number
		int index = 0;
		do {
			DIGITS[index++] = squareI % 10;
		} while ((squareI = squareI / 10) > 0);
		// use backtracking to check if any combination of the squared number's digits
		// is equal to the number
		return checkRecursive(DIGITS, i, 0, 0, index - 1);
	}

	private static boolean checkRecursive(int[] digits, int target, int currentSum, int nextSum, int index) {
		if (index < 0) {
			// no more digits, return result
			return currentSum + nextSum == target;
		}
		int newSum = 10 * nextSum + digits[index];
		// calculate both cases, one is adding the digit to the current sum and the
		// other is keeping the digit to be appended to the next digit
		return checkRecursive(digits, target, currentSum, newSum, index - 1)
				|| checkRecursive(digits, target, currentSum + newSum, 0, index - 1);
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int punishmentNumber = punishmentNumber(n); // Calls your implementation
		System.out.println("punishmentNumber is: " + punishmentNumber);
	}
}
