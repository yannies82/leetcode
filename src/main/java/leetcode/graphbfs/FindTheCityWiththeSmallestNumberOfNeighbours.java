package leetcode.graphbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindTheCityWiththeSmallestNumberOfNeighbours {

	public static void main(String[] args) {
		check(4, new int[][] { { 0, 1, 3 }, { 1, 2, 1 }, { 1, 3, 4 }, { 2, 3, 1 } }, 4, 3);
		check(6, new int[][] { { 0, 1, 10 }, { 0, 2, 1 }, { 2, 3, 1 }, { 1, 3, 1 }, { 1, 4, 1 }, { 4, 5, 10 } }, 20, 5);
		check(5, new int[][] { { 0, 1, 2 }, { 0, 4, 8 }, { 1, 2, 3 }, { 1, 4, 2 }, { 2, 3, 1 }, { 3, 4, 1 } }, 2, 0);
		check(6, new int[][] { { 0, 3, 5 }, { 2, 3, 7 }, { 0, 5, 2 }, { 0, 2, 5 }, { 1, 2, 6 }, { 1, 4, 7 },
				{ 3, 4, 4 }, { 2, 5, 5 }, { 1, 5, 8 } }, 8279, 5);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance.
	 * This solution uses the Floyd Walsall algorithm to calculate all distances,
	 * then finds the number of cities within the distanceThreshold for each city.
	 * Time complexity is O(n^3).
	 * 
	 * @param n
	 * @param edges
	 * @param distanceThreshold
	 * @return
	 */
	public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
		// initialize distance array
		int[][] distance = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(distance[i], 1000000000);
			distance[i][i] = 0;
		}
		// populate distance array with edge values interchanging from and to
		for (int i = 0; i < edges.length; i++) {
			int[] edge = edges[i];
			distance[edge[0]][edge[1]] = edge[2];
			distance[edge[1]][edge[0]] = edge[2];
		}
		// calculate all values of the distance array
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					int currentDistance = distance[j][i] + distance[i][k];
					if (currentDistance < distance[j][k]) {
						distance[j][k] = currentDistance;
					}
				}
			}
		}
		// iterate the distance array for each city i to find the number of cities
		// within the distance threshold and update the city with min number of
		// neighbors
		int city = -1;
		int countMin = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (distance[i][j] <= distanceThreshold) {
					count++;
				}
			}
			if (count <= countMin) {
				countMin = count;
				city = i;
			}
		}
		return city;
	}

	/**
	 * This solution uses the Djikstra algorithm with an adjacency list and BFS
	 * traversal to find all reachable cities for every city and then find the one
	 * with the least number of reachable cities. Time complexity is O(n^2).
	 * 
	 * @param n
	 * @param edges
	 * @param distanceThreshold
	 * @return
	 */
	public static int findTheCity2(int n, int[][] edges, int distanceThreshold) {
		// Generate adjacency list, fill with city info
		List<List<CityInfo>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		int m = edges.length;
		// populate adjacency list in both directions
		for (int i = 0; i < m; i++) {
			adj.get(edges[i][0]).add(new CityInfo(edges[i][1], edges[i][2]));
			adj.get(edges[i][1]).add(new CityInfo(edges[i][0], edges[i][2]));
		}
		// iterate all cities as starting points and find their reachable cities
		int[] minCity = new int[] { Integer.MAX_VALUE, 0 };
		for (int i = 0; i < n; i++) {
			Queue<CityInfo> queue = new PriorityQueue<>((a, b) -> b.distance - a.distance);
			queue.add(new CityInfo(i, distanceThreshold));
			// keeps the list of reachable cities for city i
			List<Integer> reachableCities = new ArrayList<>();
			// marks the visited cities
			boolean[] visited = new boolean[n];
			// iterate the queue as long as it has elements
			while (!queue.isEmpty()) {
				CityInfo currentDest = queue.poll();
				if (visited[currentDest.city]) {
					continue;
				}
				reachableCities.add(currentDest.city);
				visited[currentDest.city] = true;
				List<CityInfo> nextDests = adj.get(currentDest.city);
				// iterate for all possible adjacent cities to the current one
				for (int j = 0; j < nextDests.size(); j++) {
					CityInfo nextDest = nextDests.get(j);
					if (!visited[nextDest.city] && nextDest.distance <= currentDest.distance) {
						// next city is within the distance left so add it to the queue
						queue.add(new CityInfo(nextDest.city, currentDest.distance - nextDest.distance));
					}
				}
			}
			// update the city with min reachable cities
			if (reachableCities.size() <= minCity[0]) {
				minCity[0] = reachableCities.size();
				minCity[1] = i;
			}
		}
		return minCity[1];
	}

	private static class CityInfo {
		int city;
		int distance;

		public CityInfo(int city, int distance) {
			super();
			this.city = city;
			this.distance = distance;
		}
	}

	private static void check(int n, int[][] edges, int distanceThreshold, int expected) {
		System.out.println("n is: " + n);
		System.out.println("edges is: ");
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("distanceThreshold is: " + distanceThreshold);
		System.out.println("expected is: " + expected);
		int findTheCity = findTheCity(n, edges, distanceThreshold); // Calls your implementation
		System.out.println("findTheCity is: " + findTheCity);
	}
}
