package leetcode.graphgeneral;

import java.util.Arrays;

public class MaximumEmployeestoBeInvitedToAMeeting {

	public static void main(String[] args) {
		check(new int[] { 2, 2, 1, 2 }, 3);
		check(new int[] { 1, 2, 0 }, 3);
		check(new int[] { 3, 0, 1, 4, 1 }, 4);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting.
	 * Time complexity is O(n) where n is the length of the favorite array.
	 * 
	 * 
	 * @param favorite
	 * @return
	 */
	public static int maximumInvitations(int[] favorite) {
		int n = favorite.length;

		// Step 1: Initialize arrays
		int[] inDegree = new int[n]; // Array to store in-degrees
		int[] queue = new int[n]; // Queue for topological sorting
		int[] deep = new int[n]; // Depth array to store chain lengths

		// Step 2: Count in-degrees
		for (int i = 0; i < n; i++) {
			inDegree[favorite[i]]++;
		}

		// Step 3: Process chains using topological sorting
		int left = 0;
		int right = 0; // Pointers for the queue
		for (int i = 0; i < n; i++) {
			if (inDegree[i] == 0) {
				queue[right++] = i; // Add nodes with in-degree 0
			}
		}

		while (left < right) {
			int current = queue[left++]; // Remove node from queue
			int next = favorite[current]; // Follow the edge to the next node
			deep[next] = Math.max(deep[next], deep[current] + 1); // Update chain length
			if (--inDegree[next] == 0) { // Reduce in-degree of the next node
				queue[right++] = next;
			}
		}

		// Step 4: Process cycles and calculate results
		int smallCircle = 0; // Sum of lengths of chains and 2-cycles
		int bigCircle = 0; // Maximum cycle length for larger cycles

		for (int i = 0; i < n; i++) {
			if (inDegree[i] != 0) { // Node is part of a cycle
				inDegree[i] = 0; // Mark node as visited
				int count = 1; // Count the cycle size
				for (int j = favorite[i]; j != i; j = favorite[j]) {
					count++;
					inDegree[j] = 0; // Mark all nodes in the cycle as visited
				}

				if (count == 2) { // Special case: 2-cycle
					smallCircle += deep[i] + deep[favorite[i]] + 2;
				} else { // Larger cycles
					bigCircle = Math.max(bigCircle, count);
				}
			}
		}
		return Math.max(bigCircle, smallCircle); // Return the maximum result
	}

	private static void check(int[] favorite, int expected) {
		System.out.println("favorite is: " + Arrays.toString(favorite));
		System.out.println("expected is: " + expected);
		int maximumInvitations = maximumInvitations(favorite); // Calls your implementation
		System.out.println("maximumInvitations is: " + maximumInvitations);
	}
}
