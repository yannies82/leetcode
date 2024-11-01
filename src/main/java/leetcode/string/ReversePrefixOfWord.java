package leetcode.string;

public class ReversePrefixOfWord {

	public static void main(String[] args) {
		check("abcdefd", 'd', "dcbaefd");
		check("xyxzxe", 'z', "zxyxxe");
		check("abcd", 'z', "abcd");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/reverse-prefix-of-word. This
	 * solution performs a linear search to find the character ch in the string,
	 * then reverses the characters up to that point. Time complexity is O(n) where
	 * n is the length of string word.
	 * 
	 * @param word
	 * @param ch
	 * @return
	 */
	public static String reversePrefix(String word, char ch) {
		char[] chars = word.toCharArray();
		int index = -1;
		// find index of first occurence of char ch in word
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == ch) {
				index = i;
				break;
			}
		}
		// reverse characters up to index, if index >= 0
		int end = (index - 1) >> 1;
		for (int i = 0; i <= end; i++) {
			char temp = chars[i];
			chars[i] = chars[index - i];
			chars[index - i] = temp;
		}
		return new String(chars);
	}

	private static void check(String word, char ch, String expected) {
		System.out.println("word is: " + word);
		System.out.println("ch is: " + ch);
		System.out.println("expected is: " + expected);
		String reversePrefix = reversePrefix(word, ch); // Calls your implementation
		System.out.println("reversePrefix is: " + reversePrefix);
	}
}
