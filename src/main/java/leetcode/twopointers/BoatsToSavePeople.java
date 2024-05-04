package leetcode.twopointers;

import java.util.Arrays;

public class BoatsToSavePeople {

	public static void main(String[] args) {
		check(new int[] { 2, 2 }, 6, 1);
		check(new int[] { 1, 2, 3, 4 }, 4, 3);
		check(new int[] { 3, 5, 3, 4 }, 5, 4);
		check(new int[] { 44, 10, 29, 12, 49, 41, 23, 5, 17, 26 }, 50, 6);
		check(new int[] { 1, 2 }, 3, 1);
		check(new int[] { 3, 2, 2, 1 }, 3, 3);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/boats-to-save-people. This
	 * solution keeps the frequencies of the weights in an array, then iterates this
	 * array using two pointers to calculate how many boats will be needed. Time
	 * complexity is O(n) where n is the length of the people array.
	 * 
	 * @param people
	 * @param limit
	 * @return
	 */
	public static int numRescueBoats(int[] people, int limit) {
		int[] frequency = new int[limit + 1];
		// fill the frequency array
		for (int i = 0; i < people.length; i++) {
			frequency[people[i]]++;
		}
		int left = 0;
		int right = limit;
		int numOfBoats = 0;
		while (left < right) {
			// skip all weights with zero frequency
			while (left <= right && frequency[left] == 0) {
				left++;
			}
			// skip all weights with zero frequency
			while (left <= right && frequency[right] == 0) {
				right--;
			}
			if (left >= right) {
				break;
			}
			if (left + right <= limit) {
				// if the left and right weights sum is less than or equal to the limit
				// add as many boats as the min of the left and right frequencies
				// then subtract the min from the frequencies
				int minBoats = Math.min(frequency[left], frequency[right]);
				numOfBoats += minBoats;
				frequency[left] -= minBoats;
				frequency[right] -= minBoats;
			} else {
				// the weights sum is greater than limit, add frequency[right] boats
				// and set right frequency to 0
				numOfBoats += frequency[right];
				frequency[right] = 0;
			}
		}
		if (left == right) {
			// check how many boats will be needed to fit the weights at the last remaining index
			if (left * 2 <= limit) {
				// the weight is less than or equal to half the limit
				numOfBoats += frequency[left] / 2 + frequency[left] % 2;
			} else {
				// the weight is greater than half the limit, we will need frequency[left] boats
				numOfBoats += frequency[left];
			}
		}
		return numOfBoats;
	}

	/**
	 * Alternate solution, similar to the first one but adds exactly one boat in
	 * each loop. Time complexity is O(n) where n is the length of the people array.
	 * 
	 * @param people
	 * @param limit
	 * @return
	 */
	public static int numRescueBoats2(int[] people, int limit) {
		int[] frequency = new int[limit + 1];
		// fill the frequency array
		for (int i = 0; i < people.length; i++) {
			frequency[people[i]]++;
		}
		int left = 0;
		int right = limit;
		int numOfBoats = 0;
		while (left <= right) {
			// skip all weights with zero frequency
			// the <= check is needed for the case where left == right
			while (left <= right && frequency[left] <= 0) {
				left++;
			}
			// skip all weights with zero frequency
			// the <= check is needed for the case where left == right
			while (left <= right && frequency[right] <= 0) {
				right--;
			}
			if (left > right) {
				break;
			}
			if (left + right <= limit) {
				// if the left and right weights sum is less than or equal to the limit
				// then decrease both frequencies by 1
				frequency[left]--;
				frequency[right]--;
			} else {
				// the weights sum is greater than limit, only decrease the right frequency by 1
				frequency[right]--;
			}
			// increase number of boats
			numOfBoats++;
		}
		return numOfBoats;
	}

	private static void check(int[] people, int limit, int expected) {
		System.out.println("people is: " + Arrays.toString(people));
		System.out.println("limit is: " + limit);
		System.out.println("expected is: " + expected);
		int numRescueBoats = numRescueBoats(people, limit); // Calls your implementation
		System.out.println("numRescueBoats is: " + numRescueBoats);
	}
}
