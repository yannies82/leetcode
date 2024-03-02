package leetcode.arraystring;

public class LengthOfLastWord {

	public static void main(String[] args) {
		check("Hello World", 5);
		check("   fly me   to   the moon  ", 4);
		check("luffy is still joyboy", 6);
	}

	public static int lengthOfLastWord(String s) {
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
