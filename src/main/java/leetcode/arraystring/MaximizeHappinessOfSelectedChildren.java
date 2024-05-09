package leetcode.arraystring;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximizeHappinessOfSelectedChildren {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3 }, 2, 4);
		check(new int[] { 1, 1, 1, 1 }, 2, 1);
		check(new int[] { 2, 3, 4, 5 }, 1, 5);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximize-happiness-of-selected-children. This
	 * solution sorts the input array then calculates the sum of the max k elements
	 * after subtracting the appropriate offset from each one of them. Time
	 * complexity is O(nlogn) where n is the length of the happiness array.
	 * 
	 * 
	 * @param happiness
	 * @param k
	 * @return
	 */
	public static long maximumHappinessSum(int[] happiness, int k) {
		Arrays.sort(happiness);
		int lastIndex = happiness.length - 1;
		int limit = lastIndex - k;
		int offset = 0;
		long sum = 0;
		for (int i = lastIndex; i > limit; i--) {
			int current = happiness[i] - offset;
			if (current <= 0) {
				// no point in proceeding to the next elements if this one <= 0
				// since the next ones are sure to be <= 0 too
				break;
			}
			sum += current;
			offset++;
		}
		return sum;
	}

	/**
	 * This solution uses a priority queue to keep the max k elements at all times.
	 * It then returns these elements after subtracting the appropriate offset from
	 * each one of them. Time complexity is O(nlogn) where n is the length of the
	 * happiness array.
	 * 
	 * @param happiness
	 * @param k
	 * @return
	 */
	public static long maximumHappinessSum2(int[] happiness, int k) {
		// add first element to queue
		Queue<Integer> queue = new PriorityQueue<>();
		queue.offer(happiness[0]);
		int min = happiness[0];
		// add elements to the priority queue, keep the max k elements
		for (int i = 1; i < happiness.length; i++) {
			if (queue.size() < k) {
				queue.offer(happiness[i]);
				min = queue.peek();
			} else if (happiness[i] > min) {
				queue.poll();
				queue.offer(happiness[i]);
				min = queue.peek();
			}
		}
		// calculate the sum of the max k elements after subtracting the appropriate
		// offset from each one
		int offset = k - 1;
		long sum = 0;
		while (!queue.isEmpty()) {
			int current = queue.poll() - offset;
			sum += current - (current >>> 31) * current;
			offset--;
		}
		return sum;
	}

	private static void check(int[] happiness, int k, long expected) {
		System.out.println("happiness is: " + Arrays.toString(happiness));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		long maximumHappinessSum = maximumHappinessSum(happiness, k); // Calls your implementation
		System.out.println("maximumHappinessSum is: " + maximumHappinessSum);
	}
}
