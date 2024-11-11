package leetcode.math;

import java.util.Arrays;

public class PrimeSubtractionOperation {

	public static void main(String[] args) {
		check(new int[] { 4, 9, 6, 10 }, true);
		check(new int[] { 6, 8, 11, 12 }, true);
		check(new int[] { 5, 8, 3 }, false);
		check(new int[] { 3, 4, 10, 15, 6 }, true);
	}

	private static final boolean[] COMPOSITES = calculateComposites();

	private static boolean[] calculateComposites() {
		// calculate all composite numbers up to 999 using the sieve of Eratosthenes
		boolean[] result = new boolean[1000];
		for (int i = 4; i < 1000; i += 2) {
			result[i] = true;
		}
		for (int i = 3; i < 1000; i += 2) {
			int current = i + i;
			while (current < result.length) {
				result[current] = true;
				current += i;
			}
		}
		return result;
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/prime-subtraction-operation.
	 * This solution precalculates all composite numbers up to 999 using the sieve
	 * of Eratosthenes and uses the sieve to greedily select the largest possible
	 * prime number for each step. Time complexity is O(n) where n is the length of
	 * the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static boolean primeSubOperation(int[] nums) {
		int prev = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= prev) {
				return false;
			}
			// find the largest prime which is less than nums[i] - prev
			// so that subtracting it from nums[i] will keep nums strictly ascending
			int prime = pickMaxPrimeLessThan(nums[i] - prev);
			prev = nums[i] - prime;
		}
		return true;
	}

	private static int pickMaxPrimeLessThan(int num) {
		for (int i = num - 1; i >= 2; i--) {
			if (!COMPOSITES[i]) {
				return i;
			}
		}
		return 0;
	}

	private static void check(int[] nums, boolean expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		boolean primeSubOperation = primeSubOperation(nums); // Calls your implementation
		System.out.println("primeSubOperation is: " + primeSubOperation);
	}
}
