package leetcode.hashmap;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

	public static void main(String[] args) {
		check(19, true);
		check(2, false);
	}

	public static boolean isHappy(int n) {
		Set<Integer> prevValues = new HashSet<>();
		do {
			int sum = 0;
			do {
				int digit;
				sum += (digit = n % 10) * (digit);
				n = n / 10;
			} while (n > 0);
			n = sum;
		} while (prevValues.add(n) && n != 1);
		return n == 1;
	}

	private static void check(int n, boolean expectedResult) {
		System.out.println("n is: " + n);
		System.out.println("expectedResult is: " + expectedResult);
		boolean isHappy = isHappy(n); // Calls your implementation
		System.out.println("isHappy is: " + isHappy);
	}
}
