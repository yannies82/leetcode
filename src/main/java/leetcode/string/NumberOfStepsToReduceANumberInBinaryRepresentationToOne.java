package leetcode.string;

public class NumberOfStepsToReduceANumberInBinaryRepresentationToOne {

	public static void main(String[] args) {
		check("1101", 6);
		check("10", 1);
		check("1", 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one.
	 * This solution iterates the binary digits of the number from lower value to
	 * higher value and updates the result and carry. Time complexity is O(n) where
	 * n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int numSteps(String s) {
		char[] chars = s.toCharArray();
		int result = 0;
		int carry = 0;
		// iterate all digits from end to start
		for (int i = s.length() - 1; i > 0; i--) {
			if (chars[i] - '0' + carry == 1) {
				// if digit[i] + carry == 1 then an extra action should be performed
				// (addition with 1) and the carry will always be 1 from now on
				result++;
				carry = 1;
			}
			// an action will be performed for each character. increase counter
			result++;
		}
		return result + carry;
	}

	/**
	 * This solution uses a string builder in order to update the string after each
	 * operation. Time complexity is O(n^2) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int numSteps2(String s) {
		StringBuilder builder = new StringBuilder(s);
		int count = 0;

		// iterate until only 1 character ('1') is left in the string builder
		while (builder.length() > 1) {
			int lastCharIndex = builder.length() - 1;
			if (builder.charAt(lastCharIndex) == '0') {
				// if the last character is 0 delete it
				builder.deleteCharAt(lastCharIndex);
			} else {
				// if the last character is 1 perform addition with 1
				int index = lastCharIndex;
				while (index >= 0 && builder.charAt(index) == '1') {
					builder.setCharAt(index, '0');
					index--;
				}
				if (index == -1) {
					builder.insert(0, '1');
				} else {
					builder.setCharAt(index, '1');
				}
			}
			count++;
		}
		return count;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int numSteps = numSteps(s); // Calls your implementation
		System.out.println("numSteps is: " + numSteps);
	}
}
