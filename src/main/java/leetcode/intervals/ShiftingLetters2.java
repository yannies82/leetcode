package leetcode.intervals;

import java.util.Arrays;

public class ShiftingLetters2 {

	public static void main(String[] args) {
		check("abc", new int[][] { { 0, 1, 0 }, { 1, 2, 1 }, { 0, 2, 1 } }, "ace");
		check("dztz", new int[][] { { 0, 0, 0 }, { 1, 1, 1 } }, "catz");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/shifting-letters-ii. This
	 * solution marks the intervals where shifts will occur and applies the
	 * aggregated intervals to each character. Branchless solution. Time complexity
	 * is O(m+n) where m is the length of the shifts array and n is the length of
	 * string s.
	 * 
	 * @param s
	 * @param shifts
	 * @return
	 */
	public static String shiftingLetters(String s, int[][] shifts) {
		byte[] bytes = s.getBytes();
		int[] totalShift = new int[bytes.length + 1];
		for (int i = 0; i < shifts.length; i++) {
			// add 1 to the start of the interval, subtract 1 after the end of the interval
			int shift = (shifts[i][2] << 1) - 1;
			totalShift[shifts[i][0]] += shift;
			totalShift[shifts[i][1] + 1] -= shift;
		}
		int currentShift = 0;
		for (int i = 0; i < bytes.length; i++) {
			// apply the aggregated shift to each character
			currentShift += totalShift[i];
			// make sure to handle the cases where newChar is out of range
			int newChar = bytes[i] + (currentShift % 26);
			bytes[i] = (byte) ((((newChar - 'a') >>> 31) - (('z' - newChar) >>> 31)) * 26 + newChar);
		}
		return new String(bytes);
	}

	private static void check(String s, int[][] shifts, String expected) {
		System.out.println("s is: " + s);
		System.out.println("shifts is: ");
		for (int[] shift : shifts) {
			System.out.println(Arrays.toString(shift));
		}
		System.out.println("expected is: " + expected);
		String shiftingLetters = shiftingLetters(s, shifts); // Calls your implementation
		System.out.println("shiftingLetters is: " + shiftingLetters);
	}
}
