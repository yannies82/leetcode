package leetcode.string;

public class CircularSentence {

	public static void main(String[] args) {
		check("leetcode exercises sound delightful", true);
		check("eetcode", true);
		check("Leetcode is cool", false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/circular-sentence. Time
	 * complexity is O(n) where n is the length of sentence s.
	 * 
	 * @param sentence
	 * @return
	 */
	public static boolean isCircularSentence(String sentence) {
		char[] chars = sentence.toCharArray();
		int lastIndex = chars.length - 1;
		if (chars[0] != chars[lastIndex]) {
			return false;
		}
		for (int i = 1; i < lastIndex; i++) {
			if (chars[i] == ' ') {
				if (chars[i - 1] != chars[i + 1]) {
					return false;
				}
			}
		}
		return true;
	}

	private static void check(String sentence, boolean expected) {
		System.out.println("sentence is: " + sentence);
		System.out.println("expected is: " + expected);
		boolean isCircularSentence = isCircularSentence(sentence); // Calls your implementation
		System.out.println("isCircularSentence is: " + isCircularSentence);
	}
}
