package leetcode.math;

public class TheKthLexicographicalStringOfAllHappyStringsOfLengthN {

	public static void main(String[] args) {
		check(1, 3, "c");
		check(1, 4, "");
		check(3, 9, "cab");
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n.
	 * This solution assumes the totalCount of happy strings for the provided n and
	 * the fact that they are lexicographically sorted so that it can used k as
	 * index for building the final string. Time complexity is O(n).
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static String getHappyString(int n, int k) {
		// total count of happy strings is 3*2^(n-1)
		int totalCount = (int) Math.pow(2, n - 1) * 3;
		if (k > totalCount) {
			// k is greater than the total count of happy strings
			return "";
		}
		StringBuilder builder = new StringBuilder();
		// dummy char which allows all possible values as next
		int prevChar = 'd';
		// 3 characters with exactly the same number of happy strings starting with each
		// one of them, therefore totalCount / 3 happy strings starting with each
		// character
		int partLength = totalCount / 3;
		// adjust k to be used for 0-based indexing
		int realK = k - 1;
		for (int i = 0; i < n; i++) {
			// find which part the current character belongs to and use it as
			// a 0-based index to identify the character
			int index = realK / partLength;
			// use the index to find the current character according to the allowed chars
			// for the previous character
			char currentChar = ALLOWED_CHARS_MAP[prevChar - 'a'][index];
			builder.append(currentChar);
			// calculate index for the next part
			realK = realK % partLength;
			// divide partLength by 2, since the count of happy strings for n - 1 is half
			// the count for n
			partLength >>>= 1;
			prevChar = currentChar;
		}
		return builder.toString();
	}

	// keeps all possible next values for a char, by index
	private static char[][] ALLOWED_CHARS_MAP = new char[][] { { 'b', 'c' }, { 'a', 'c' }, { 'a', 'b' },
			{ 'a', 'b', 'c' } };

	private static void check(int n, int k, String expected) {
		System.out.println("n is: " + n);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		String getHappyString = getHappyString(n, k); // Calls your implementation
		System.out.println("getHappyString is: " + getHappyString);
	}
}
