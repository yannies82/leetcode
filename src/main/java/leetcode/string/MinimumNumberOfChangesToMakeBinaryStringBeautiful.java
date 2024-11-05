package leetcode.string;

public class MinimumNumberOfChangesToMakeBinaryStringBeautiful {

	public static void main(String[] args) {
		check("1001", 2);
		check("10", 1);
		check("0000", 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful.
	 * This solution takes advantage of the fact that ASCII code for '0' is 48 and
	 * '0' ^ '1' == 1. Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int minChanges(String s) {
		int length = s.length();
		int minChanges = 0;
		for (int i = 0; i < length; i += 2) {
			minChanges += s.charAt(i) ^ s.charAt(i + 1);
		}
		return minChanges;
	}

	public static int minChanges2(String s) {
		int length = s.length();
		int minChanges = 0;
		for (int i = 0; i < length; i += 2) {
			minChanges += (s.charAt(i) + s.charAt(i + 1)) & 1;
		}
		return minChanges;
	}

	public static int minChanges3(String s) {
		int length = s.length();
		int index = 0;
		int minChanges = 0;
		while (index < length) {
			char prev = s.charAt(index);
			int start = index++;
			while (index < length && s.charAt(index) == prev) {
				index++;
			}
			int count = index - start;
			if ((count & 1) == 1) {
				minChanges++;
				index++;
			}
		}
		return minChanges;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int minChanges = minChanges(s); // Calls your implementation
		System.out.println("minChanges is: " + minChanges);
	}
}
