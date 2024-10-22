package leetcode.string;

public class MaximumOddBinaryNumber {

	public static void main(String[] args) {
		check("010", "001");
		check("0101", "1001");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/maximum-odd-binary-number.
	 * This solution iterates the string to find the number of '1's then places them
	 * to the left of the string except for one. Time complexity is O(n) where n is
	 * the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String maximumOddBinaryNumber(String s) {
		char[] chars = s.toCharArray();
		int numOf1s = 0;
		// count the number of 1s in the string s
		for (int i = 0; i < chars.length; i++) {
			numOf1s += chars[i] - '0';
		}
		int numOf1sMinus1 = numOf1s - 1;
		// place all 1s except for one at the most important digits
		for (int i = 0; i < numOf1sMinus1; i++) {
			chars[i] = '1';
		}
		// fill the rest of the positions with 0s
		int lastIndex = chars.length - 1;
		for (int i = numOf1sMinus1; i < lastIndex; i++) {
			chars[i] = '0';
		}
		// fill the last digit
		chars[lastIndex] = numOf1s == 0 ? '0' : '1';
		return String.valueOf(chars);
	}

	private static void check(String s, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String maximumOddBinaryNumber = maximumOddBinaryNumber(s); // Calls your implementation
		System.out.println("maximumOddBinaryNumber is: " + maximumOddBinaryNumber);
	}
}
