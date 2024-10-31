package leetcode.array.frequency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimumTotalDistanceTravelled {

	public static void main(String[] args) {
		check(new ArrayList<>(List.of(0, 4, 6)), new int[][] { { 2, 2 }, { 6, 2 } }, 4);
		check(new ArrayList<>(List.of(1, -1)), new int[][] { { -2, 1 }, { 2, 1 } }, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-total-distance-traveled. This solution
	 * uses multidimensional dynamic programming to test all possible cases and
	 * select optimal ones. Time complexity is O(m*n) where m is the size of robots
	 * list and n is the length of the factory array.
	 * 
	 * @param robot
	 * @param factory
	 * @return
	 */
	public static long minimumTotalDistance(List<Integer> robot, int[][] factory) {
		// Sort robots and factories by position for optimal assignment
		Collections.sort(robot);
		Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

		int robotCount = robot.size();
		int factoryCount = factory.length;

		// dp[i][j] represents min total distance for robots[i:] using factories[j:]
		long[][] dpArray = new long[robotCount + 1][factoryCount + 1];

		// Set last column to MAX_VALUE as boundary condition
		for (int i = 0; i <= robotCount; i++) {
			dpArray[i][factoryCount] = Long.MAX_VALUE;
		}

		// Use array as custom deque to maintain potential optimal assignments
		Assignment[] deque = new Assignment[robotCount + 1];
		// Process each factory from right to left
		for (int j = factoryCount - 1; j >= 0; j--) {
			// Track cumulative distance from current factory to robots
			long prefix = 0;

			// Initialize with boundary condition
			deque[0] = new Assignment(robotCount, 0L);
			int tail = 0, head = 0;

			// Process each robot from right to left
			for (int i = robotCount - 1; i >= 0; i--) {
				// Add distance from current robot to current factory
				prefix += Math.abs(robot.get(i) - factory[j][0]);

				// Remove assignments that exceed factory capacity
				int capacity = i + factory[j][1];
				while (tail >= head && deque[head].getRobotCount() > capacity) {
					head++;
				}

				// Remove suboptimal assignments
				long minDistance = dpArray[i][j + 1] - prefix;
				while (tail >= head && deque[tail].getDistance() >= minDistance) {
					tail--;
				}

				// Add current state to deque
				deque[++tail] = new Assignment(i, minDistance);
				// Update dp with optimal assignment
				dpArray[i][j] = deque[head].getDistance() + prefix;
			}
		}

		// dpArray[0][0] represents the minimum total distance for all robots using all
		// factories
		return dpArray[0][0];
	}

	private static class Assignment {
		private int robotCount;
		private long distance;

		public Assignment(int robotCount, long distance) {
			super();
			this.robotCount = robotCount;
			this.distance = distance;
		}

		public int getRobotCount() {
			return robotCount;
		}

		public long getDistance() {
			return distance;
		}
	}

	private static void check(List<Integer> robot, int[][] factory, long expected) {
		System.out.println("robot is: " + robot);
		System.out.println("factory is: ");
		for (int[] item : factory) {
			System.out.println(Arrays.toString(item));
		}
		System.out.println("expected is: " + expected);
		long minimumTotalDistance = minimumTotalDistance(robot, factory); // Calls your implementation
		System.out.println("minimumTotalDistance is: " + minimumTotalDistance);
	}
}
