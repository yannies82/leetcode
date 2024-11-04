package leetcode.string;

public class StringCompression3 {

	public static void main(String[] args) {
		check("abcde", "1a1b1c1d1e");
		check("aaaaaaaaaaaaaabb", "9a5a2b");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/string-compression-iii. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String compressedString(String word) {
		StringBuilder builder = new StringBuilder();
		int length = word.length();
		int i = 0;
		while (i < length) {
			int start = i;
			char prev = word.charAt(i++);
			while (i < length && prev == word.charAt(i)) {
				i++;
			}
			int count = i - start;
			while (count > 9) {
				builder.append('9').append(prev);
				count -= 9;
			}
			builder.append((char) (count + '0')).append(prev);
		}
		return builder.toString();
	}

	public static String compressedString2(String word) {
		StringBuilder builder = new StringBuilder();
		char prev = word.charAt(0);
		int length = word.length();
		int count = 1;
		for (int i = 1; i < length; i++) {
			char current = word.charAt(i);
			if (current == prev) {
				if (count == 9) {
					builder.append((char) (count + '0')).append(current);
					count = 1;
				} else {
					count++;
				}
			} else {
				builder.append((char) (count + '0')).append(prev);
				count = 1;
				prev = current;
			}
		}
		builder.append(count).append(prev);
		return builder.toString();
	}

	public static String compressedString3(String word) {
		char[] chars = word.toCharArray();
		StringBuilder builder = new StringBuilder();
		char prev = chars[0];
		int count = 1;
		for (int i = 1; i < chars.length; i++) {
			if (chars[i] == prev) {
				if (count == 9) {
					builder.append((char) (count + '0')).append(chars[i]);
					count = 1;
				} else {
					count++;
				}
			} else {
				builder.append((char) (count + '0')).append(prev);
				count = 1;
				prev = chars[i];
			}
		}
		builder.append(count).append(prev);
		return builder.toString();
	}

	private static void check(String s, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String compressedString = compressedString(s); // Calls your implementation
		System.out.println("compressedString is: " + compressedString);
	}
}
