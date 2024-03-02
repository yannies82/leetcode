package leetcode.arraystring;

public class FirstOccurenceInString {

	public static void main(String[] args) {
		check("sadbutsad", "sad", 0);
		check("leetcode", "leeto", -1);
		check("mississippi", "issip", 4);
		check("mississippi", "pi", 9);
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

	public static int strStr(String haystack, String needle) {
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
