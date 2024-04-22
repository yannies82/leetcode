package leetcode.graphbfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class OpenTheLock {

	public static void main(String[] args) {
		String[] deadends1 = { "0201", "0101", "0102", "1212", "2002" };
		check(deadends1, "0202", 6);
		String[] deadends2 = { "8888" };
		check(deadends2, "0009", 1);
		String[] deadends3 = { "8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888" };
		check(deadends3, "8888", -1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/open-the-lock. This solution
	 * performs BFS traversal of the combinations as a graph, starting from 0 and
	 * aiming to reach the target number. The solution treats combinations as
	 * integer numbers for space and time efficiency. Time complexity is O(n) where
	 * n is the combination range (10000 in this case).
	 * 
	 * @param deadends
	 * @param target
	 * @return
	 */
	public static int openLock(String[] deadends, String target) {
		int startNumber = 0;
		int targetNumber = Integer.parseInt(target);
		if (startNumber == targetNumber) {
			// early exist if no start and target combinations are the same
			return 0;
		}
		boolean[] deadendsArray = new boolean[10000];
		for (int i = 0; i < deadends.length; i++) {
			deadendsArray[Integer.parseInt(deadends[i])] = true;
		}
		if (deadendsArray[startNumber]) {
			// early exit if start number is in the deadends array
			return -1;
		}
		// keeps visited combinations
		boolean[] visited = new boolean[10000];
		// is used for BFS traversal
		Queue<Integer> combinations = new ArrayDeque<>();
		combinations.offer(startNumber);
		visited[startNumber] = true;
		int combinationsCount = 0;
		while (!combinations.isEmpty()) {
			// increase combinations count for the nth possible mutation
			combinationsCount++;
			// remove all nth combinations and put in the queue all possible n + 1 level
			// combinations
			int levelLength = combinations.size();
			for (int i = 0; i < levelLength; i++) {
				// remove possible nth level combination from the queue
				int currentCombination = combinations.poll();
				// mark combination as visited
				int[] nextCombinations = getNextCombinations(currentCombination);
				for (int j = 0; j < nextCombinations.length; j++) {
					if (!visited[nextCombinations[j]] && !deadendsArray[nextCombinations[j]]) {
						// only put in the queue non visited combinations which are
						// not included in the deadEnds set
						if (nextCombinations[j] == targetNumber) {
							// after performing combinationsCount number of combinations we have reached
							// the target
							return combinationsCount;
						}
						combinations.offer(nextCombinations[j]);
						visited[nextCombinations[j]] = true;
					}
				}
			}
		}
		// we are out of combinations, the target cannot be reached
		return -1;
	}

	private static int[] getNextCombinations(int combination) {
		int[] result = new int[8];
		int factor = 1;
		// the next possible combinations are 8, for each one we roll one of the four
		// digits either up or down
		for (int i = 0; i < 4; i++) {
			int nextFactor = factor * 10;
			int digit = (combination % nextFactor) / factor;
			if (digit == 9) {
				// turn the digit to 0 if it is 9
				result[i << 1] = combination - 9 * factor;
			} else {
				// add 1 to the digit
				result[i << 1] = combination + factor;
			}
			if (digit == 0) {
				// turn the digit to 9 if it is 0
				result[(i << 1) + 1] = combination + 9 * factor;
			} else {
				// subtract 1 from the digit
				result[(i << 1) + 1] = combination - factor;
			}
			factor = nextFactor;
		}
		return result;
	}

	private static void check(String[] deadends, String target, int expected) {
		System.out.println("deadends is: " + Arrays.toString(deadends));
		System.out.println("target is: " + target);
		System.out.println("expected is: " + expected);
		int openLock = openLock(deadends, target); // Calls your implementation
		System.out.println("openLock is: " + openLock);
	}
}
