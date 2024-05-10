package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestPrimeFraction {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 5 }, 3, new int[] { 2, 5 });
		check(new int[] { 1, 7 }, 1, new int[] { 1, 7 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/k-th-smallest-prime-fraction.
	 * This solution uses a priority queue to keep the smallest fractions sorted,
	 * then removes the k-1 smallest fractions so that we are left with the answer
	 * to our problem. Time complexity is O(n*logn) where n is the length of the arr
	 * array.
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
		int lastIndex = arr.length - 1;
		if (k == 1) {
			// early exit
			return new int[] { arr[0], arr[lastIndex] };
		}
		// this priority queue keeps the fractions sorted by their values, along with
		// the element indexes
		Queue<Object[]> queue = new PriorityQueue<>((a, b) -> Double.compare((double) a[0], (double) b[0]));
		for (int i = 0; i <= lastIndex; i++) {
			// add all elements to the queue for i = 0 to n-1 and j = lastIndex
			queue.add(new Object[] { (double) arr[i] / arr[lastIndex], i, lastIndex });
		}
		// iterate k - 1 times
		while (--k > 0) {
			// remove the current smallest element from the queue, decrease j by
			// 1, then calculate the fraction and insert in the queue
			Object[] currentSmallest = queue.poll();
			int i = (int) currentSmallest[1];
			int nextJ = (int) currentSmallest[2] - 1;
			queue.add(new Object[] { (double) arr[i] / arr[nextJ], i, nextJ });
		}
		// the k - 1 smallest elements have been removed, therefore at the head
		// of the queue we can find the kth smallest element
		Object[] finalSmallest = queue.peek();
		int i = (int) finalSmallest[1];
		int j = (int) finalSmallest[2];
		return new int[] { arr[i], arr[j] };
	}

	private static void check(int[] arr, int k, int[] expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] kthSmallestPrimeFraction = kthSmallestPrimeFraction(arr, k); // Calls your implementation
		System.out.println("kthSmallestPrimeFraction is: " + Arrays.toString(kthSmallestPrimeFraction));
	}
}
