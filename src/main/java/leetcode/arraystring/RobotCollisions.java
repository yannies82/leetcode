package leetcode.arraystring;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class RobotCollisions {

	public static void main(String[] args) {
		check(new int[] { 33, 60, 79, 34, 26 }, new int[] { 927, 428, 859, 322, 245 }, "LRLRR", List.of(926, 857));
		check(new int[] { 5, 46, 12 }, new int[] { 3, 27, 43 }, "RLL", List.of(27, 42));
		check(new int[] { 5, 4, 3, 2, 1 }, new int[] { 2, 17, 9, 15, 10 }, "RRRRR", List.of(2, 17, 9, 15, 10));
		check(new int[] { 3, 5, 2, 6 }, new int[] { 10, 10, 15, 12 }, "RLRL", List.of(14));
		check(new int[] { 1, 2, 5, 6 }, new int[] { 10, 10, 11, 11 }, "RLRL", List.of());
	}

	private static final Comparator<int[]> COMPARATOR = (a, b) -> {
		return a[1] - b[1];
	};

	/**
	 * Leetcode problem: https://leetcode.com/problems/robot-collisions. This
	 * solution keeps all robots in an array sorted by position. It iterates the
	 * array of robots and adds all robots going right to a stack. For each robot
	 * going left, it uses the robots in the stack to perform collisions with robots
	 * going right. Time complexity is O(nlogn) where n is the length of the
	 * positions array.
	 * 
	 * @param positions
	 * @param healths
	 * @param directions
	 * @return
	 */
	public static List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
		// keep two arrays of all robots, one we will work on, the other keeps the
		// initial order
		int[][] initialRobots = new int[positions.length][4];
		int[][] allRobots = new int[positions.length][4];
		for (int i = 0; i < positions.length; i++) {
			allRobots[i] = new int[] { i, positions[i], healths[i], directions.charAt(i) };
			initialRobots[i] = allRobots[i];
		}
		// sort robots by position
		Arrays.sort(allRobots, COMPARATOR);
		// keep a stack with the robots going right
		Deque<int[]> robotsStack = new ArrayDeque<>();
		for (int i = 0; i < allRobots.length; i++) {
			int[] robot = allRobots[i];
			if (robot[3] == 'R') {
				// add the robot going right to the stack
				robotsStack.offerFirst(robot);
			} else {
				// this robot goes left, while its health is > 0 and there are robots in the
				// stack going right, perform collisions
				while (!robotsStack.isEmpty() && robot[2] > 0) {
					int[] robotGoingRight = robotsStack.peek();
					if (robot[2] > robotGoingRight[2]) {
						// robot going right is destroyed by the collision
						robotGoingRight[2] = 0;
						robot[2]--;
					} else if (robot[2] < robotGoingRight[2]) {
						// robot going left is destroyed by the collision
						robot[2] = 0;
						robotGoingRight[2]--;
					} else {
						// both robots are destroyed by the collision
						robot[2] = 0;
						robotGoingRight[2] = 0;
					}
					if (robotGoingRight[2] == 0) {
						// robot going right did not survive the collision, remove from stack
						robotsStack.poll();
					}
				}
			}
		}
		// add all robots with health > 0 to the final list
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < initialRobots.length; i++) {
			if (initialRobots[i][2] > 0) {
				result.add(initialRobots[i][2]);
			}
		}
		return result;
	}

	/**
	 * Alternate solution which uses binary search to find the position of the next
	 * right element. Time complexity is O(nlogn) where n is the length of the
	 * positions array.
	 * 
	 * @param positions
	 * @param healths
	 * @param directions
	 * @return
	 */
	public static List<Integer> survivedRobotsHealths2(int[] positions, int[] healths, String directions) {
		// split robots into two lists, one going left and one going right
		List<int[]> robotsGoingLeftList = new ArrayList<>();
		List<int[]> robotsGoingRightList = new ArrayList<>();
		// also keep an array of all robots in their initial order
		int[][] allRobots = new int[positions.length][3];
		for (int i = 0; i < positions.length; i++) {
			allRobots[i] = new int[] { i, positions[i], healths[i] };
			if (directions.charAt(i) == 'L') {
				robotsGoingLeftList.add(allRobots[i]);
			} else {
				robotsGoingRightList.add(allRobots[i]);
			}
		}
		if (robotsGoingLeftList.isEmpty() || robotsGoingRightList.isEmpty()) {
			// early exit if there are no robots going left or right, because there will be
			// no collisions
			return Arrays.stream(healths).boxed().toList();
		}
		// sort both lists by position
		robotsGoingLeftList.sort(COMPARATOR);
		robotsGoingRightList.sort(COMPARATOR);
		// iterate elements of the robotsGoingLeftList and perform binary search to find
		// the first element of the robotsGoingRightList which is on their left
		for (int i = 0; i < robotsGoingLeftList.size(); i++) {
			int[] robotGoingLeft = robotsGoingLeftList.get(i);
			// find first robot going right which is on the left of the current robot going
			// left
			int indexOfNextGoingRight = performBinarySearch(robotsGoingRightList, robotGoingLeft[1]);
			while (robotGoingLeft[2] > 0 && indexOfNextGoingRight >= 0) {
				// continue as long as the current robot has health or we are out of robots
				// going right
				int[] robotGoingRight = robotsGoingRightList.get(indexOfNextGoingRight);
				if (robotGoingLeft[2] > robotGoingRight[2]) {
					robotGoingRight[2] = 0;
					robotGoingLeft[2]--;
				} else if (robotGoingLeft[2] < robotGoingRight[2]) {
					robotGoingLeft[2] = 0;
					robotGoingRight[2]--;
				} else {
					robotGoingLeft[2] = 0;
					robotGoingRight[2] = 0;
				}
				if (robotGoingRight[2] == 0) {
					// the robot going right did not survive the collision, therefore remove it from
					// the list
					robotsGoingRightList.remove(indexOfNextGoingRight);
					indexOfNextGoingRight--;
				}
			}
		}
		// add all robots with health > 0 to the final list
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < allRobots.length; i++) {
			if (allRobots[i][2] > 0) {
				result.add(allRobots[i][2]);
			}
		}
		return result;
	}

	private static int performBinarySearch(List<int[]> robotsGoingRightList, int position) {
		int start = 0;
		int end = robotsGoingRightList.size();
		int result = -1;
		while (start < end) {
			int mid = (start + end) / 2;
			if (robotsGoingRightList.get(mid)[1] < position) {
				result = mid;
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return result;
	}

	private static void check(int[] positions, int[] healths, String directions, List<Integer> expected) {
		System.out.println("positions is: " + Arrays.toString(positions));
		System.out.println("healths is: " + Arrays.toString(healths));
		System.out.println("directions is: " + directions);
		System.out.println("expected is: " + expected);
		List<Integer> survivedRobotsHealths = survivedRobotsHealths(positions, healths, directions); // Calls your
																										// implementation
		System.out.println("survivedRobotsHealths is: " + survivedRobotsHealths);
	}
}
