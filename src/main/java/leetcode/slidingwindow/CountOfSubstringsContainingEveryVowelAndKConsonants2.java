package leetcode.slidingwindow;

public class CountOfSubstringsContainingEveryVowelAndKConsonants2 {

	public static void main(String[] args) {
		check("aeioqq", 1, 0);
		check("aeiou", 0, 1);
		check("ieaouqqieaouqq", 1, 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii.
	 * This solution uses a sliding window to calculate all valid substrings. Time
	 * complexity is O(n) where n is the length of the word string.
	 * 
	 * @param word
	 * @param k
	 * @return
	 */
	public static long countOfSubstrings(String word, int k) {
		byte[] bytes = word.getBytes();
		int[] frequency = new int[128];
		boolean[] vowels = new boolean[128];
		vowels['a'] = vowels['e'] = vowels['i'] = vowels['o'] = vowels['u'] = true;
		long result = 0;
		int consonantsCount = 0;
		int distinctVowelsCount = 0;
		int extraLeft = 0;
		for (int right = 0, left = 0; right < bytes.length; right++) {
			byte rightChar = bytes[right];
			if (vowels[rightChar]) {
				frequency[rightChar]++;
				if (frequency[rightChar] == 1) {
					distinctVowelsCount++;
				}
			} else {
				consonantsCount++;
			}

			while (consonantsCount > k) {
				byte leftChar = bytes[left];
				if (vowels[leftChar]) {
					frequency[leftChar]--;
					if (frequency[leftChar] == 0) {
						distinctVowelsCount--;
					}
				} else {
					consonantsCount--;
				}
				left++;
				extraLeft = 0;
			}
			while (distinctVowelsCount == 5 && consonantsCount == k && left < right && vowels[bytes[left]]
					&& frequency[bytes[left]] > 1) {
				extraLeft++;
				frequency[bytes[left]]--;
				left++;
			}
			if (distinctVowelsCount == 5 && consonantsCount == k) {
				result += 1 + extraLeft;
			}
		}
		return result;
	}

	private static void check(String word, int k, long expected) {
		System.out.println("word is: " + word);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		long countOfSubstrings = countOfSubstrings(word, k); // Calls your implementation
		System.out.println("countOfSubstrings is: " + countOfSubstrings);
	}
}
