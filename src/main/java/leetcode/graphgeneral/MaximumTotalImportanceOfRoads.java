package leetcode.graphgeneral;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumTotalImportanceOfRoads {

	public static void main(String[] args) {
		check(5, new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 0, 2 }, { 1, 3 }, { 2, 4 } }, 43);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-total-importance-of-roads. This
	 * solution calculates the frequency of each city in the roads array, then sorts
	 * the frequency array and calculates the total importance. Time complexity is
	 * O(max(m,n)) where m is the length of the roads array.
	 * 
	 * @param n
	 * @param roads
	 * @return
	 */
	public static long maximumImportance(int n, int[][] roads) {
		// calculate frequency
		int[] frequency = new int[n];
		for (int[] road : roads) {
			frequency[road[0]]++;
			frequency[road[1]]++;
		}
		// sort frequency
		Arrays.sort(frequency);
		// starting from the least important city calculate
		// the total importance
		long totalImportance = 0;
		for (int i = 0; i < frequency.length; i++) {
			totalImportance += (i + 1) * (long) frequency[i];
		}
		return totalImportance;
	}

	/**
	 * This solution assigns the importance to each city by its index, before
	 * calculating the total importance. Time complexity is O(max(m,n)) where m is
	 * the length of the roads array.
	 * 
	 * @param n
	 * @param roads
	 * @return
	 */
	public static long maximumImportance2(int n, int[][] roads) {
		int[] importance = new int[n];
		int[] frequency = new int[n];
		for (int i = 0; i < roads.length; i++) {
			frequency[roads[i][0]]++;
			frequency[roads[i][1]]++;
		}
		int[][] importanceByIndex = new int[n][2];
		for (int i = 0; i < n; i++) {
			importanceByIndex[i][0] = i;
			importanceByIndex[i][1] = frequency[i];
		}
		Arrays.sort(importanceByIndex, (a, b) -> b[1] - a[1]);

		int maxImportance = n;
		for (int i = 0; i < n; i++) {
			importance[importanceByIndex[i][0]] = maxImportance--;
		}
		long totalImportance = 0;
		for (int i = 0; i < roads.length; i++) {
			totalImportance += importance[roads[i][0]] + importance[roads[i][1]];
		}
		return totalImportance;
	}

	/**
	 * Similar solution to the previous one, this one uses a PriorityQueue.
	 * 
	 * @param n
	 * @param roads
	 * @return
	 */
	public static long maximumImportance3(int n, int[][] roads) {
		int[] importance = new int[n];
		int[] adjacency = new int[n];
		for (int i = 0; i < roads.length; i++) {
			adjacency[roads[i][0]]++;
			adjacency[roads[i][1]]++;
		}
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		for (int i = 0; i < adjacency.length; i++) {
			queue.offer(new int[] { i, adjacency[i] });
		}

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			importance[current[0]] = n--;
		}
		long totalImportance = 0;
		for (int i = 0; i < roads.length; i++) {
			totalImportance += importance[roads[i][0]] + importance[roads[i][1]];
		}
		return totalImportance;
	}

	private static void check(int n, int[][] roads, int expected) {
		System.out.println("n is: " + n);
		System.out.println("roads is: ");
		for (int[] road : roads) {
			System.out.println(Arrays.toString(road));
		}
		System.out.println("expected is: " + expected);
		long maximumImportance = maximumImportance(n, roads); // calls implementation
		System.out.println("maximumImportance is: " + maximumImportance);
	}
}
