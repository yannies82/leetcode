package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximalScoreAfterApplyingKOperations {

	public static void main(String[] args) {
		check(new int[] { 10, 10, 10, 10, 10 }, 5, 50L);
		check(new int[] { 1, 10, 3, 3, 3 }, 3, 17L);
		check(new int[] { 756902131, 995414896, 95906472, 149914376, 387433380, 848985151 }, 6, 3603535575L);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximal-score-after-applying-k-operations. This
	 * solution uses a heap to always offer the element with the greatest value.
	 * Time complexity is O((n+k)logn) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static long maxKelements(int[] nums, int k) {
		Queue<Integer> queue = new PriorityQueue<>((a, b) -> b.compareTo(a));
		for (int i = 0; i < nums.length; i++) {
			queue.offer(nums[i]);
		}
		long sum = 0;
		for (int i = 0; i < k; i++) {
			int next = queue.poll();
			sum += next;
			queue.offer(next / 3 + (next % 3 == 0 ? 0 : 1));
		}
		return sum;
	}

	private static void check(int[] nums, int k, long expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		long maxKelements = maxKelements(nums, k); // Calls your implementation
		System.out.println("maxKelements is: " + maxKelements);
	}
}
