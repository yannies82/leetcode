package leetcode.greedy;

public class TwoKeysKeyboard {

	public static void main(String[] args) {
		check(3, 3);
		check(1, 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/2-keys-keyboard. This
	 * solution greedily checks at each steps if the onScreen characters can be
	 * exactly divided by the remaining characters and performs copy or paste
	 * accordingly. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int minSteps(int n) {
		if (n == 1) {
			return 0;
		}
		// first two actions are always Copy, Paste
		int copySize = 1;
		int actions = 2;
		int onScreen = 2;
		while (onScreen < n) {
			int remaining = n - onScreen;
			if (remaining % onScreen == 0 && copySize != onScreen) {
				// the remaining characters can be exactly divided by the onScreen characters
				// therefore perform copy to increase the copied characters
				copySize = onScreen;
			} else {
				// the remaining characters cannot be exactly divided by the onScreen characters
				// perform paste because performing copy would make it impossible to reach
				// exactly n characters
				onScreen += copySize;
			}
			actions++;
		}
		return actions;
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int minSteps = minSteps(n); // Calls your implementation
		System.out.println("minSteps is: " + minSteps);
	}
}
