package leetcode.string;

public class FirstOccurenceInString {

	public static void main(String[] args) {
		check("sadbutsad", "sad", 0);
		check("leetcode", "leeto", -1);
		check("mississippi", "issip", 4);
		check("mississippi", "pi", 9);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string.
	 * This solution searches for occurences of the first character of needle in
	 * haystack, then searches for the rest of the needle characters starting from
	 * that index. Time complexity is O(n * m) where n is the haystack length and m
	 * is the needle length.
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr(String haystack, String needle) {
		char[] haystackChars = haystack.toCharArray();
		char[] needleChars = needle.toCharArray();
		int length = haystackChars.length;
		int needleLength = needleChars.length;
		int max = length - needleLength;
		char first = needleChars[0];
		int index = 0;
		while (true) {
			while (index <= max && haystackChars[index] != first) {
				index++;
			}
			if (index > max) {
				return -1;
			}
			int needleIndex = 1;
			while (needleIndex < needleLength && haystackChars[index + needleIndex] == needleChars[needleIndex]) {
				needleIndex++;
			}
			if (needleIndex == needleLength) {
				return index;
			}
			index++;
		}
	}

	public static int strStr2(String haystack, String needle) {
		int length = haystack.length();
		int needleLength = needle.length();
		int max = length - needleLength;
		char first = needle.charAt(0);
		for (int i = 0; i <= max; i++) {
			if (haystack.charAt(i) != first) {
				while (++i <= max && haystack.charAt(i) != first)
					;
			}
			if (i <= max) {
				int j = i + 1;
				int end = i + needleLength;
				for (int k = 1; j < end && haystack.charAt(j) == needle.charAt(k); j++, k++)
					;
				if (j == end) {
					return i;
				}
			}
		}
		return -1;
	}

	public static int strStr3(String haystack, String needle) {
		int length = haystack.length();
		int needleLength = needle.length();
		int max = length - needleLength;
		if (max < 0) {
			return -1;
		}
		int count = 0;
		int i = -1;
		while (++i < length) {
			if (haystack.charAt(i) == needle.charAt(count)) {
				count++;
			} else {
				i -= count;
				count = 0;
				if (i > max) {
					return -1;
				}
			}
			if (count == needleLength) {
				return i - count + 1;
			}
		}
		return -1;
	}

	private static void check(String haystack, String needle, int expectedIndex) {
		System.out.println("haystack is: " + haystack);
		System.out.println("needle is: " + needle);
		System.out.println("expectedIndex is: " + expectedIndex);
		int index = strStr(haystack, needle); // Calls your implementation
		System.out.println("index is: " + index);
	}
}
