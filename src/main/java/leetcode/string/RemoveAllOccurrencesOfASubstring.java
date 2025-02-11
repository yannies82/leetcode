package leetcode.string;

public class RemoveAllOccurrencesOfASubstring {

	public static void main(String[] args) {
		check("daabcbaabcbc", "abc", "dab");
		check("axxxxyyyyb", "xy", "ab");
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/remove-all-occurrences-of-a-substring. Simple
	 * solution which uses the built in Java method indexOf. Time complexity is
	 * O(n*m) where n is the length of string s and m is the length of string part.
	 * 
	 * @param s
	 * @param part
	 * @return
	 */
	public static String removeOccurrences(String s, String part) {
		StringBuilder builder = new StringBuilder(s);
		int partLength = part.length();
		int index = -1;
		while ((index = builder.indexOf(part)) != -1) {
			builder.delete(index, index + partLength);
		}
		return builder.toString();
	}

	/**
	 * Custom solution, does not use indexOf methos, similar idea to the first one.
	 * Time complexity is O(n*m) where n is the length of string s and m is the
	 * length of string part.
	 * 
	 * @param s
	 * @param part
	 * @return
	 */
	public static String removeOccurrences2(String s, String part) {
		StringBuilder builder = new StringBuilder(s);
		char[] partChars = part.toCharArray();
		int limit = builder.length() - partChars.length;
		int i = 0;
		while (i <= limit) {
			if (isContained(builder, partChars, i)) {
				builder.delete(i, i + partChars.length);
				limit -= partChars.length;
				i = Math.max(i - partChars.length + 1, 0);
			} else {
				i++;
			}
		}
		return builder.toString();
	}

	private static boolean isContained(StringBuilder builder, char[] partChars, int i) {
		char current = builder.charAt(i);
		boolean isEqual = current == partChars[0];
		if (isEqual) {
			for (int j = 1; j < partChars.length; j++) {
				if (builder.charAt(i + j) != partChars[j]) {
					return false;
				}
			}
		}
		return isEqual;
	}

	private static void check(String s, String part, String expected) {
		System.out.println("s is: " + s);
		System.out.println("part is: " + part);
		System.out.println("expected is: " + expected);
		String removeOccurrences = removeOccurrences(s, part); // Calls your implementation
		System.out.println("removeOccurrences is: " + removeOccurrences);
	}
}
