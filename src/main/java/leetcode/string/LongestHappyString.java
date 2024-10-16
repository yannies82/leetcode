package leetcode.string;

public class LongestHappyString {

	public static void main(String[] args) {
		check(2, 2, 1, "ababc");
		check(2, 4, 1, "bbabbac");
		check(1, 1, 7, "ccbccacc");
		check(7, 1, 0, "aabaa");
	}

	private static char[] chars = { 'a', 'b', 'c' };

	/**
	 * Leetcode problem: https://leetcode.com/problems/longest-happy-string. This
	 * solution favors appending the character with greatest frequency more times
	 * until the remaining frequencies are equal or there are no characters left.
	 * Time complexity is O(max(a,b,c)).
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static String longestDiverseString(int a, int b, int c) {
		StringBuilder builder = new StringBuilder();
		int[] frequencies = { a, b, c };
		// find the min, max and secondMax indexes according to the frequencies
		int minIndex = 0;
		int maxIndex = 0;
		for (int i = 1; i < frequencies.length; i++) {
			if (frequencies[i] < frequencies[minIndex]) {
				minIndex = i;
			} else if (frequencies[i] >= frequencies[maxIndex]) {
				maxIndex = i;
			}
		}
		int secondMaxIndex = 3 ^ minIndex ^ maxIndex;
		while (frequencies[maxIndex] > frequencies[secondMaxIndex] && frequencies[maxIndex] - frequencies[minIndex] > 1
				&& frequencies[secondMaxIndex] > 0) {
			// as long as there is a character with greater frequency than the other two,
			// use it as much as possible, appending 2 chars followed by 1 char of the
			// second max frequency
			builder.append(chars[maxIndex]).append(chars[maxIndex]).append(chars[secondMaxIndex]);
			frequencies[maxIndex] -= 2;
			frequencies[secondMaxIndex] -= 1;
			// switch minIndex and secondMaxIndex if required
			if (frequencies[secondMaxIndex] < frequencies[minIndex]) {
				int tempIndex = secondMaxIndex;
				secondMaxIndex = minIndex;
				minIndex = tempIndex;
			}
		}
		if (frequencies[minIndex] == frequencies[secondMaxIndex]
				&& frequencies[maxIndex] - frequencies[minIndex] == 1) {
			// append 1 maxIndex character so that all characters will have the same
			// remaining frequency
			builder.append(chars[maxIndex]);
			frequencies[maxIndex]--;
		}
		if (frequencies[maxIndex] == frequencies[secondMaxIndex]) {
			// there are at least two different characters with the same frequency
			while (frequencies[maxIndex] > frequencies[minIndex]) {
				// the two characters have a frequency greater than the third one's, append them
				// in succession
				builder.append(chars[maxIndex]).append(chars[secondMaxIndex]);
				frequencies[maxIndex]--;
			}
			while (frequencies[maxIndex] > 0) {
				// all 3 characters have the same remaining frequency, append them in succession
				builder.append(chars[maxIndex]).append(chars[secondMaxIndex]).append(chars[minIndex]);
				frequencies[maxIndex]--;
			}
		} else {
			// only the maxIndex characters are left, append 2 of them at most
			builder.append(chars[maxIndex]);
			if (frequencies[maxIndex] > 1) {
				builder.append(chars[maxIndex]);
			}
		}
		return builder.toString();
	}

	private static void check(int a, int b, int c, String expected) {
		System.out.println("a is: " + a);
		System.out.println("b is: " + b);
		System.out.println("c is: " + c);
		System.out.println("expected is: " + expected);
		String longestDiverseString = longestDiverseString(a, b, c); // Calls your implementation
		System.out.println("longestDiverseString is: " + longestDiverseString);
	}
}
