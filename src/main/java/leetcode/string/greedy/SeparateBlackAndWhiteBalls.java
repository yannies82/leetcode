package leetcode.string.greedy;

public class SeparateBlackAndWhiteBalls {

	public static void main(String[] args) {
		check("101", 1);
		check("100", 2);
		check("0111", 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/separate-black-and-white-balls. This solution
	 * greedily counts the number of substitutions for all 0s that are not at the
	 * start of the string. Time complexity is O(n) where n is the length of string
	 * s.
	 * 
	 * @param s
	 * @return
	 */
	public static long minimumSteps(String s) {
		char[] chars = s.toCharArray();
		// this will be the index of the first 1 of the string
		int startIndex = 0;
		// skip all 0s to get to the first 1
		while (startIndex < chars.length && chars[startIndex] == '0') {
			startIndex++;
		}
		// this will keep the number of substitutions required
		long count = 0;
		// this is the current index, initialized to be just after the first 1 of the
		// string
		int currentIndex = startIndex + 1;
		while (currentIndex < chars.length) {
			if (chars[currentIndex] == '0') {
				// every 0 we encounter will need currentIndex - startIndex substitutions to
				// reach the other 0s at the start of the string
				count += currentIndex - startIndex;
				// since the 0 was brought to the start of the string increase the position of
				// the first 1
				startIndex++;
			}
			currentIndex++;
		}
		return count;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		long minimumSteps = minimumSteps(s); // Calls your implementation
		System.out.println("minimumSteps is: " + minimumSteps);
	}
}
