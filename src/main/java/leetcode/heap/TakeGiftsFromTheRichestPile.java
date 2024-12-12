package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class TakeGiftsFromTheRichestPile {

	public static void main(String[] args) {
		int[] nums0 = { 25, 64, 9, 4, 100 };
		check(nums0, 4, 29);
		int[] nums1 = { 1, 1, 1, 1 };
		check(nums1, 4, 4);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/take-gifts-from-the-richest-pile. This solution
	 * uses a priority queue in order to select the largest number at each step.
	 * Time complexity is O((n+k)logn) where n is the length of the gifts array.
	 * 
	 * @param gifts
	 * @param k
	 * @return
	 */
	public static long pickGifts(int[] gifts, int k) {
		Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
		long sum = 0;
		for (int i = 0; i < gifts.length; i++) {
			sum += gifts[i];
			queue.offer(gifts[i]);
		}
		for (int i = 0; i < k; i++) {
			int current = queue.poll();
			int root = (int) Math.floor(Math.sqrt(current));
			queue.offer(root);
			sum += root - current;
		}
		return sum;
	}

	private static void check(int[] gifts, int k, int expected) {
		System.out.println("gifts is: " + Arrays.toString(gifts));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		long pickGifts = pickGifts(gifts, k); // Calls your implementation
		System.out.println("pickGifts is: " + pickGifts);
	}
}
