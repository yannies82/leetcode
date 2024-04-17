package leetcode.graphbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheapestFlightsWithinKStops {

	public static void main(String[] args) {
		check(3, new int[][] { { 0, 1, 100 }, { 1, 2, 100 }, { 0, 2, 500 } }, 0, 2, 1, 200);
		check(4, new int[][] { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, { 2, 3, 200 } }, 0, 3, 1,
				700);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/cheapest-flights-within-k-stops. This solution
	 * uses the Djikstra algorithm. Time complexity is O(n).
	 * 
	 * @param n
	 * @param flights
	 * @param src
	 * @param dst
	 * @param k
	 * @return
	 */
	public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		// Generate adjacency list, fill with flight info
		List<List<DestInfo>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		int m = flights.length;
		for (int i = 0; i < m; i++) {
			adj.get(flights[i][0]).add(new DestInfo(flights[i][1], flights[i][2]));
		}

		// this queue stores the intermediate destination nodes along with the cost and
		// number of stops from the src node and is used for the traversal of the graph
		Queue<CurrentDestInfo> queue = new ArrayDeque<>();
		queue.add(new CurrentDestInfo(src, 0, 0));

		// stores the min cost from the source for each destination
		int[] minCost = new int[n];
		for (int i = 0; i < n; i++) {
			minCost[i] = Integer.MAX_VALUE;
		}
		minCost[src] = 0;

		// iterate the queue as long as it has elements
		while (!queue.isEmpty()) {
			CurrentDestInfo currentDest = queue.poll();

			// do not proceed if the number of stops has been exceeded
			if (currentDest.numOfStops <= k) {
				List<DestInfo> nextDests = adj.get(currentDest.dest);
				// iterate for all possible adjacent destinations to the current one
				for (int i = 0; i < nextDests.size(); i++) {
					int nextDest = nextDests.get(i).dest;
					int nextCost = currentDest.cost + nextDests.get(i).cost;

					// update the min cost for the nextDest if it is less than the previous min cost
					// also add nextDest the queue with updated numOfStops and cost
					if (nextCost < minCost[nextDest]) {
						minCost[nextDest] = nextCost;
						queue.add(new CurrentDestInfo(nextDest, nextCost, currentDest.numOfStops + 1));
					}
				}
			}
		}
		if (minCost[dst] == Integer.MAX_VALUE) {
			// it was not possible to calculate a min cost for dest within k stops
			return -1;
		}
		return minCost[dst];
	}

	/**
	 * This solution uses the Djikstra algorithm with a priority queue. Time
	 * complexity is O(n*logn).
	 * 
	 * @param n
	 * @param flights
	 * @param src
	 * @param dst
	 * @param k
	 * @return
	 */
	public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
		// Generate adjacency list, fill with flight info
		List<List<DestInfo>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		int m = flights.length;
		for (int i = 0; i < m; i++) {
			adj.get(flights[i][0]).add(new DestInfo(flights[i][1], flights[i][2]));
		}

		// this queue stores the intermediate destination nodes along with the cost and
		// number of stops from the src node and is used for the traversal of the graph
		// it is a priority queue and returns elements by ascending cost
		Queue<CurrentDestInfo> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
		queue.add(new CurrentDestInfo(src, 0, 0));

		// iterate the queue as long as it has elements
		while (!queue.isEmpty()) {
			CurrentDestInfo currentDest = queue.poll();
			if (currentDest.dest == dst) {
				// the first time we encounter the destination node we return the min cost
				return currentDest.cost;
			} else if (currentDest.numOfStops <= k) {
				// do not proceed if the number of stops has been exceeded
				List<DestInfo> nextDests = adj.get(currentDest.dest);
				// iterate for all possible adjacent destinations to the current one
				for (int i = 0; i < nextDests.size(); i++) {
					int nextDest = nextDests.get(i).dest;
					int nextCost = currentDest.cost + nextDests.get(i).cost;
					queue.add(new CurrentDestInfo(nextDest, nextCost, currentDest.numOfStops + 1));
				}
			}
		}
		return -1;
	}

	/**
	 * This solution uses BFS traversal of the graph. Time complexity is O(n * m *
	 * k) where m is the flights array length.
	 * 
	 * @param n
	 * @param flights
	 * @param src
	 * @param dst
	 * @param k
	 * @return
	 */
	public static int findCheapestPrice3(int n, int[][] flights, int src, int dst, int k) {
		Map<Integer, List<DestInfo>> destinationsMap = new HashMap<>();
		for (int i = 0; i < flights.length; i++) {
			destinationsMap.computeIfAbsent(flights[i][0], key -> new ArrayList<>())
					.add(new DestInfo(flights[i][1], flights[i][2]));
		}
		if (destinationsMap.get(src) == null) {
			return -1;
		}
		Queue<DestInfo> queue = new ArrayDeque<>();
		queue.addAll(destinationsMap.get(src));
		int numOfStops = 0;
		int minCost = Integer.MAX_VALUE;
		while (!queue.isEmpty() && numOfStops <= k) {
			int length = queue.size();
			for (int i = 0; i < length; i++) {
				DestInfo currentDest = queue.poll();
				if (currentDest.cost < minCost) {
					if (currentDest.dest == dst) {
						minCost = currentDest.cost;
					} else {
						List<DestInfo> nextDests = destinationsMap.get(currentDest.dest);
						if (nextDests != null) {
							for (int j = 0; j < nextDests.size(); j++) {
								DestInfo nextDest = nextDests.get(j);
								queue.add(new DestInfo(nextDest.dest, currentDest.cost + nextDest.cost));
							}
						}
					}
				}
			}
			numOfStops++;
		}
		return minCost == Integer.MAX_VALUE ? -1 : minCost;
	}

	private static class DestInfo {
		int dest;
		int cost;

		private DestInfo(int dest, int cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}
	}

	private static class CurrentDestInfo {
		int dest;
		int cost;
		int numOfStops;

		private CurrentDestInfo(int dest, int cost, int numOfStops) {
			super();
			this.dest = dest;
			this.cost = cost;
			this.numOfStops = numOfStops;
		}
	}

	private static void check(int n, int[][] flights, int src, int dst, int k, int expected) {
		System.out.println("n is: " + n);
		System.out.println("flights is: ");
		for (int[] flight : flights) {
			System.out.println(Arrays.toString(flight));
		}
		System.out.println("src is: " + src);
		System.out.println("dst is: " + dst);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int findCheapestPrice = findCheapestPrice(n, flights, src, dst, k); // Calls your implementation
		System.out.println("findCheapestPrice is: " + findCheapestPrice);
	}
}
