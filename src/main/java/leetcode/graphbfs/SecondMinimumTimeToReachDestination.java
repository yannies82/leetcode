package leetcode.graphbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SecondMinimumTimeToReachDestination {

	public static void main(String[] args) {
		check(5, new int[][] { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 3, 4 }, { 4, 5 } }, 3, 5, 13);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/second-minimum-time-to-reach-destination. This
	 * solution uses the Djikstra algorithm with a priority queue to traverse the
	 * graph of cities and find the second minimum time to reach city n. Time
	 * complexity is O(nlogn + m) where m is the length of the edges array.
	 * 
	 * @param n
	 * @param edges
	 * @param time
	 * @param change
	 * @return
	 */
	public static int secondMinimum(int n, int[][] edges, int time, int change) {
		// Generate adjacency list, fill with city info
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		int m = edges.length;
		// populate adjacency list in both directions
		for (int i = 0; i < m; i++) {
			adj.get(edges[i][0]).add(edges[i][1]);
			adj.get(edges[i][1]).add(edges[i][0]);
		}
		// start from city 1 and iterate cities until city n is reached twice
		Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		queue.add(new int[] { 1, 0 });
		// marks the number of visits to each city
		int[] visited = new int[n + 1];
		// keeps min time to reach each city
		int[] minTime = new int[n + 1];
		Arrays.fill(minTime, -1);
		// iterate the queue as long as it has elements
		while (!queue.isEmpty()) {
			int[] currentDest = queue.poll();
			int currentCity = currentDest[0];
			int currentTime = currentDest[1];
			if (minTime[currentCity] == currentTime || visited[currentCity] == 2) {
				// do not process this city if it has already been visited twice
				// or has already been processed after arriving at exactly the same time
				continue;
			}
			visited[currentCity]++;
			minTime[currentCity] = currentTime;
			if (currentCity == n && visited[currentCity] == 2) {
				// return result since this is the second time that we reach city n
				return currentTime;
			}
			// calculate time to adjacent cities taking waiting time into account
			if ((currentTime / change) % 2 == 1) {
				currentTime += change - (currentTime % change);
			}
			int newTime = currentTime + time;
			List<Integer> nextDests = adj.get(currentCity);
			// iterate for all possible adjacent cities to the current one
			for (int j = 0; j < nextDests.size(); j++) {
				Integer nextDest = nextDests.get(j);
				if (newTime != minTime[nextDest] && visited[nextDest] < 2) {
					queue.add(new int[] { nextDest, newTime });
				}
			}
		}
		return -1;
	}

	private static void check(int n, int[][] edges, int time, int change, int expected) {
		System.out.println("n is: " + n);
		System.out.println("edges is: ");
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("time is: " + time);
		System.out.println("change is: " + change);
		System.out.println("expected is: " + expected);
		int secondMinimum = secondMinimum(n, edges, time, change); // Calls your implementation
		System.out.println("secondMinimum is: " + secondMinimum);
	}
}
