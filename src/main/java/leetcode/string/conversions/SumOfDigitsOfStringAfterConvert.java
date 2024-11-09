package leetcode.string.conversions;

public class SumOfDigitsOfStringAfterConvert {

	public static void main(String[] args) {
		check("iiii", 1, 36);
		check("leetcode", 2, 6);
		check("zbax", 2, 8);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/sum-of-digits-of-string-after-convert. Time
	 * complexity is O(klogm) where m is the length of string s.
	 * 
	 * @param chalk
	 * @param k
	 * @return
	 */
	public static int getLucky(String s, int k) {
		char[] chars = s.toCharArray();
		int sum = 0;
		char prevA = 'a' - 1;
		for (int i = 0; i < chars.length; i++) {
			int num = chars[i] - prevA;
			sum += num / 10 + num % 10;
		}
		while (--k > 0) {
			int prevSum = sum;
			sum = 0;
			do {
				sum += prevSum % 10;
			} while ((prevSum = prevSum / 10) > 0);
		}
		return sum;
	}

	private static void check(String s, int k, int expected) {
		System.out.println("s is: " + s);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int getLucky = getLucky(s, k); // Calls your implementation
		System.out.println("getLucky is: " + getLucky);
	}
}
