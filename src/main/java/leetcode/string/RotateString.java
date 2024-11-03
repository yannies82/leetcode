package leetcode.string;

public class RotateString {

	public static void main(String[] args) {
		check("abcde", "cdeab", true);
		check("abcde", "abced", false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/rotate-string. This solution
	 * checks for a match on the first character, then compares the rest of the
	 * characters of the strings. Time complexity is O(n^2) where n is the length of
	 * both strings.
	 * 
	 * @param s
	 * @param goal
	 * @return
	 */
	public static boolean rotateString(String s, String goal) {
		int sLength = s.length();
		int goalLength = goal.length();
		if (sLength != goalLength) {
			// early exit if the strings have different lengths
			return false;
		}
		// use all characters of string s as starting points
		for (int i = 0; i < sLength; i++) {
			if (s.charAt(i) == goal.charAt(0)) {
				// if the first character matches, check the rest of the characters
				int matchedChars = 1;
				while (matchedChars < sLength) {
					int sIndex = (i + matchedChars) % sLength;
					if (s.charAt(sIndex) != goal.charAt(matchedChars)) {
						break;
					}
					matchedChars++;
				}
				if (matchedChars == sLength) {
					// all characters matched, return true
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Similar solution which converts the strings to arrays of chars first. Time
	 * complexity is O(n^2) where n is the length of both strings.
	 * 
	 * @param s
	 * @param goal
	 * @return
	 */
	public static boolean rotateString2(String s, String goal) {
		int sLength = s.length();
		int goalLength = goal.length();
		if (sLength != goalLength) {
			// early exit if the strings have different lengths
			return false;
		}
		char[] sChars = s.toCharArray();
		char[] goalChars = goal.toCharArray();
		// use all characters of string s as starting points
		for (int i = 0; i < sLength; i++) {
			if (sChars[i] == goalChars[0]) {
				// if the first character matches, check the rest of the characters
				int matchedChars = 1;
				while (matchedChars < sLength) {
					int sIndex = (i + matchedChars) % sLength;
					if (sChars[sIndex] != goalChars[matchedChars]) {
						break;
					}
					matchedChars++;
				}
				if (matchedChars == sLength) {
					// all characters matched, return true
					return true;
				}
			}
		}
		return false;
	}

	private static void check(String s, String goal, boolean expected) {
		System.out.println("s is: " + s);
		System.out.println("goal is: " + goal);
		System.out.println("expected is: " + expected);
		boolean rotateString = rotateString(s, goal); // Calls your implementation
		System.out.println("rotateString is: " + rotateString);
	}
}
