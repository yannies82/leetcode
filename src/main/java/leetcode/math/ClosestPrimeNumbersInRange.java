package leetcode.math;

import java.util.Arrays;

public class ClosestPrimeNumbersInRange {

	public static void main(String[] args) {
		check(10, 19, new int[] { 11, 13 });
		check(4, 6, new int[] { -1, -1 });
		check(19, 31, new int[] { 29, 31 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/closest-prime-numbers-in-range. Time complexity
	 * is O(n*sqrt(n)) where n is (right + left) / 2.
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static int[] closestPrimes(int left, int right) {
		// initialize num1 and num2 to such values so that i - lastPrime
		// will always be smaller than num2 - num1 if if one of them is < 0
		int num1 = Integer.MIN_VALUE;
		int num2 = -10000000;
		int diff = num2 - num1;
		int lastPrime = num2;
		for (int i = left; i <= right; i++) {
			if (isPrime(i)) {
				if (i - lastPrime < diff) {
					num1 = lastPrime;
					num2 = i;
					diff = num2 - num1;
					if (diff <= 2) {
						// twin primes or [2,3]
						return new int[] { num1, num2 };
					}
				}
				lastPrime = i;
			}
		}
		if (num1 < 0) {
			return new int[] { -1, -1 };
		}
		return new int[] { num1, num2 };
	}

	private static boolean isPrime(int n) {
		// check if n is 1 or 0
		if (n <= 1) {
			return false;
		}
		// check if n is 2 or 3
		if (n == 2 || n == 3) {
			return true;
		}
		// check whether n is divisible by 2 or 3
		if (n % 2 == 0 || n % 3 == 0) {
			return false;
		}
		// check from 5 to square root of n
		// iterate i by (i+6)
		int squareRootN = (int) Math.sqrt((double) n);
		for (int i = 5; i <= squareRootN; i = i + 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}
		return true;
	}

	private static void check(int left, int right, int[] expected) {
		System.out.println("left is: " + left);
		System.out.println("right is: " + right);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] closestPrimes = closestPrimes(left, right); // Calls your implementation
		System.out.println("closestPrimes is: " + Arrays.toString(closestPrimes));
	}
}
