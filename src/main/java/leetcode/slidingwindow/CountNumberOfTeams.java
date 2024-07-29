package leetcode.slidingwindow;

import java.util.Arrays;

public class CountNumberOfTeams {

	public static void main(String[] args) {
		int[] nums0 = { 2, 5, 3, 4, 1 };
		check(nums0, 3);
		int[] nums1 = { 2, 1, 3 };
		check(nums1, 0);
		int[] nums2 = { 1, 2, 3, 4 };
		check(nums2, 4);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/count-number-of-teams. This
	 * solution selects the middle element, then counts the number of valid
	 * combinations between elements on its left and elements on its right. Time
	 * complexity is O(n^2) where n is the length of the rating array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int numTeams(int[] rating) {
		int n = rating.length;
		int nMinus1 = n - 1;
		int leftGreater = 0;
		int leftSmaller = 0;
		int rightGreater = 0;
		int rightSmaller = 0;
		int total = 0;
		// use all numbers as middle ones except for the first and last number
		for (int i = 1; i < nMinus1; i++) {
			// check how many numbers on the left of the middle one are greater and how many
			// are smaller
			for (int j = 0; j < i; j++) {
				if (rating[j] > rating[i]) {
					leftGreater++;
				} else {
					leftSmaller++;
				}
			}
			// check how many numbers on the right of the middle one are greater and how
			// many are smaller
			for (int j = i + 1; j < n; j++) {
				if (rating[j] > rating[i]) {
					rightGreater++;
				} else {
					rightSmaller++;
				}
			}
			// add the combinations to the total count and reset counters
			total += (leftGreater * rightSmaller) + (leftSmaller * rightGreater);
			leftSmaller = leftGreater = rightSmaller = rightGreater = 0;
		}
		return total;
	}

	/**
	 * Simple solution which uses 3 nested loops to traverse all numbers. Time
	 * complexity is O(n^3) where n is the length of the rating array.
	 * 
	 * @param rating
	 * @return
	 */
	public int numTeams2(int[] rating) {
		int n = rating.length;
		int nMinus1 = n - 1;
		int nMinus2 = n - 2;
		int count = 0;
		for (int i = 0; i < nMinus2; i++) {
			for (int j = i + 1; j < nMinus1; j++) {
				boolean iLessThanJ = rating[i] < rating[j];
				for (int k = j + 1; k < n; k++) {
					if (iLessThanJ ^ rating[j] > rating[k]) {
						count++;
					}
				}
			}
		}
		return count;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int numTeams = numTeams(nums); // Calls your implementation
		System.out.println("numTeams is: " + numTeams);
	}
}
