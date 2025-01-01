package leetcode.string;

public class MaximumScoreAfterSplittingAString {

	public static void main(String[] args) {
		check("011101", 5);
		check("00111", 5);
		check("1111", 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-score-after-splitting-a-string. This
	 * solution performs a single loop to get the result. Time complexity is O(n)
	 * where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int maxScore(String s) {
		int length = s.length();
		int onesCount = 0;
		int score = '1' - s.charAt(0);
		int maxScore = score;
		int lastCharIndex = length - 1;
		for (int i = 1; i < lastCharIndex; i++) {
			char currentChar = s.charAt(i);
			score += 97 - (currentChar << 1);
			onesCount += currentChar - '0';
			if (score > maxScore) {
				maxScore = score;
			}
		}
		onesCount += s.charAt(lastCharIndex) - '0';
		return onesCount + maxScore;
	}

	/**
	 * This solution performs two loops to get the result. Time complexity is O(n)
	 * where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int maxScore2(String s) {
		char[] chars = s.toCharArray();
		int score = '1' - chars[0];
		for (int i = 1; i < chars.length; i++) {
			score += chars[i] - '0';
		}
		int maxScore = score;
		int lastCharIndex = chars.length - 1;
		for (int i = 1; i < lastCharIndex; i++) {
			score += 97 - (chars[i] << 1);
			maxScore = Math.max(maxScore, score);
		}
		return maxScore;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int maxScore = maxScore(s); // Calls your implementation
		System.out.println("maxScore is: " + maxScore);
	}
}
