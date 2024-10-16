package leetcode.array;

import java.util.Arrays;

public class GasStation {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 4, 5, 1, 2 }, 3);
		check(new int[] { 2, 3, 4 }, new int[] { 3, 4, 3 }, -1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/gas-station. This solution
	 * traverses the arrays to find a candidate index, from which and until the
	 * length of the array the supplied gas is less than the cost. It then iterates
	 * the arrays again until it either reaches the candidate index or the
	 * accumulated supplied gas becomes less than the cost. Time complexity is O(n)
	 * where n is the length of the input arrays.
	 * 
	 * @param gas
	 * @param cost
	 * @return
	 */
	public static int canCompleteCircuit(int[] gas, int[] cost) {
		int length = gas.length;
		int candidateIndex = 0;
		int sum = 0;
		// iterate the arrays to find the candidate index
		for (int i = 0; i < length; i++) {
			sum += gas[i] - cost[i];
			if (sum < 0) {
				// the remaining gas at this station is less than the cost of reaching the next
				// station, try starting from the next station
				candidateIndex = i + 1;
				sum = 0;
			}
		}
		if (candidateIndex == length) {
			// no candidate index was found
			return -1;
		}
		// perform a second iteration to check if we can reach the candidate index again
		for (int i = 0; i < candidateIndex; i++) {
			sum += gas[i] - cost[i];
			if (sum < 0) {
				// we could not reach the candidate index
				return -1;
			}
		}
		// we reached the candidate index, return it
		return candidateIndex;
	}

	/**
	 * Equivalent solution to the first one with alternate code using a single while
	 * loop.
	 * 
	 * @param gas
	 * @param cost
	 * @return
	 */
	public static int canCompleteCircuit2(int[] gas, int[] cost) {
		int length = gas.length;
		int candidateIndex = 0;
		int sum = 0;
		int i = 0;
		boolean hasLooped = false;
		while (!hasLooped || i != candidateIndex) {
			sum += gas[i] - cost[i];
			if (++i == length) {
				hasLooped = true;
				i = 0;
			}
			if (sum < 0) {
				if (hasLooped) {
					return -1;
				}
				candidateIndex = i;
				sum = 0;
			}
		}
		return candidateIndex;
	}

	private static void check(int[] gas, int[] cost, int expectedCanCompleteCircuit) {
		System.out.println("gas is: " + Arrays.toString(gas));
		System.out.println("cost is: " + Arrays.toString(cost));
		System.out.println("expectedCanCompleteCircuit is: " + expectedCanCompleteCircuit);
		int canCompleteCircuit = canCompleteCircuit(gas, cost); // Calls your implementation
		System.out.println("canCompleteCircuit is: " + canCompleteCircuit);
	}
}
