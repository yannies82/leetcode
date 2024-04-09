package leetcode.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class IPO {

	public static void main(String[] args) {
		check(2, 0, new int[] { 1, 2, 3 }, new int[] { 0, 1, 1 }, 4);
		check(3, 0, new int[] { 1, 2, 3 }, new int[] { 0, 1, 2 }, 6);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/ipo. This solution uses a
	 * priority queue to keep the eligible profits. Every time that a project is
	 * completed, the capital is increased and the new eligible profits are added to
	 * the queue until all projects are completed. Time complexity is O(k * n *
	 * logn) where n is the profits array length.
	 * 
	 * @param k
	 * @param w
	 * @param profits
	 * @param capital
	 * @return
	 */
	public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
		// this is the profits queue, keeps all eligible profits
		PriorityQueue<Integer> profitsQueue = new PriorityQueue<>(Comparator.reverseOrder());
		// this set keeps the indexes all profits which are not eligible yet due to the
		// required capital
		Set<Integer> leftoverIndexes = new LinkedHashSet<>();
		// iterate all profits and either add them to the queue or to the
		// leftoverIndexes
		// according to their required capital
		for (int i = 0; i < profits.length; i++) {
			if (capital[i] <= w) {
				// this project is within our capital budget
				profitsQueue.add(profits[i]);
			} else {
				// this project is out of our capital budget, add index to leftoverIndexes
				leftoverIndexes.add(i);
			}
		}
		int currentCapital = w;
		// iterate until all projects are completed or until no projects are eligible
		for (int i = 0; i < k && !profitsQueue.isEmpty(); i++) {
			int newCapital = currentCapital + profitsQueue.poll();
			// capital has increased, check leftover projects and add eligible ones to the
			// queue
			for (Iterator<Integer> iter = leftoverIndexes.iterator(); iter.hasNext();) {
				int index = iter.next();
				if (capital[index] <= newCapital) {
					// project is eligible, add to queue
					profitsQueue.add(profits[index]);
					// remove project from leftoverIndexes for efficiency
					iter.remove();
				}
			}
			// update currentCapital
			currentCapital = newCapital;
		}
		return currentCapital;
	}

	private static void check(int k, int w, int[] profits, int[] capital, int expected) {
		System.out.println("k is: " + k);
		System.out.println("w is: " + w);
		System.out.println("profits is: " + Arrays.toString(profits));
		System.out.println("capital is: " + Arrays.toString(capital));
		System.out.println("expected is: " + expected);
		int findMaximizedCapital = findMaximizedCapital(k, w, profits, capital); // Calls your implementation
		System.out.println("findMaximizedCapital is: " + findMaximizedCapital);
	}
}
