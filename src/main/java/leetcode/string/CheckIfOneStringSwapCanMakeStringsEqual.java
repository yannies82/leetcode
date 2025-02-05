package leetcode.string;

public class CheckIfOneStringSwapCanMakeStringsEqual {

	public static void main(String[] args) {
		check("bank", "kanb", true);
		check("attack", "defend", false);
		check("kelb", "kelb", true);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal.
	 * Time complexity is O(n) where n is the length of string s1.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean areAlmostEqual(String s1, String s2) {
		int s1Length = s1.length();
		int s2Length = s2.length();
		if (s1Length != s2Length) {
			return false;
		}
		int diffCount = 0;
		char foundFirst = '0';
		char foundSecond = '0';
		for (int i = 0; i < s1Length; i++) {
			char first = s1.charAt(i);
			char second = s2.charAt(i);
			if (first != second) {
				if (diffCount == 0) {
					foundFirst = first;
					foundSecond = second;
				} else if (diffCount == 1) {
					if (first != foundSecond || second != foundFirst) {
						return false;
					}
				} else {
					return false;
				}
				diffCount++;
			}
		}
		return diffCount == 0 || diffCount == 2;
	}

	private static void check(String s1, String s2, boolean expected) {
		System.out.println("s1 is: " + s1);
		System.out.println("s2 is: " + s2);
		System.out.println("expected is: " + expected);
		boolean areAlmostEqual = areAlmostEqual(s1, s2); // Calls your implementation
		System.out.println("areSentencesareAlmostEqualSimilar is: " + areAlmostEqual);
	}
}
