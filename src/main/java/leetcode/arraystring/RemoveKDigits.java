package leetcode.arraystring;

public class RemoveKDigits {

	public static void main(String[] args) {
		check("10001", 4, "0");
		check("112", 1, "11");
		check("1432219", 3, "1219");
		check("10200", 1, "200");
		check("10", 2, "0");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i <= 50000; i++) {
			builder.append('1');
		}
		for (int i = 0; i <= 50000; i++) {
			builder.append('2');
		}
		StringBuilder expectedBuilder = new StringBuilder();
		for (int i = 0; i <= 50000; i++) {
			expectedBuilder.append('1');
		}
		expectedBuilder.append('2');
		check(builder.toString(), 50000, expectedBuilder.toString());
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/remove-k-digits. This
	 * solution removes k numbers one at a time. The number which is removed at each
	 * iteration is the first one which is greater than the next number. If no such
	 * number is found then the last k numbers are removed all at once. Time
	 * complexity is O(n) where n is the length of the num string.
	 * 
	 * @param num
	 * @param k
	 * @return
	 */
	public static String removeKdigits(String num, int k) {
		if (k >= num.length()) {
			// early exit
			return "0";
		}
		// initialize a mutable string builder from the num string
		StringBuilder builder = new StringBuilder(num);
		// remove k characters, loop k times
		while (k > 0) {
			int index = 0;
			int lastIndex = builder.length() - 1;
			// start from index 0 and stop at the index where num[index] > num[index + 1]
			while (index < lastIndex && builder.charAt(index) <= builder.charAt(index + 1)) {
				index++;
			}
			if (index == lastIndex) {
				// if we reached the lastIndex this means that the builder contains
				// numbers in non decreasing order, therefore it is safe to delete the last k
				// characters all at once and exit the loop
				builder.delete(builder.length() - k, builder.length());
				break;
			}
			// delete the char at this index because it is greater than the character
			// of the next index
			builder.deleteCharAt(index);
			k--;
		}
		// remove remaining leading zeroes
		while (builder.length() > 0 && builder.charAt(0) == '0') {
			builder.deleteCharAt(0);
		}
		return builder.isEmpty() ? "0" : builder.toString();
	}

	private static void check(String num, int k, String expected) {
		System.out.println("num is: " + num);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		String removeKdigits = removeKdigits(num, k); // Calls your implementation
		System.out.println("removeKdigits is: " + removeKdigits);
	}
}
