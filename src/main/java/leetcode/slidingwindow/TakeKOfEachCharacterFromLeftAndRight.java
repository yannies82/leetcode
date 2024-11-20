package leetcode.slidingwindow;

public class TakeKOfEachCharacterFromLeftAndRight {

	public static void main(String[] args) {
		check("aabaaaacaabc", 2, 8);
		check("a", 1, -1);
		check("aabbccca", 2, 6);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/take-k-of-each-character-from-left-and-right.
	 * This solution uses a sliding window for excluding as many characters as
	 * possible, as long as k instances of 'a','b','c' are contained. Time
	 * complexity is O(n) where n is the length of the string s.
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	public static int takeCharacters(String s, int k) {
		if (k == 0) {
			return 0;
		}
		char[] chars = s.toCharArray();
		int[] count = new int[3];
		// calculate the frequency of all characters
		for (int i = 0; i < chars.length; i++) {
			count[chars[i] - 'a']++;
		}
		if (count[0] < k || count[1] < k || count[2] < k) {
			return -1;
		}
		int result = chars.length;
		int left = 0;
		int right = -1;
		// advance right pointer
		while (++right < chars.length) {
			int indexRight;
			// increase window size until the rest of the string contains less than k
			// instances of one character
			do {
				indexRight = chars[right] - 'a';
			} while (--count[indexRight] >= k && ++right < chars.length);
			// update the result after subtracting the window length from the string length
			result = Math.min(result, chars.length - (right - left));
			int indexLeft;
			// decrease window size until the character with less than k instances is
			// encountered again
			do {
				indexLeft = chars[left++] - 'a';
				count[indexLeft]++;
			} while (indexLeft != indexRight);
		}
		return result;
	}

	private static void check(String s, int k, int expected) {
		System.out.println("s is: " + s);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int takeCharacters = takeCharacters(s, k); // Calls your implementation
		System.out.println("takeCharacters is: " + takeCharacters);
	}
}
