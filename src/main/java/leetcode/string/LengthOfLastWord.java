package leetcode.string;

public class LengthOfLastWord {

	public static void main(String[] args) {
		check("Hello World", 5);
		check("   fly me   to   the moon  ", 4);
		check("luffy is still joyboy", 6);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/length-of-last-word. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLastWord(String s) {
		int index = s.length();
		while (s.charAt(--index) == ' ');
		int endIndex = index;
		while (--index >= 0 && s.charAt(index) != ' ');
		return endIndex - index;
	}

	/**
	 * Alternative solution. Time complexity is O(n) where n is the length of string
	 * s.
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLastWord2(String s) {
		int length = s.length();
		int lastWordLength = 0;
		for (int i = length - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				if (lastWordLength > 0) {
					break;
				}
			} else {
				lastWordLength++;
			}
		}
		return lastWordLength;
	}

	private static void check(String s, int expectedLastWordLength) {
		System.out.println("s is: " + s);
		System.out.println("expectedLastWordLength is: " + expectedLastWordLength);
		int lengthOfLastWord = lengthOfLastWord(s); // Calls your implementation
		System.out.println("lengthOfLastWord is: " + lengthOfLastWord);
	}
}
