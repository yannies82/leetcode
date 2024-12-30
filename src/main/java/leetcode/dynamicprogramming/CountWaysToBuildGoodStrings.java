package leetcode.dynamicprogramming;

public class CountWaysToBuildGoodStrings {

	public static void main(String[] args) {
		check(3, 3, 1, 1, 8);
		check(2, 3, 1, 2, 5);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-ways-to-build-good-strings. This solution
	 * uses bottom up dynamic programming to calculate the ways that good strings
	 * with i characters can be built and adds up the ways from low up to high to
	 * calculate the final result. Time complexity is O(high - max + (max -
	 * min)/min) where max and min are decided between the values of one and zero.
	 * 
	 * @param low
	 * @param high
	 * @param zero
	 * @param one
	 * @return
	 */
	public static int countGoodStrings(int low, int high, int zero, int one) {
		// keeps the ways to build a string with i characters
		int[] dpArray = new int[high + 1];
		// there is only 1 way to build an empty string
		dpArray[0] = 1;
		int min = Math.min(zero, one);
		int max = Math.max(zero, one);
		// it is only possible to build exactly 1 string with length between min and max
		// if i is a multiple of min
		for (int i = min; i < max; i += min) {
			dpArray[i] = 1;
		}
		// between max and low it is possible to count the ways to build a string
		// by using dynamic programming
		for (int i = max; i < low; i++) {
			dpArray[i] = (dpArray[i - min] + dpArray[i - max]) % 1000000007;
		}
		int result = 0;
		// between low and high it is possible to count the ways to build a string
		// by using dynamic programming, also use this loop to accumulate the result
		for (int i = low; i <= high; i++) {
			dpArray[i] = (dpArray[i - min] + dpArray[i - max]) % 1000000007;
			result = (result + dpArray[i]) % 1000000007;
		}
		return result;
	}

	private static void check(int low, int high, int zero, int one, int expected) {
		System.out.println("low is: " + low);
		System.out.println("high is: " + high);
		System.out.println("zero is: " + zero);
		System.out.println("one is: " + one);
		System.out.println("expected is: " + expected);
		int countGoodStrings = countGoodStrings(low, high, zero, one); // Calls your implementation
		System.out.println("countGoodStrings is: " + countGoodStrings);
	}
}
