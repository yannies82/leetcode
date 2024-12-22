package leetcode.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindBuildingWhereAliceAndBobCanMeet {

	public static void main(String[] args) {
		check(new int[] { 6, 4, 8, 5, 2, 7 }, new int[][] { { 0, 1 }, { 0, 3 }, { 2, 4 }, { 3, 4 }, { 2, 2 } },
				new int[] { 2, 5, -1, 5, 2 });
		check(new int[] { 5, 3, 8, 2, 6, 1, 4, 6 }, new int[][] { { 0, 7 }, { 3, 5 }, { 5, 2 }, { 3, 0 }, { 1, 6 } },
				new int[] { 7, 6, -1, 4, 6 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet.
	 * This solution groups queries by max height index, then iterates all heights
	 * and keeps adding queries to a priority queue by ascending height. Time
	 * complexity is O(n+mlogm) where n is the length of the heights array and m is
	 * the length of the queries array.
	 * 
	 * @param heights
	 * @param queries
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
		// this array of lists keeps all queries grouped by height index i
		// the index i contains all queries which have i as their maxIndex
		// (and are seeking an index j > i with heights[j] > heights[minIndex])
		List<int[]>[] queriesPerHeightIndex = new ArrayList[heights.length];
		for (int i = 0; i < heights.length; i++) {
			queriesPerHeightIndex[i] = new ArrayList<>();
		}
		// this queue keeps queries sorted by height, along with their query index
		Queue<int[]> heightsQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		int[] result = new int[queries.length];
		Arrays.fill(result, -1);
		for (int i = 0; i < queries.length; i++) {
			int minIndex, maxIndex;
			if (queries[i][0] <= queries[i][1]) {
				minIndex = queries[i][0];
				maxIndex = queries[i][1];
			} else {
				minIndex = queries[i][1];
				maxIndex = queries[i][0];
			}
			if (minIndex == maxIndex || heights[minIndex] < heights[maxIndex]) {
				result[i] = maxIndex;
			} else {
				// heights[minIndex] is greater than or equal to heights[maxIndex]
				// we are looking for the next index j where j > maxIndex and heights[j] >
				// heights[minIndex]
				queriesPerHeightIndex[maxIndex].add(new int[] { heights[minIndex], i });
			}
		}
		// iterate all heights
		int[] firstElement = null;
		for (int i = 0; i < heights.length; i++) {
			// all queries in the queue with a height less than the current height
			// have height index i as their solution
			while (firstElement != null && firstElement[0] < heights[i]) {
				result[heightsQueue.poll()[1]] = i;
				firstElement = heightsQueue.peek();
			}
			// add to the heightsQueue all queries for height index i since they are looking
			// for an index greater than i
			int size = queriesPerHeightIndex[i].size();
			if (size > 0) {
				for (int j = 0; j < size; j++) {
					heightsQueue.offer(queriesPerHeightIndex[i].get(j));
				}
				firstElement = heightsQueue.peek();
			}
		}
		return result;
	}

	private static void check(int[] heights, int[][] queries, int[] expected) {
		System.out.println("heights is: " + Arrays.toString(heights));
		System.out.println("queries is: ");
		for (int[] query : queries) {
			System.out.println(Arrays.toString(query));
		}
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] leftmostBuildingQueries = leftmostBuildingQueries(heights, queries); // Calls your implementation
		System.out.println("leftmostBuildingQueries is: " + Arrays.toString(leftmostBuildingQueries));
	}
}
