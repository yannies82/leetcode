package leetcode.binarysearch;

import java.util.Arrays;

public class WalkingRobotSimulation {

	public static void main(String[] args) {
		check(new int[] { 4, -1, 3 }, new int[][] {}, 25);
		check(new int[] { 4, -1, 4, -2, 4 }, new int[][] { { 2, 4 } }, 65);
		check(new int[] { 6, -1, -1, 6 }, new int[][] {}, 36);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/walking-robot-simulation.
	 * This solution maintains two separate arrays of the obstacles sorted by the X
	 * values and the Y values respectively and uses binary search to find obstacles
	 * that will be present in the robot's path. Time complexity is O(m*logn) where
	 * m is the length of the commands array and n is the length of the obstacles
	 * array.
	 * 
	 * @param commands
	 * @param obstacles
	 * @return
	 */
	public static int robotSim(int[] commands, int[][] obstacles) {
		// create 2 copy arrays of the obstacles, one to be sorted by X values first
		// and one sorted by Y values first
		int[][] obstaclesSortedByX = new int[obstacles.length][2];
		int[][] obstaclesSortedByY = new int[obstacles.length][2];
		System.arraycopy(obstacles, 0, obstaclesSortedByX, 0, obstacles.length);
		System.arraycopy(obstacles, 0, obstaclesSortedByY, 0, obstacles.length);
		Arrays.sort(obstaclesSortedByX, (a, b) -> {
			int diff = a[0] - b[0];
			if (diff == 0) {
				return a[1] - b[1];
			}
			return diff;
		});
		Arrays.sort(obstaclesSortedByY, (a, b) -> {
			int diff = a[1] - b[1];
			if (diff == 0) {
				return a[0] - b[0];
			}
			return diff;
		});
		// initialize position and direction
		Direction direction = Direction.UP;
		int[] position = { 0, 0 };
		int maxDistance = 0;
		// iterate all commands
		for (int i = 0; i < commands.length; i++) {
			if (commands[i] >= 0) {
				// move position taking obstacles into consideration
				move(position, direction, commands[i], obstaclesSortedByX, obstaclesSortedByY);
				// calculate new distance and compare with maxDistance
				int newDistance = position[0] * position[0] + position[1] * position[1];
				if (newDistance > maxDistance) {
					maxDistance = newDistance;
				}
			} else {
				// find next direction
				direction = direction.findNextDirection(commands[i]);
			}
		}
		return maxDistance;
	}

	private static void move(int[] position, Direction direction, int command, int[][] obstaclesSortedByX,
			int[][] obstaclesSortedByY) {
		switch (direction) {
		case UP -> movePositionAsc(position, command, obstaclesSortedByX, 0);
		case RIGHT -> movePositionAsc(position, command, obstaclesSortedByY, 1);
		case DOWN -> movePositionDesc(position, -command, obstaclesSortedByX, 0);
		case LEFT -> movePositionDesc(position, -command, obstaclesSortedByY, 1);
		}
	}

	private static void movePositionAsc(int[] position, int distance, int[][] obstacles, int targetIndex) {
		int otherIndex = targetIndex ^ 1;
		int start = 0;
		int end = obstacles.length;
		// perform binary search to find the obstacle with the closest (greater)
		// otherIndex value
		// compared to position, with the same targetIndex
		int[] nearestObstacle = null;
		while (start < end) {
			int mid = (start + end) / 2;
			if (position[targetIndex] < obstacles[mid][targetIndex]) {
				end = mid;
			} else if (position[targetIndex] > obstacles[mid][targetIndex]) {
				start = mid + 1;
			} else if (position[otherIndex] < obstacles[mid][otherIndex]) {
				nearestObstacle = obstacles[mid];
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		if (nearestObstacle == null) {
			// no nearest obstacle exists, the robot can move freely
			position[otherIndex] = position[otherIndex] + distance;
		} else {
			// nearest obstacle exist, stop before it if needed
			position[otherIndex] = Math.min(position[otherIndex] + distance, nearestObstacle[otherIndex] - 1);
		}
	}

	private static void movePositionDesc(int[] position, int distance, int[][] obstacles, int targetIndex) {
		int otherIndex = targetIndex ^ 1;
		int start = 0;
		int end = obstacles.length;
		int[] nearestObstacle = null;
		// perform binary search to find the obstacle with the closest (smaller)
		// otherIndex value
		// compared to position, with the same targetIndex
		while (start < end) {
			int mid = (start + end) / 2;
			if (position[targetIndex] < obstacles[mid][targetIndex]) {
				end = mid;
			} else if (position[targetIndex] > obstacles[mid][targetIndex]) {
				start = mid + 1;
			} else if (position[otherIndex] > obstacles[mid][otherIndex]) {
				nearestObstacle = obstacles[mid];
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		if (nearestObstacle == null) {
			// no nearest obstacle exists, the robot can move freely
			position[otherIndex] = position[otherIndex] + distance;
		} else {
			// nearest obstacle exist, stop before it if needed
			position[otherIndex] = Math.max(position[otherIndex] + distance, nearestObstacle[otherIndex] + 1);
		}
	}

	private static enum Direction {
		UP, RIGHT, DOWN, LEFT;

		private static Direction[] VALUES = Direction.values();

		Direction findNextDirection(int command) {
			int ordinal = this.ordinal();
			if (command == -1) {
				if (ordinal == 3) {
					ordinal = 0;
				} else {
					ordinal++;
				}
			} else if (command == -2) {
				if (ordinal == 0) {
					ordinal = 3;
				} else {
					ordinal--;
				}
			}
			return VALUES[ordinal];
		}
	}

	private static void check(int[] commands, int[][] obstacles, int expected) {
		System.out.println("commands is: " + Arrays.toString(commands));
		System.out.println("obstacles is: ");
		for (int[] obstacle : obstacles) {
			System.out.println(Arrays.toString(obstacle));
		}
		System.out.println("expected is: " + expected);
		int robotSim = robotSim(commands, obstacles); // Calls your implementation
		System.out.println("robotSim is: " + robotSim);
	}
}
