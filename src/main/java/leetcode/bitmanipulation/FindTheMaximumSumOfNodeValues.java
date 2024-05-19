package leetcode.bitmanipulation;

import java.util.Arrays;

public class FindTheMaximumSumOfNodeValues {

	public static void main(String[] args) {
		check(new int[] { 67, 13, 79, 13, 75, 11, 0, 41, 94 }, 7,
				new int[][] { { 0, 1 }, { 3, 7 }, { 4, 7 }, { 6, 5 }, { 6, 0 }, { 0, 2 }, { 7, 2 }, { 7, 8 } }, 407);
		check(new int[] { 1, 2, 1 }, 3, new int[][] { { 0, 1 }, { 0, 2 } }, 6);
		check(new int[] { 2, 3 }, 7, new int[][] { { 0, 1 } }, 9);
		check(new int[] { 7, 7, 7, 7, 7, 7 }, 3, new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 } }, 42);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-maximum-sum-of-node-values. This
	 * solution takes into advantage of the specific conditions of this problem in
	 * order to completely disregard the edges array. The problem states that the
	 * tree is valid, therefore all nodes are connected. Since all nodes are
	 * connected then we can directly apply xor to any two nodes since the
	 * intermediate ones will be eliminated by twice applying xor. For example if
	 * there are edges [0, 1] and [0,2] then we can directly apply xor to nodes 1
	 * and 2 since that would be equivalent to nums[0] ^ nums[1] ^ nums[0] ^ nums[2]
	 * = nums[1] ^ nums[2]. The solution applies xor to all node values, then keeps
	 * track of how many nodes have smaller values than their xor with k. Time
	 * complexity is O(n) where n is the length of the nums array.
	 * 
	 * 
	 * @param nums
	 * @param k
	 * @param edges
	 * @return
	 */
	public static long maximumValueSum(int[] nums, int k, int[][] edges) {
		// keeps the min difference between a num and its xor with k when the num is
		// greater or equal
		int minPositiveDiff = Integer.MAX_VALUE;
		// keeps the min difference between a num and its xor with k when the num is
		// less
		int minNegativeDiff = Integer.MAX_VALUE;
		// keeps the count of numbers with value less than their xor with k
		int negativeCount = 0;
		// keeps the total sum to return
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			// adds the num to the total sum
			sum += nums[i];
			// calculate xor with k and the diff with num
			int xor = nums[i] ^ k;
			int diff = nums[i] - xor;
			if (diff < 0) {
				// num is less than its xor with k, increase count
				negativeCount++;
				// add the difference to the sum (so that we effectively add the xor value
				// instead of the num)
				sum += -diff;
				// update the min negative diff
				minNegativeDiff = Math.min(minNegativeDiff, -diff);
			} else {
				// num is greater than or equal to its xor with k
				// update the min positive diff
				minPositiveDiff = Math.min(minPositiveDiff, diff);
			}
		}
		if (negativeCount % 2 == 1) {
			// there is an odd count of nums less than their xor with k
			// since xor is applied in pairs we should check if we should
			// apply the last xor with a num greater than its xor with k
			// therefore we should subtract from the sum the min value between
			// minNegativeDiff and minPositiveDiff
			// subtracting minNegativeDiff means not applying the last xor
			// subtracting minPositiveDiff means applying the last xor
			sum -= Math.min(minNegativeDiff, minPositiveDiff);
		}
		return sum;
	}

	private static void check(int[] nums, int k, int[][] edges, long expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("edges is: ");
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("expected is: " + expected);
		long maximumValueSum = maximumValueSum(nums, k, edges); // Calls your implementation
		System.out.println("maximumValueSum is: " + maximumValueSum);
	}
}
