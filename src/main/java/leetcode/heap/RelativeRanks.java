package leetcode.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class RelativeRanks {

	public static void main(String[] args) {
		int[] nums0 = { 5, 4, 3, 2, 1 };
		String[] expected0 = { "Gold Medal", "Silver Medal", "Bronze Medal", "4", "5" };
		check(nums0, expected0);
		int[] nums1 = { 10, 3, 8, 9, 4 };
		String[] expected1 = { "Gold Medal", "5", "Bronze Medal", "Silver Medal", "4" };
		check(nums1, expected1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/relative-ranks. This solution
	 * uses a priority queue to sort the input scores and a map to map scores to
	 * array indexes. Time complexity is O(n*logn) where n is the length of the
	 * score array.
	 * 
	 * @param score
	 * @return
	 */
	public static String[] findRelativeRanks(int[] score) {
		// initialize priority queue and map, add scores to them
		Queue<Integer> scoresQueue = new PriorityQueue<>(Comparator.reverseOrder());
		Map<Integer, Integer> scoresMap = new HashMap<>();
		for (int i = 0; i < score.length; i++) {
			scoresQueue.offer(score[i]);
			scoresMap.put(score[i], i);
		}
		String[] result = new String[score.length];
		int rank = 1;
		// iterate the scores queue, increasing the rank every time
		while (!scoresQueue.isEmpty()) {
			// this is the input array index of the score with this rank
			int index = scoresMap.get(scoresQueue.poll());
			// assign a rank string to this score
			String rankString = switch (rank) {
			case 1 -> "Gold Medal";
			case 2 -> "Silver Medal";
			case 3 -> "Bronze Medal";
			default -> Integer.toString(rank);
			};
			result[index] = rankString;
			rank++;
		}
		return result;
	}

	private static void check(int[] score, String[] expected) {
		System.out.println("score is: " + Arrays.toString(score));
		System.out.println("expected is: " + Arrays.toString(expected));
		String[] findRelativeRanks = findRelativeRanks(score); // Calls your implementation
		System.out.println("findRelativeRanks is: " + Arrays.toString(findRelativeRanks));
	}
}
