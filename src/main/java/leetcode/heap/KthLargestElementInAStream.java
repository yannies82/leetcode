package leetcode.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElementInAStream {

	public static void main(String[] args) {
		KthLargest kthLargest = new KthLargest(3, new int[] { 4, 5, 8, 2 });
		check(kthLargest, 3, 4);
		check(kthLargest, 5, 5);
		check(kthLargest, 10, 5);
		check(kthLargest, 9, 8);
		check(kthLargest, 4, 8);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/kth-largest-element-in-a-stream. This class
	 * implementation uses a priority queue internally which keeps the k greatest
	 * elements at all times. Time complexity for add method is O(logk).
	 * 
	 * @author yanni
	 *
	 */
	static class KthLargest {

		Queue<Integer> queue;
		int k;

		public KthLargest(int k, int[] nums) {
			this.k = k;
			this.queue = new PriorityQueue<>();
			for (int i = 0; i < nums.length; i++) {
				addInternal(nums[i]);
			}
		}

		public int add(int val) {
			addInternal(val);
			return queue.peek();
		}

		private void addInternal(int val) {
			if (queue.size() < this.k) {
				queue.offer(val);
			} else if (val > queue.peek()) {
				queue.poll();
				queue.offer(val);
			}
		}
	}

	private static void check(KthLargest kthLargest, int numToAdd, int expected) {
		System.out.println("numToAdd is: " + numToAdd);
		System.out.println("expected is: " + expected);
		int result = kthLargest.add(numToAdd); // Calls your implementation
		System.out.println("result is: " + result);
	}
}
