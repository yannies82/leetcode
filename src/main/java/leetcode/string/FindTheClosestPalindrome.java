package leetcode.string;

public class FindTheClosestPalindrome {

	public static void main(String[] args) {
		check("123", "121");
		check("1", "0");
		check("11", "9");
		check("2000", "2002");
		check("2002", "1991");
		check("1000", "999");
		check("999", "1001");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-the-closest-palindrome.
	 * Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param n
	 * @return
	 */
	public static String nearestPalindromic(String n) {
		char[] chars = n.toCharArray();
		// get the string value as a long number
		long number = getLeftHalfValue(chars, chars.length);
		if (number <= 10) {
			// early exit in case of numbers 1 - 10
			return Long.toString(number - 1);
		}
		int limit = (chars.length + 1) >> 1;
		// the palindromes will be generated based on the higher order digit values
		// because we want them to remain as close to the original number value as
		// possible
		long leftHalfValue = getLeftHalfValue(chars, limit);
		long nextPalindromeHalfValue = leftHalfValue + 1;
		long prevPalindromeHalfValue = leftHalfValue - 1;
		// the left halfs will be multiplied with this factor before we add the right
		// halfs to them
		long multFactor = (long) Math.pow(10, chars.length - limit);
		// if the string has an odd number of digits then divFactor is 10, else 1
		// it is used to divide the leftHalfValue for generating the palindromic right
		// half
		int divFactor = (int) Math.pow(10, chars.length % 2);
		// we will generate 5 candidate palindrome numbers in total
		// these 5 candidates cover all possible cases for the min palindrome candidate
		long[] candidates = new long[5];
		// first candidate is the palindrome based on the leftHalfValue
		// covers cases such as 12344 -> 12321
		candidates[0] = leftHalfValue * multFactor + calculateRightHalfFromLeftHalf(leftHalfValue / divFactor);
		// second candidate is the palindrome based on (leftHalfValue + 1)
		// covers cases such as 12399 -> 12421
		candidates[1] = nextPalindromeHalfValue * multFactor
				+ calculateRightHalfFromLeftHalf(nextPalindromeHalfValue / divFactor);
		// third candidate is the palindrome based on (leftHalfValue - 1)
		// covers cases such as 58300 -> 58285
		candidates[2] = prevPalindromeHalfValue * multFactor
				+ calculateRightHalfFromLeftHalf(prevPalindromeHalfValue / divFactor);
		// fourth candidate is the next power of 10 (greater than the current number)
		// plus 1
		// covers cases such as 999 -> 1001
		candidates[3] = (long) Math.pow(10, chars.length) + 1;
		// fifth candidate is the previous power of 10 (less than the current number)
		// minus 1
		// covers cases such as 1000 -> 999
		candidates[4] = (long) Math.pow(10, chars.length - 1) - 1;
		// compare candidates and keep the one closest to the initial number
		long closestPalindrome = 0;
		long minDiff = Long.MAX_VALUE;
		for (int i = 0; i < candidates.length; i++) {
			if (candidates[i] != number) {
				// only take palindrome into account if it is different than the initial number
				long diff = Math.abs(candidates[i] - number);
				if (diff < minDiff || (diff == minDiff && candidates[i] < closestPalindrome)) {
					// update the min palindrome if the current diff is less than minDiff
					// or if it is equal and the current palindrome is less than the
					// closestPalindrome
					closestPalindrome = candidates[i];
					minDiff = diff;
				}
			}
		}
		return Long.toString(closestPalindrome);
	}

	private static long calculateRightHalfFromLeftHalf(long leftHalf) {
		long result = 0;
		do {
			result = result * 10 + leftHalf % 10;
		} while ((leftHalf = leftHalf / 10) > 0);
		return result;
	}

	private static long getLeftHalfValue(char[] chars, int limit) {
		long result = 0;
		for (int i = 0; i < limit; i++) {
			result = result * 10 + chars[i] - '0';
		}
		return result;
	}

	private static void check(String n, String expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		String nearestPalindromic = nearestPalindromic(n); // Calls your implementation
		System.out.println("nearestPalindromic is: " + nearestPalindromic);
	}
}
