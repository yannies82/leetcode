package leetcode.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class FindKPairsWithSmallestSum {

	public static void main(String[] args) {
		check(new int[] { 1, 7, 11 }, new int[] { 2, 4, 6 }, 3, List.of(List.of(1, 2), List.of(1, 4), List.of(1, 6)));
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums. Improved
	 * version of the priority queue solution which eliminates the need for a set to
	 * prevent duplication and uses a traversal indicator for each pair instead.
	 * Time complexity is O(k * logk).
	 * 
	 * @param nums1
	 * @param nums2
	 * @param k
	 * @return
	 */
	public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		// stores the results
		List<List<Integer>> results = new ArrayList<>();
		// this priority queue keeps the pairs ordered by their sum
		// each int[] array has a length of 4 (sum, index of nums1, index of nums2,
		// traversalIndicator)
		// the traversal indicator is a flag which is used to avoid pair duplication
		// without using a set
		PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		// add first pair to the queue and set
		priorityQueue.add(new int[] { nums1[0] + nums2[0], 0, 0, 1 });
		// iterate until k pairs have been returned
		// since k <= nums1.length * nums2.length the queue is not expected to be empty
		while (k-- > 0) {
			// remove the element with the smallest sum from the queue
			// and add it to the results
			int[] currentMin = priorityQueue.poll();
			int i = currentMin[1];
			int j = currentMin[2];
			results.add(List.of(nums1[i], nums2[j]));
			// since both arrays are sorted, the next smallest pairs are
			// i + 1, j or i, j + 1
			// add both to the queue if they have not already been added
			if (currentMin[3] == 1) {
				// the last element in the array is the traversal indicator
				// it is set to 1 for the i+1 next pair and to 0 for the j+1 next pair
				// we only check the i+1 next pair if the previous element's traversal indicator
				// is 1
				// this way we avoid duplication of pairs without using a set
				int nextIndexNums1 = i + 1;
				if (nextIndexNums1 < nums1.length) {
					priorityQueue.add(new int[] { nums1[nextIndexNums1] + nums2[j], nextIndexNums1, j, 1 });
				}
			}
			int nextIndexNums2 = j + 1;
			if (nextIndexNums2 < nums2.length) {
				priorityQueue.add(new int[] { nums1[i] + nums2[nextIndexNums2], i, nextIndexNums2, 0 });
			}
		}
		return results;
	}

	/**
	 * This solution uses a priority queue to store the minimum pairs. In each
	 * iteration the min pair is removed from the queue and placed in the results
	 * list and the two adjacent pairs are placed in the queue. Time complexity is
	 * O(k * logk).
	 * 
	 * @param nums1
	 * @param nums2
	 * @param k
	 * @return
	 */
	public static List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
		// stores the results
		List<List<Integer>> results = new ArrayList<>();
		// this priority queue keeps the pairs ordered by their sum
		// each int[] array has a length of 3 (sum, index of nums1, index of nums2)
		PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		// this set is used to mark traversed pairs so that they are not duplicated
		// the hash for each pair is calculated as i * nums2.length + j
		Set<Integer> traversed = new HashSet<>();
		// add first pair to the queue and set
		priorityQueue.add(new int[] { nums1[0] + nums2[0], 0, 0 });
		traversed.add(0);
		// iterate until k pairs have been returned
		// since k <= nums1.length * nums2.length the queue is not expected to be empty
		while (k-- > 0) {
			// remove the element with the smallest sum from the queue
			// and add it to the results
			int[] currentMin = priorityQueue.poll();
			int i = currentMin[1];
			int j = currentMin[2];
			results.add(List.of(nums1[i], nums2[j]));
			// since both arrays are sorted, the next smallest pairs are
			// i + 1, j or i, j + 1
			// add both to the queue if they have not already been added
			int nextIndexNums1 = i + 1;
			if (nextIndexNums1 < nums1.length && traversed.add(nextIndexNums1 * nums2.length + j)) {
				priorityQueue.add(new int[] { nums1[nextIndexNums1] + nums2[j], nextIndexNums1, j });
			}
			int nextIndexNums2 = j + 1;
			if (nextIndexNums2 < nums2.length && traversed.add(i * nums2.length + nextIndexNums2)) {
				priorityQueue.add(new int[] { nums1[i] + nums2[nextIndexNums2], i, nextIndexNums2 });
			}
		}
		return results;
	}

	/**
	 * This solution uses an array to store the traversed indexes in nums2 array for
	 * all indexes of nums1 array. Time complexity is O(k*n) where n is the length
	 * of the nums1 array.
	 * 
	 * @param nums1
	 * @param nums2
	 * @param k
	 * @return
	 */
	public static List<List<Integer>> kSmallestPairs3(int[] nums1, int[] nums2, int k) {
		// results are stored in this list
		List<List<Integer>> results = new ArrayList<>();
		// stores the current index for the num2 array
		// for each position of the num1 array
		int currentNum2Index[] = new int[nums1.length];
		do {
			int min = Integer.MAX_VALUE;
			int minIndex = 0;
			// in order to find the next pair check the sum of all nums1 numbers
			// and their respective nums2 numbers as defined by the stored indexes
			// in currentNum2Index array
			for (int i = 0; i < nums1.length; i++) {
				if (currentNum2Index[i] < nums2.length && nums1[i] + nums2[currentNum2Index[i]] < min) {
					// found new min, update index
					minIndex = i;
					// update min sum
					min = nums1[i] + nums2[currentNum2Index[i]];
				}
			}
			results.add(List.of(nums1[minIndex], nums2[currentNum2Index[minIndex]]));
			currentNum2Index[minIndex]++;
		} while (--k > 0);
		return results;
	}

	private static void check(int[] nums1, int[] nums2, int k, List<List<Integer>> expected) {
		System.out.println("nums1 is: " + Arrays.toString(nums1));
		System.out.println("nums2 is: " + Arrays.toString(nums2));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		List<List<Integer>> kSmallestPairs = kSmallestPairs(nums1, nums2, k); // Calls your implementation
		System.out.println("kSmallestPairs is: " + kSmallestPairs);
	}
}
