package leetcode.array.frequency;

import java.util.Arrays;

public class MinimumNumberOfPushesToTypeWord2 {

	public static void main(String[] args) {
		check("abcde", 5);
		check("xyzxyzxyzxyz", 12);
		check("aabbccddeeffgghhiiiiii", 24);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii. This
	 * solution counts the frequency of all characters in string word, then sorts
	 * the frequency array to calculate the min number of pushes by assigning the
	 * most frequent letters to require the least number of pushes. Time complexity
	 * is O(n) where n is the length of the word string.
	 * 
	 * @param word
	 * @return
	 */
	public static int minimumPushes(String word) {
		int[] frequency = new int[26];
		char[] chars = word.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			frequency[chars[i] - 'a']++;
		}
		Arrays.sort(frequency);
		int result = 0;
		int factor = 1;
		for (int i = 25; i >= 0 && frequency[i] > 0; i--) {
			if (i == 17 || i == 9 || i == 1) {
				factor++;
			}
			result += frequency[i] * factor;
		}
		return result;
	}

	private static void check(String word, int expected) {
		System.out.println("word is: " + word);
		System.out.println("expected is: " + expected);
		int minimumPushes = minimumPushes(word); // Calls your implementation
		System.out.println("minimumPushes is: " + minimumPushes);
	}
}
