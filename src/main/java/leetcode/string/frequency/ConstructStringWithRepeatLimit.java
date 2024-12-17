package leetcode.string.frequency;

public class ConstructStringWithRepeatLimit {

	public static void main(String[] args) {
		check("cczazcc", 3, "zzcccac");
		check("aababab", 2, "bbabaa");
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/construct-string-with-repeat-limit. This
	 * solution keeps the frequency of each character and iterates the frequencies
	 * in reverse to append the greatest lexicographically characters first. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param repeatLimit
	 * @return
	 */
	public static String repeatLimitedString(String s, int repeatLimit) {
		char[] chars = s.toCharArray();
		int[] frequency = new int[26];
		for (int i = 0; i < chars.length; i++) {
			frequency[chars[i] - 'a']++;
		}
		char[] result = new char[chars.length];
		int resultIndex = 0;
		int lastIndex = frequency.length - 1;
		int nextIndex = lastIndex;
		for (int i = lastIndex; i >= 0; i--) {
			char currentChar = (char) (i + 'a');
			while (frequency[i] > repeatLimit) {
				for (int j = 0; j < repeatLimit; j++) {
					result[resultIndex++] = currentChar;
				}
				frequency[i] -= repeatLimit;
				int j = Math.min(i, nextIndex);
				while (--j >= 0 && frequency[j] == 0)
					;
				if (j < 0) {
					return new String(result, 0, resultIndex);
				}
				result[resultIndex++] = (char) (j + 'a');
				frequency[j]--;
				nextIndex = j + 1;
			}
			for (int j = 0; j < frequency[i]; j++) {
				result[resultIndex++] = currentChar;
			}
		}
		return new String(result, 0, resultIndex);
	}

	/**
	 * Similar solution, uses a StringBuilder to construct the result string. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param repeatLimit
	 * @return
	 */
	public static String repeatLimitedString2(String s, int repeatLimit) {
		char[] chars = s.toCharArray();
		int[] frequency = new int[26];
		for (int i = 0; i < chars.length; i++) {
			frequency[chars[i] - 'a']++;
		}
		StringBuilder builder = new StringBuilder();
		int lastIndex = frequency.length - 1;
		int nextIndex = lastIndex;
		for (int i = lastIndex; i >= 0; i--) {
			char currentChar = (char) (i + 'a');
			while (frequency[i] > repeatLimit) {
				for (int j = 0; j < repeatLimit; j++) {
					builder.append(currentChar);
				}
				frequency[i] -= repeatLimit;
				int j = Math.min(i, nextIndex);
				while (--j >= 0 && frequency[j] == 0)
					;
				if (j < 0) {
					return builder.toString();
				}
				builder.append((char) (j + 'a'));
				frequency[j]--;
				nextIndex = j + 1;
			}
			for (int j = 0; j < frequency[i]; j++) {
				builder.append(currentChar);
			}
		}
		return builder.toString();
	}

	/**
	 * Similar solution, has slightly different loop logic. Time complexity is O(n)
	 * where n is the length of string s.
	 * 
	 * @param s
	 * @param repeatLimit
	 * @return
	 */
	public static String repeatLimitedString3(String s, int repeatLimit) {
		char[] chars = s.toCharArray();
		int[] frequency = new int[26];
		for (int i = 0; i < chars.length; i++) {
			frequency[chars[i] - 'a']++;
		}
		StringBuilder builder = new StringBuilder();
		int lastIndex = frequency.length - 1;
		int nextIndex = lastIndex;
		for (int i = lastIndex; i >= 0; i--) {
			char currentChar = (char) (i + 'a');
			while (frequency[i] > 0) {
				int consecutive = Math.min(frequency[i], repeatLimit);
				while (consecutive-- > 0) {
					builder.append(currentChar);
					frequency[i]--;
				}
				if (frequency[i] > 0) {
					int j = Math.min(i, nextIndex);
					while (--j >= 0) {
						if (frequency[j] > 0) {
							builder.append((char) (j + 'a'));
							frequency[j]--;
							nextIndex = j + 1;
							break;
						}
					}
					if (j < 0) {
						return builder.toString();
					}
				}
			}
		}
		return builder.toString();
	}

	private static void check(String s, int repeatLimit, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		System.out.println("repeatLimit is: " + repeatLimit);
		String repeatLimitedString = repeatLimitedString(s, repeatLimit); // Calls your implementation
		System.out.println("repeatLimitedString is: " + repeatLimitedString);
	}
}
