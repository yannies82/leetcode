package leetcode.array;

import java.util.Arrays;

public class PatchingArray {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 31, 33 }, 2147483647, 28);
		check(new int[] { 1, 3 }, 6, 1);
		check(new int[] { 1, 5, 10 }, 20, 2);
		check(new int[] { 1, 2, 2 }, 5, 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/patching-array. This solution
	 * checks for the smallest missing number and updates it while iterating the
	 * nums array. Time complexity is O(m) where m is the length of the nums array.
	 * 
	 * @param nums
	 * @param n
	 * @return
	 */
	public static int minPatches(int[] nums, int n) {
		// initialize the missing number to the smallest possible value
		long missing = 1;
		int i = 0;
		int patchesCount = 0;
		// iterate all numbers until the missing number becomes greater than n
		while ((i < nums.length) && (missing <= n)) {
			if (nums[i] <= missing) {
				// nums[i] is less than or equal to the missing number
				// we could already build all numbers up to missing - 1 as sums
				// and now we can build all numbers up to missing + nums[i] - 1
				// therefore the missing number that we search should be updated
				// to missing + nums[i]
				missing += nums[i];
				i++;
			} else {
				// nums[i] is greater than the missing number, therefore we
				// should patch the missing number to the array and increase
				// the pathes count
				// since we could already build all numbers up to missing - 1 as sums, now
				// we are sure that we can build all numbers up to missing - 1 + missing
				// therefore the missing number that we search should be missing + missing
				missing += missing;
				patchesCount++;
			}
		}
		// if missing is still not greater than n while being greater than all elements
		// of nums, add all remaining patches until missing becomes greater than n
		while (missing <= n) {
			missing += missing;
			patchesCount++;
		}
		return patchesCount;
	}

	private static void check(int[] nums, int n, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int minPatches = minPatches(nums, n); // Calls your implementation
		System.out.println("minPatches is: " + minPatches);
	}
}
