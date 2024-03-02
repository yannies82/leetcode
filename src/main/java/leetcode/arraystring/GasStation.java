package leetcode.arraystring;

import java.util.Arrays;

public class GasStation {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 4, 5, 1, 2 }, 3);
		check(new int[] { 2, 3, 4 }, new int[] { 3, 4, 3 }, -1);
	}

	public static int canCompleteCircuit(int[] gas, int[] cost) {
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
