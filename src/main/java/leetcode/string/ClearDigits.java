package leetcode.string;

public class ClearDigits {

	public static void main(String[] args) {
		check("abc", "abc");
		check("cb34", "");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/clear-digits. Time complexity
	 * is O(n) where n is the length of string s.
	 * 
	 * @param ratings
	 * @return
	 */
	public static String clearDigits(String s) {
		StringBuilder builder = new StringBuilder(s);
		int nonDigitsToDelete = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (isDigit(s, i)) {
				nonDigitsToDelete++;
				builder.deleteCharAt(i);
			} else if (nonDigitsToDelete > 0) {
				nonDigitsToDelete--;
				builder.deleteCharAt(i);
			}
		}
		return builder.toString();
	}

	private static boolean isDigit(String s, int i) {
		char current = s.charAt(i);
		return current >= '0' && current <= '9';
	}

	private static void check(String s, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String clearDigits = clearDigits(s); // Calls your implementation
		System.out.println("clearDigits is: " + clearDigits);
	}
}
