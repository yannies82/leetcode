package leetcode.binarysearch;

import java.util.Arrays;

public class MinimumNumberOfBallsInABag {

	public static void main(String[] args) {
		check(new int[] { 9 }, 2, 3);
		check(new int[] { 9, 3 }, 2, 3);
		check(new int[] { 9, 4 }, 2, 4);
		check(new int[] { 9, 5 }, 2, 5);
		check(new int[] { 9, 6 }, 2, 5);
		check(new int[] { 2, 4, 8, 2 }, 4, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag. This solution
	 * performs binary search to find the minimum size of the bag which is
	 * achievable by performing at most maxOperations. Time complexity is O (nlogn)
	 * where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param maxOperations
	 * @return
	 */
	public static int minimumSize(int[] nums, int maxOperations) {
		// set start as the min size of the bag and end as the max size of the bag
		int start = 1;
		int end = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > end) {
				end = nums[i];
			}
		}
		// assume the max bag size and perform binary search to find the
		// smallest achievable size
		int result = end;
		while (start <= end) {
			int mid = (start + end) / 2;
			int operations = 0;
			for (int i = 0; i < nums.length && operations <= maxOperations; i++) {
				// this is the number of operations required for nums[i]
				// so that it is split into bags with size at most mid
				operations += (nums[i] - 1) / mid;
			}
			if (operations <= maxOperations) {
				result = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return result;
	}

	private static void check(int[] nums, int maxOperations, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("maxOperations is: " + maxOperations);
		System.out.println("expected is: " + expected);
		int minimumSize = minimumSize(nums, maxOperations); // Calls your implementation
		System.out.println("minimumSize is: " + minimumSize);
	}
}
