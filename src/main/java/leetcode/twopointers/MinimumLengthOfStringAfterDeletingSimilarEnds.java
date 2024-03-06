package leetcode.twopointers;

public class MinimumLengthOfStringAfterDeletingSimilarEnds {

	public static void main(String[] args) {
		check("ca", 2);
		check("cabaabac", 0);
		check("aabccabba", 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends.
	 * This solution uses two pinters at both ends of the string and converges them
	 * until chars[start] != chars[end]. Time complexity is O(n) where n is the
	 * length of the string.
	 * 
	 * @param s
	 * @return
	 */
	public static int minimumLength(String s) {
		char[] chars = s.toCharArray();
		int length = chars.length;
		int start = 0;
		int end = length - 1;
		while (start < end) {
			char cStart = chars[start];
			if (cStart != chars[end]) {
				// if char at start is different from char at end return the length of the
				// substring
				return end - start + 1;
			}
			while (start <= end && chars[start] == cStart) {
				// increase start index while the char remains the same
				start++;
			}
			while (start <= end && chars[end] == cStart) {
				// decrease end index while the char remains the same
				end--;
			}
		}
		// return the length of the substring
		return end - start + 1;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int minimumLength = minimumLength(s); // Calls your implementation
		System.out.println("minimumLength is: " + minimumLength);
	}
}
