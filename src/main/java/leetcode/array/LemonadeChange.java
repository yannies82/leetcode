package leetcode.array;

import java.util.Arrays;

public class LemonadeChange {

	public static void main(String[] args) {
		check(new int[] { 5, 5, 5, 10, 20 }, true);
		check(new int[] { 5, 5, 10, 10, 20 }, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/lemonade-change. Time
	 * complexity is O(n) where n is the length of the bills array.
	 * 
	 * @param bills
	 * @return
	 */
	public static boolean lemonadeChange(int[] bills) {
		int fiveNotes = 0;
		int tenNotes = 0;
		for (int i = 0; i < bills.length; i++) {
			if (bills[i] == 5) {
				fiveNotes++;
			} else if (bills[i] == 10) {
				tenNotes++;
				if (--fiveNotes < 0) {
					return false;
				}
			} else {
				if (tenNotes == 0) {
					fiveNotes -= 2;
				} else {
					tenNotes--;
				}
				if (--fiveNotes < 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Alternate solution.
	 * 
	 * @param bills
	 * @return
	 */
	public static boolean lemonadeChange2(int[] bills) {
		int[] notes = new int[21];
		for (int i = 0; i < bills.length; i++) {
			notes[bills[i]]++;
			switch (bills[i]) {
			case 20:
				if (notes[10] == 0) {
					notes[5] -= 2;
				} else {
					notes[10]--;
				}
			case 10:
				// falls through
				if (--notes[5] < 0) {
					return false;
				}
				break;
			}
		}
		return true;
	}

	private static void check(int[] bills, boolean expected) {
		System.out.println("bills is: " + Arrays.toString(bills));
		System.out.println("expected is: " + expected);
		boolean lemonadeChange = lemonadeChange(bills); // Calls your implementation
		System.out.println("lemonadeChange is: " + lemonadeChange);
	}
}
