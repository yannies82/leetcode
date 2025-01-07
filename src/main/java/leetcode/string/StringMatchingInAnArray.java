package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringMatchingInAnArray {

	public static void main(String[] args) {
		check(new String[] { "mass", "as", "hero", "superhero" }, List.of("as", "hero"));
		check(new String[] { "leetcode", "et", "code" }, List.of("et", "code"));
		check(new String[] { "blue", "green", "bu" }, List.of());
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/string-matching-in-an-array.
	 * This solution uses a separate method for checking if a string is a substring
	 * of another. Performance is better this way due to JIT compiler optimizations
	 * (method inlining). Time complexity is O(m^2*n^2) where m is the length of the
	 * average word and n is the length of the words array.
	 * 
	 * @param words
	 * @return
	 */
	public static List<String> stringMatching(String[] words) {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			if (isSubstring(words, i)) {
				result.add(words[i]);
			}
		}
		return result;
	}

	private static boolean isSubstring(String[] words, int i) {
		for (int j = 0; j < words.length; j++) {
			if (j != i && words[j].indexOf(words[i]) >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This solution concatenates all strings in the words array and checks if each
	 * string occurs twice in the concatenated string. Time complexity is O(m^2*n^2)
	 * where m is the length of the average word and n is the length of the words
	 * array.
	 * 
	 * @param words
	 * @return
	 */
	public static List<String> stringMatching2(String[] words) {
		String concatenatedString = String.join(" ", words);
		List<String> result = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			if (concatenatedString.indexOf(words[i]) != concatenatedString.lastIndexOf(words[i])) {
				result.add(words[i]);
			}
		}
		return result;
	}

	/**
	 * This solution iterates all words and checks if one contains another. Time
	 * complexity is O(m^2*n^2) where m is the length of the average word and n is
	 * the length of the words array.
	 * 
	 * @param words
	 * @return
	 */
	public static List<String> stringMatching3(String[] words) {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				if (i != j && words[j].indexOf(words[i]) >= 0) {
					result.add(words[i]);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * This solution manually implements the logic for matching the words. Time
	 * complexity is O(m^2*n^2) where m is the length of the average word and n is
	 * the length of the words array.
	 * 
	 * @param words
	 * @return
	 */
	public static List<String> stringMatching4(String[] words) {
		char[][] wordChars = new char[words.length][];
		for (int i = 0; i < words.length; i++) {
			wordChars[i] = words[i].toCharArray();
		}
		Arrays.sort(wordChars, (a, b) -> a.length - b.length);
		List<String> result = new ArrayList<>();
		outer: for (int i = 0; i < wordChars.length; i++) {
			char[] firstWord = wordChars[i];
			for (int j = i + 1; j < words.length; j++) {
				char[] secondWord = wordChars[j];
				if (firstWord.length == secondWord.length) {
					continue;
				}
				int limit = secondWord.length - firstWord.length;
				inner: for (int k = 0; k <= limit; k++) {
					if (firstWord[0] != secondWord[k]) {
						continue;
					}
					for (int l = 1; l < firstWord.length; l++) {
						if (firstWord[l] != secondWord[l + k]) {
							continue inner;
						}
					}
					result.add(new String(firstWord));
					continue outer;
				}
			}
		}
		return result;
	}

	private static void check(String[] words, List<String> expected) {
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("expected is: " + expected);
		List<String> stringMatching = stringMatching(words); // Calls your implementation
		System.out.println("stringMatching is: " + stringMatching);
	}
}
