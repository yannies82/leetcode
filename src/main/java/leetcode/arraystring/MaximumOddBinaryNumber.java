package leetcode.arraystring;

public class MaximumOddBinaryNumber {

	public static void main(String[] args) {
		check("010", "001");
		check("0101", "1001");
	}

	/**
	 * This solution iterates the string to find the number of '1's then places them
	 * to the left of the string except for one. Time complexity is O(n) where n is
	 * the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String maximumOddBinaryNumber(String s) {
		int length = s.length();
		int numOf1s = 0;
		// count the number of 1s in the string s
		for (int i = 0; i < length; i++) {
			if (s.charAt(i) == '1') {
				numOf1s++;
			}
		}
		char[] chars = new char[length];
		// place all 1s except for one at the most important digits
		for (int i = 0; i < numOf1s - 1; i++) {
			chars[i] = '1';
		}
		// fill the rest of the positions with 0s
		for (int i = numOf1s - 1; i < length - 1; i++) {
			chars[i] = '0';
		}
		// fill the last digit
		chars[length - 1] = numOf1s == 0 ? '0' : '1';
		return String.valueOf(chars);
	}

	private static void check(String s, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String maximumOddBinaryNumber = maximumOddBinaryNumber(s); // Calls your implementation
		System.out.println("maximumOddBinaryNumber is: " + maximumOddBinaryNumber);
	}
}
