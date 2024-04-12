package leetcode.math;

import java.util.Arrays;

public class PlusOne {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3 }, new int[] { 1, 2, 4 });
		check(new int[] { 9, 9, 9 }, new int[] { 1, 0, 0, 0 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/plus-one. Time complexity is
	 * O(N) where N is the digits length.
	 * 
	 * @param digits
	 * @return
	 */
	public static int[] plusOne(int[] digits) {
		int carry = 1;
		for (int i = digits.length - 1; i >= 0 && carry > 0; i--) {
			int sum = digits[i] + carry;
			carry = sum / 10;
			digits[i] = sum % 10;
		}
		// if there is a carry allocate a new array to fit the result
		if (carry > 0) {
			int[] result = new int[digits.length + 1];
			result[0] = carry;
			System.arraycopy(digits, 0, result, 1, digits.length);
			return result;
		}
		return digits;
	}

	private static void check(int[] digits, int[] expected) {
		System.out.println("digits is: " + Arrays.toString(digits));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] plusOne = plusOne(digits); // Calls your implementation
		System.out.println("plusOne is: " + Arrays.toString(plusOne));
	}

}
