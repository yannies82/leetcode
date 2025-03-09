package leetcode.slidingwindow;

import java.util.Arrays;

public class AlternatingGroups2 {

	public static void main(String[] args) {
		check(new int[] { 0, 1, 0, 1, 0 }, 3, 3);
		check(new int[] { 0, 1, 0, 0, 1, 0, 1 }, 6, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/alternating-groups-ii. Time
	 * complexity is O(n+k) where n is the length of the colors array.
	 * 
	 * @param colors
	 * @param k
	 * @return
	 */
	public static int numberOfAlternatingGroups(int[] colors, int k) {
		// create an augmented colors array and append the first k - 1 values of colors
		int[] augmentedColors = new int[colors.length + k - 1];
		System.arraycopy(colors, 0, augmentedColors, 0, colors.length);
		System.arraycopy(colors, 0, augmentedColors, colors.length, k - 1);
		int result = 0;
		int alternatingLength = 0;
		int prev = 1 - augmentedColors[0];
		int limit = augmentedColors.length;
		for (int i = 0; i < limit; i++) {
			if (augmentedColors[i] == prev) {
				// number is same as previous one, reset alternatingLength
				alternatingLength = 1;
			} else {
				alternatingLength++;
				// increase result if alternatingLength >= k
				if (alternatingLength >= k) {
					result++;
				}
			}
			prev = augmentedColors[i];
		}
		return result;
	}

	/**
	 * Same solution but branchless, using bit manipulation. Time complexity is
	 * O(n+k) where n is the length of the colors array.
	 * 
	 * @param colors
	 * @param k
	 * @return
	 */
	public static int numberOfAlternatingGroups2(int[] colors, int k) {
		// create an augmented colors array and append the first k - 1 values of colors
		int kMinus1 = k - 1;
		int[] augmentedColors = new int[colors.length + kMinus1];
		System.arraycopy(colors, 0, augmentedColors, 0, colors.length);
		System.arraycopy(colors, 0, augmentedColors, colors.length, k - 1);
		int result = 0;
		int alternatingLength = 0;
		int prev = 1 - augmentedColors[0];
		int limit = augmentedColors.length;
		for (int i = 0; i < limit; i++) {
			alternatingLength = ((augmentedColors[i] - prev) & 1) * alternatingLength + 1;
			result += ((kMinus1 - alternatingLength) >>> 31) & 1;
			prev = augmentedColors[i];
		}
		return result;
	}

	private static void check(int[] colors, int k, int expected) {
		System.out.println("blocks is: " + Arrays.toString(colors));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int numberOfAlternatingGroups = numberOfAlternatingGroups(colors, k); // Calls your implementation
		System.out.println("numberOfAlternatingGroups is: " + numberOfAlternatingGroups);
	}
}
