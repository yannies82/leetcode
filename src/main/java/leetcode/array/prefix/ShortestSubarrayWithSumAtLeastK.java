package leetcode.array.prefix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ShortestSubarrayWithSumAtLeastK {

	public static void main(String[] args) {
		check(new int[] { 1 }, 1, 1);
		check(new int[] { 1, 2 }, 4, -1);
		check(new int[] { 2, -1, 2 }, 3, 3);
		check(new int[] { 56, -21, 56, 35, -9 }, 61, 2);
		check(new int[] { 48, 99, 37, 4, -31 }, 140, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k. This
	 * solution uses an array as a deque to keep all sums along with the respective
	 * index. It compares each sum with all eligible sums from the head of the queue
	 * and removes all obsolete sums from the tail of the queue. Time complexity is
	 * O(n) where n is the length of the nums array.
	 * 
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int shortestSubarray(int[] nums, int k) {
		int result = Integer.MAX_VALUE;
		long sum = 0;
		// this custom deque keeps the current sum along with the respective index
		Entry[] queue = new Entry[nums.length + 1];
		queue[nums.length] = new Entry(0, -1);
		int tail = nums.length, head = nums.length;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			// check all eligible entries from the head of the queue
			while (head >= tail && sum - queue[head].sum >= k) {
				// the sum stored at this entry has a diff >= k from the current sum
				// remove it from the queue and update the result
				result = Math.min(result, i - queue[head--].index);
			}

			// remove all entries from the tail of the queue which are greater than sum,
			// since the current entry will offer a better solution (shorter array)
			while (head >= tail && queue[tail].sum > sum) {
				tail++;
			}
			queue[--tail] = new Entry(sum, i);
		}
		return result == Integer.MAX_VALUE ? -1 : result;
	}

	/**
	 * Similar solution using a standard ArrayDeque. Time complexity is O(n) where n
	 * is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int shortestSubarray2(int[] nums, int k) {
		int res = Integer.MAX_VALUE;
		long sum = 0;
		// this deque keeps the current sum along with the respective index
		Deque<Entry> queue = new ArrayDeque<>();
		queue.add(new Entry(0, -1));
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			// check all eligible entries from the head of the queue
			while (!queue.isEmpty() && sum - queue.peekFirst().sum >= k) {
				// the sum stored at this entry has a diff >= k from the current sum
				// remove it from the queue and update the result
				Entry front = queue.pollFirst();
				res = Math.min(res, i - front.index);
			}

			// remove all entries from the tail of the queue which are greater than sum,
			// since the current entry will offer a better solution (shorter array)
			while (!queue.isEmpty() && queue.peekLast().sum > sum) {
				queue.pollLast();
			}
			queue.offerLast(new Entry(sum, i));
		}

		return res == Integer.MAX_VALUE ? -1 : res;
	}

	private static record Entry(long sum, int index) {
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int shortestSubarray = shortestSubarray(nums, k); // Calls your implementation
		System.out.println("shortestSubarray is: " + shortestSubarray);
	}
}
