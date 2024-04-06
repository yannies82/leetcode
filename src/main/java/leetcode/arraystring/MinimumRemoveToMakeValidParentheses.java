package leetcode.arraystring;

public class MinimumRemoveToMakeValidParentheses {

	public static void main(String[] args) {
		check("lee(t(c)o)de)", "lee(t(c)o)de");
		check("a)b(c)d", "ab(c)d");
		check("))((", "");
	}

	/**
	 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String minRemoveToMakeValid(String s) {
		// keeps the number of open parentheses which do not
		// have a respective closing one
		int count = 0;
		StringBuilder builder = new StringBuilder();
		// iterate all characters and ignore surplus ')' characters
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				count++;
			} else if (c == ')') {
				if (count == 0) {
					// do not append character because it is surplus
					continue;
				}
				count--;
			}
			builder.append(c);
		}
		// iterate builder string from the end and remove surplus '(' characters
		int index = builder.length() - 1;
		while (count > 0) {
			if (builder.charAt(index) == '(') {
				builder.deleteCharAt(index);
				count--;
			}
			index--;
		}
		return builder.toString();
	}

	private static void check(String s, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String minRemoveToMakeValid = minRemoveToMakeValid(s); // Calls your implementation
		System.out.println("minRemoveToMakeValid is: " + minRemoveToMakeValid);
	}
}
