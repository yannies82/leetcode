package leetcode.string;

import java.util.Arrays;

public class NumberOfSeniorCitizens {

	public static void main(String[] args) {
		check(new String[] { "7868190130M7522", "5303914400F9211", "9273338290F4010" }, 2);
		check(new String[] { "1313579440F2036", "2921522980M5644" }, 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/number-of-senior-citizens.
	 * Time complexity is O(n) where n is the length of the details array.
	 * 
	 * @param details
	 * @return
	 */
	public static int countSeniors(String[] details) {
		int count = 0;
		int offset = 11 * '0';
		for (int i = 0; i < details.length; i++) {
			count += (60 - (10 * details[i].charAt(11) + details[i].charAt(12) - offset)) >>> 31;
		}
		return count;
	}
	
	public static int countSeniors2(String[] details) {
		int count = 0;
		for (int i = 0; i < details.length; i++) {
			char ageMajor = details[i].charAt(11);
			if (ageMajor > '6' || (ageMajor == '6' && details[i].charAt(12) > '0')) {
				count++;
			}
		}
		return count;
	}

	private static void check(String[] details, int expected) {
		System.out.println("ratings is: " + Arrays.toString(details));
		System.out.println("expected is: " + expected);
		int countSeniors = countSeniors(details); // Calls your implementation
		System.out.println("countSeniors is: " + countSeniors);
	}
}
