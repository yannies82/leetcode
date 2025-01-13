package leetcode.string.frequency;

public class MinimumLengthOfStringAfterOperations {

	public static void main(String[] args) {
		check("abaacbcbb", 5);
		check("aa", 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-length-of-string-after-operations. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int minimumLength(String s) {
		int[] frequency = new int[26];
		int length = s.length();
		for (int i = 0; i < length; i++) {
			frequency[s.charAt(i) - 'a']++;
		}
		int newLength = 0;
		for (int i = 0; i < frequency.length; i++) {
			if (frequency[i] == 0) {
				continue;
			}
			newLength += (frequency[i] & 1) + ((1 - (frequency[i] & 1)) << 1);
		}
		return newLength;
	}

	public static int minimumLength2(String s) {
		int[] frequency = new int[26];
		int length = s.length();
		for (int i = 0; i < length; i++) {
			frequency[s.charAt(i) - 'a']++;
		}
		int newLength = 0;
		for (int i = 0; i < frequency.length; i++) {
			if (frequency[i] == 0) {
				continue;
			}
			newLength += (frequency[i] & 1) == 1 ? 1 : 2;
		}
		return newLength;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int minimumLength = minimumLength(s); // Calls your implementation
		System.out.println("minimumLength is: " + minimumLength);
	}
}
