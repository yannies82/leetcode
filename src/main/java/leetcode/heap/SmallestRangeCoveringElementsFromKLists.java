package leetcode.heap;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SmallestRangeCoveringElementsFromKLists {

	public static void main(String[] args) {
		check(List.of(List.of(4, 10, 15, 24, 26), List.of(0, 9, 12, 20), List.of(5, 18, 22, 30)), new int[] { 20, 24 });
		check(List.of(List.of(1, 2, 3), List.of(1, 2, 3), List.of(1, 2, 3)), new int[] { 1, 1 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists.
	 * This solution adds the smallest elements from each list into a heap and
	 * calculates the max element and range. It then removes the smallest element
	 * from the heap and replaces it with the next element from the same list while
	 * updating the minRange and max element. This process continues until one of
	 * the lists is exhausted. Time complexity is O(max(k*n, klogk)) where k is the
	 * number of lists and n is thelength of each list.
	 * 
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] smallestRange(List<List<Integer>> nums) {
		int k = nums.size();
		// keeps the current index for every list
		int[] indexes = new int[k];
		// keeps one number from each list along with the list index, ordered ascending
		Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
		int max = -100000;
		// iterate all lists and add the smallest number from each list to the queue
		// also calculate the max of these k numbers
		for (int i = 0; i < k; i++) {
			int current = nums.get(i).get(0);
			if (current > max) {
				max = current;
			}
			queue.offer(new int[] { current, i });
		}
		// initialize the minRange
		int[] minRangeValues = { queue.peek()[0], max };
		int minRange = minRangeValues[1] - minRangeValues[0];
		// iterate until one of the lists is exhausted
		while (true) {
			// pop the min element from the heap
			int[] minElement = queue.poll();
			int min = minElement[0];
			int range = max - min;
			// compare the current range to the minRange and update minRange if necessary
			if (range < minRange) {
				// current range is less than minRange, update minRange
				minRangeValues[0] = min;
				minRangeValues[1] = max;
				minRange = max - min;
			}
			// find the list to which the min element belonged to, since we popped it from
			// the queue we should replace it by offering the next element of this list
			int listIndex = minElement[1];
			int nextListIndex = ++indexes[listIndex];
			List<Integer> targetList = nums.get(listIndex);
			if (minRange == 0 || nextListIndex == targetList.size()) {
				// exit if the minRange is already 0 or we have reached the end of the list
				return minRangeValues;
			}
			int nextNumber = targetList.get(nextListIndex);
			if (nextNumber > max) {
				// if the next number of the list is greater than max, update max
				max = nextNumber;
			}
			queue.offer(new int[] { nextNumber, listIndex });
		}
	}

	private static void check(List<List<Integer>> nums, int[] expected) {
		System.out.println("nums is: " + nums);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] smallestRange = smallestRange(nums); // Calls your implementation
		System.out.println("smallestRange is: " + Arrays.toString(smallestRange));
	}
}
