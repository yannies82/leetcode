package leetcode.string;

public class DeleteCharactersToMakeFancyString {

	public static void main(String[] args) {
		check("leeetcode", "leetcode");
		check("aaabaaaa", "aabaa");
		check("aab", "aab");
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/delete-characters-to-make-fancy-string. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String makeFancyString(String s) {
		char[] chars = s.toCharArray();
		StringBuilder builder = new StringBuilder().append(chars[0]);
		int count = 1;
		char prev = chars[0];
		for (int i = 1; i < chars.length; i++) {
			if (chars[i] == prev) {
				if (count < 2) {
					builder.append(chars[i]);
					count++;
				}
			} else {
				builder.append(chars[i]);
				count = 1;
				prev = chars[i];
			}
		}
		return builder.toString();
	}

	private static void check(String s, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String makeFancyString = makeFancyString(s); // Calls your implementation
		System.out.println("makeFancyString is: " + makeFancyString);
	}
}
