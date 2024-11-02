package leetcode.string;

public class ScoreOfString {

	public static void main(String[] args) {
		check("hello", 13);
		check("zaz", 50);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/score-of-a-string. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param ratings
	 * @return
	 */
	public static int scoreOfString(String s) {
		int result = 0;
		int length = s.length();
		char prev = s.charAt(0);
		for (int i = 1; i < length; i++) {
			char current = s.charAt(i);
			result += Math.abs(current - prev);
			prev = current;
		}
		return result;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int scoreOfString = scoreOfString(s); // Calls your implementation
		System.out.println("scoreOfString is: " + scoreOfString);
	}
}
