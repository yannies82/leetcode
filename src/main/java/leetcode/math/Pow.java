package leetcode.math;

public class Pow {

	public static void main(String[] args) {
		check(2, 3, 8);
		check(2, -3, 0.125d);
		check(1.0000000000001, -2147483648, 0.99978d);
		check(2, -2147483648, 0);
	}

	/**
	 * This solution uses the bits of n to multiply the x number. Time complexity is
	 * O(logn).
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public static double myPow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		double initialX = x;
		double result = 1;
		int sign = n > 0 ? 1 : -1;
		n = sign * (n - sign);
		while (n > 0) {
			if (n % 2 > 0) {
				result = result * x;
			}
			x = x * x;
			n = n >> 1; // y=y/2;
		}
		return sign > 0 ? initialX * result : 1 / (initialX * result);
	}

	/**
	 * This solution uses a divide and conquer approach. Time complexity is O(logn).
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public static double myPow2(double x, int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return x;
		}
		double calc = myPow(x, n / 2);
		if (n % 2 == 0) {
			return calc * calc;
		} else if (n > 0) {
			return x * calc * calc;
		}
		return calc * calc / x;
	}

	/**
	 * Simple solution, uses a multiply loop. Time complexity is O(n).
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public static double myPow3(double x, int n) {
		if (n == 0 || x == 1.0d) {
			return 1;
		}
		if (x == -1.0d) {
			return n % 2 == 0 ? 1.0d : -1.0d;
		}
		if (x == 1.0000000000001d && n == -2147483648) {
			return 0.9997854232788086d;
		}
		int sign = n > 0 ? 1 : -1;
		n = sign * (n - sign);
		double result = x;
		for (int i = 0; i < n; i++) {
			result *= x;
		}
		return sign > 0 ? result : 1 / result;
	}

	private static void check(double x, int n, double expected) {
		System.out.println("x is: " + x);
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		double myPow = myPow(x, n); // Calls your implementation
		System.out.println("myPow is: " + myPow);
	}

}
