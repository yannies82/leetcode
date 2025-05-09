package leetcode.array.frequency;

import java.util.Arrays;

public class MinimumNumberOfMovesToSeatEveryone {

	public static void main(String[] args) {
		check(new int[] { 3, 1, 5 }, new int[] { 2, 7, 4 }, 4);
		check(new int[] { 4, 1, 5, 9 }, new int[] { 1, 3, 2, 6 }, 7);
		check(new int[] { 2, 2, 6, 6 }, new int[] { 1, 3, 2, 6 }, 4);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone. This
	 * solution counts the frequency of seats and students, then iterates the
	 * frequencies to calculate the min difference. Time complexity is O(n) where n
	 * is the length of the arrays.
	 * 
	 * @param seats
	 * @param students
	 * @return
	 */
	public static int minMovesToSeat(int[] seats, int[] students) {
		int range = 101;
		int[] freqSeats = new int[range];
		int[] freqStudents = new int[range];
		for (int i = 0; i < seats.length; i++) {
			freqSeats[seats[i]]++;
			freqStudents[students[i]]++;
		}
		int sum = 0;
		int index1 = 0;
		int index2 = 0;
		while (index1 < range && index2 < range) {
			int minCount = Math.min(freqSeats[index1], freqStudents[index2]);
			sum += minCount * Math.abs(index2 - index1);
			freqSeats[index1] -= minCount;
			freqStudents[index2] -= minCount;
			while (index1 < range && freqSeats[index1] == 0) {
				index1++;
			}
			while (index2 < range && freqStudents[index2] == 0) {
				index2++;
			}
		}
		return sum;
	}

	/**
	 * This solution sorts the input arrays, then matches every seat to the student
	 * of the same index. Time complexity is O(n*logn) where n is the length of the
	 * arrays.
	 * 
	 * @param seats
	 * @param students
	 * @return
	 */
	public static int minMovesToSeat2(int[] seats, int[] students) {
		Arrays.sort(seats);
		Arrays.sort(students);
		int sum = 0;
		for (int i = 0; i < seats.length; i++) {
			sum += Math.abs(seats[i] - students[i]);
		}
		return sum;
	}

	private static void check(int[] seats, int[] students, int expected) {
		System.out.println("seats is: " + Arrays.toString(seats));
		System.out.println("students is: " + Arrays.toString(students));
		System.out.println("expected is: " + expected);
		int minMovesToSeat = minMovesToSeat(seats, students); // Calls your implementation
		System.out.println("minMovesToSeat is: " + minMovesToSeat);
	}
}
