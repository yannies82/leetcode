package leetcode.stack;

public class ConstructSmallestNumberFromDIString {

	public static void main(String[] args) {
		check("DDD", "4321");
		check("IIIDIDDD", "123549876");
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/construct-smallest-number-from-di-string. This
	 * solution uses an array as a stack in order to keep numbers for descending
	 * sequences, so that they can be appended in reverse. Time complexity is O(n)
	 * where n is the length of the pattern String.
	 * 
	 * @param pattern
	 * @return
	 */
	public static String smallestNumber(String pattern) {
		int n = pattern.length();
		StringBuilder result = new StringBuilder();
		int[] stack = new int[n + 1];
		int index = 0;
		for (int i = 0; i <= n; i++) {
			stack[index++] = i + 1;
			if (i == n || pattern.charAt(i) == 'I') {
				while (index > 0) {
					result.append(stack[--index]);
				}
			}
		}

		return result.toString();
	}

	/**
	 * This solution uses backtracking to check all possible combinations and uses
	 * an array to accumulate the result. Time complexity is O(n^n) where n is the
	 * length of the pattern string.
	 * 
	 */
	public static String smallestNumber2(String pattern) {
		char[] patternChars = pattern.toCharArray();
		char[] finalPatternChars = new char[patternChars.length + 1];
		System.arraycopy(patternChars, 0, finalPatternChars, 0, patternChars.length);
		boolean[] usedNumbers = new boolean[patternChars.length + 2];
		int[] result = new int[patternChars.length + 1];
		calculate(finalPatternChars, usedNumbers, result, 0, 'I', 0);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			builder.append(result[i]);
		}
		return builder.toString();
	}

	private static boolean calculate(char[] patternChars, boolean[] usedNumbers, int[] result, int i, char charPattern,
			int prevNumber) {
		if (i == patternChars.length) {
			return true;
		}
		for (int j = 1; j < usedNumbers.length; j++) {
			if (!usedNumbers[j] && ((charPattern == 'I' && j > prevNumber) || (charPattern == 'D' && j < prevNumber))) {
				result[i] = j;
				usedNumbers[j] = true;
				if (calculate(patternChars, usedNumbers, result, i + 1, patternChars[i], j)) {
					return true;
				}
				usedNumbers[j] = false;
			}
		}
		return false;
	}

	private static void check(String pattern, String expected) {
		System.out.println("pattern is: " + pattern);
		System.out.println("expected is: " + expected);
		String smallestNumber = smallestNumber(pattern); // Calls your implementation
		System.out.println("smallestNumber is: " + smallestNumber);
	}
}
