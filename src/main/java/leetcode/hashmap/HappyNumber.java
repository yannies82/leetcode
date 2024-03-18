package leetcode.hashmap;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

	public static void main(String[] args) {
		check(19, true);
		check(2, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/happy-number. This solution
	 * calculates the next number n until it is 1 or 4, because these are the
	 * numbers that cycle. Time complexity is O(nlogn).
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isHappy(int n) {
		do {
			// calculate the sum of the squares of all digits of n
			int sum = 0;
			do {
				int digit = n % 10;
				sum += digit * digit;
				n = n / 10;
			} while (n > 0);
			// set the sum as the new number n
			n = sum;
			// continue as long as n is different from 1 or 4, because it cycles
			// for these numbers
		} while (n != 1 && n != 4);
		return n == 1;
	}

	/**
	 * This solution uses a set to store the previous values and detect if the
	 * values cycle or not. Time complexity is O(nlogn).
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isHappy2(int n) {
		// set to store the previous values
		Set<Integer> prevValues = new HashSet<>();
		do {
			// calculate the sum of the squares of all digits of n
			int sum = 0;
			do {
				int digit = n % 10;
				sum += digit * digit;
				n = n / 10;
			} while (n > 0);
			// set the sum as the new number n
			n = sum;
			// continue as long as n is different from 1 and does not already exist in the
			// set
		} while (prevValues.add(n) && n != 1);
		return n == 1;
	}

	private static void check(int n, boolean expectedResult) {
		System.out.println("n is: " + n);
		System.out.println("expectedResult is: " + expectedResult);
		boolean isHappy = isHappy(n); // Calls your implementation
		System.out.println("isHappy is: " + isHappy);
	}
}
