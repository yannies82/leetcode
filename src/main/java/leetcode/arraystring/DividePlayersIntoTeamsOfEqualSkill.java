package leetcode.arraystring;

import java.util.Arrays;

public class DividePlayersIntoTeamsOfEqualSkill {

	public static void main(String[] args) {
		check(new int[] { 3, 2, 5, 1, 3, 4 }, 22);
		check(new int[] { 3, 4 }, 12);
		check(new int[] { 1, 1, 2, 3 }, -1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/divide-players-into-teams-of-equal-skill. This
	 * solution keeps the frequency of each number and checks if min and max
	 * frequencies are matching, also calculating their multiples. Time complexity
	 * is O(n) where n is the length of the skill array.
	 * 
	 * @param skill
	 * @return
	 */
	public static long dividePlayers(int[] skill) {
		// find the frequency of each number, min and max numbers
		int[] frequency = new int[1001];
		int min = skill[0];
		int max = skill[0];
		for (int i = 0; i < skill.length; i++) {
			if (skill[i] < min) {
				min = skill[i];
			} else if (skill[i] > max) {
				max = skill[i];
			}
			frequency[skill[i]]++;
		}
		// check if min and max frequencies are equal and calculate multiples
		long mult = 0;
		int limit = (max - min) / 2;
		for (int i = 0; i <= limit; i++) {
			int left = min + i;
			int right = max - i;
			if (frequency[left] != frequency[right]) {
				return -1;
			}
			mult += (long) frequency[left] * left * right;
		}
		// compensate for the middle value if it exists, where left == right
		// because the multiple has been counted twice
		if ((max - min) % 2 == 0) {
			int mid = (max + min) / 2;
			mult -= (long) frequency[mid] * mid * mid / 2;
		}
		return mult;
	}

	/**
	 * This solution sorts the input array and uses a two pointer approach. Time
	 * complexity is O(nlogn) where n is the length of the skill array.
	 * 
	 * @param skill
	 * @return
	 */
	public static long dividePlayers2(int[] skill) {
		Arrays.sort(skill);
		int lastIndex = skill.length - 1;
		int targetSum = skill[0] + skill[lastIndex];
		long mult = skill[0] * skill[lastIndex];
		int limit = skill.length / 2;
		for (int i = 1; i < limit; i++) {
			int endIndex = lastIndex - i;
			if (skill[i] + skill[endIndex] != targetSum) {
				return -1;
			}
			mult += skill[i] * skill[endIndex];
		}
		return mult;
	}

	private static void check(int[] skill, long expected) {
		System.out.println("skill is: " + Arrays.toString(skill));
		System.out.println("expected is: " + expected);
		long dividePlayers = dividePlayers(skill); // Calls your implementation
		System.out.println("dividePlayers is: " + dividePlayers);
	}
}
