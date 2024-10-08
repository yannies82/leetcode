package leetcode.arraystring;

public class MinimumNumberOfSwapsToMakeStringBalanced {

	public static void main(String[] args) {
		check("][][", 1);
		check("]]][[[", 2);
		check("[]", 0);
		check("[[[]]]][][]][[]]][[[", 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced.
	 * This solution counts the number of closing brackets on the first half of the
	 * string and the number of opening brackets on the second half of the string.
	 * Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int minSwaps(String s) {
		char[] chars = s.toCharArray();
		int limit = chars.length / 2;
		int openCount = 0;
		int swapCount = 0;
		// search for closing brackets on the first half, increase swapCount
		// if they are more than the opening brackets
		for (int i = 0; i < limit; i++) {
			if (chars[i] == '[') {
				openCount++;
			} else if (chars[i] == ']') {
				if (openCount == 0) {
					// number of closing brackets exceeded number of opening ones
					// do a swap
					swapCount++;
					openCount = 1;
				} else {
					openCount--;
				}
			}
		}
		// initialize closedCount to be 2*swapCount to take into considerations
		// the swaps that occured in the first half
		int closedCount = swapCount << 1;
		// search for opening brackets on the second half, increase swapCount
		// if they are more than the closing brackets
		for (int i = chars.length - 1; i >= limit; i--) {
			if (chars[i] == ']') {
				closedCount++;
			} else if (chars[i] == '[') {
				if (closedCount == 0) {
					// number of opening brackets exceeded number of closing ones
					// do a swap
					swapCount++;
					closedCount = 1;
				} else {
					closedCount--;
				}
			}
		}
		return swapCount;
	}

	/**
	 * Alternate solution. Time complexity is O(n) where n is the length of string
	 * s.
	 * 
	 * @param s
	 * @return
	 */
	public static int minSwaps2(String s) {
		char[] chars = s.toCharArray();
		int count = 0;
		int max = 0;
		// find the max number of closing brackets that have no opening bracket before
		// them
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == ']') {
				count++;
				if (count > max) {
					max = count;
				}
			} else {
				count--;
			}
		}
		// since swapping decreases excess number of closing brackets by 2, we need as
		// many swaps as half of max + 1
		return (max + 1) / 2;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int minSwaps = minSwaps(s); // Calls your implementation
		System.out.println("minSwaps is: " + minSwaps);
	}
}
