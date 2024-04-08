package leetcode.arraystring;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class NumberOfStudentsUnableToEatLunch {

	public static void main(String[] args) {
		check(new int[] { 1, 1, 0, 0 }, new int[] { 0, 1, 0, 1 }, 0);
		check(new int[] { 1, 1, 1, 0, 0, 1 }, new int[] { 1, 0, 0, 0, 1, 1 }, 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/number-of-students-unable-to-eat-lunch. This
	 * solution counts both types of sandwiches that the students want. It then
	 * iterates over the available sandwiches array and decreases the counts while
	 * they are greater than 0. If a count becomes 0 then the remaining students
	 * cannot be serviced. Time complexity is O(n) where n is the length of the
	 * arrays.
	 * 
	 * @param students
	 * @param sandwiches
	 * @return
	 */
	public static int countStudents(int[] students, int[] sandwiches) {
		int circular = 0;
		int square = 0;
		// iterate all students and keep the count of the sandwiches they want
		for (int i = 0; i < students.length; i++) {
			circular += students[i];
			square += 1 - students[i];
		}
		// iterate all sandwiches
		for (int i = 0; i < sandwiches.length; i++) {
			if (sandwiches[i] == 1 && circular > 0) {
				// decrease circular sandwiches count if there are any left
				circular--;
			} else if (sandwiches[i] == 0 && square > 0) {
				// decrease square sandwiches count if there are any left
				square--;
			} else {
				// if no more circular or square sandwiches are left and cannot be decreased
				// return the remaining requested circular and square sandwiches
				return circular + square;
			}
		}
		// all students have been served, return 0
		return 0;
	}

	/**
	 * Alternate solution which uses a queue for the requested student sandwiches.
	 * Time complexity is O(n) where n is the length of the arrays.
	 * 
	 * @param students
	 * @param sandwiches
	 * @return
	 */
	public static int countStudents2(int[] students, int[] sandwiches) {
		Queue<Integer> studentsQueue = new ArrayDeque<>();
		for (int i = 0; i < students.length; i++) {
			studentsQueue.offer(students[i]);
		}
		int sandwichesIndex = 0;
		while (!studentsQueue.isEmpty()) {
			int length = studentsQueue.size();
			for (int i = 0; i < length; i++) {
				int current = studentsQueue.poll();
				if (current == sandwiches[sandwichesIndex]) {
					sandwichesIndex++;
				} else {
					studentsQueue.offer(current);
				}
			}
			if (length == studentsQueue.size()) {
				return length;
			}
		}
		return 0;
	}

	private static void check(int[] students, int[] sandwiches, int expected) {
		System.out.println("students is: " + Arrays.toString(students));
		System.out.println("sandwiches is: " + Arrays.toString(sandwiches));
		System.out.println("expected is: " + expected);
		int countStudents = countStudents(students, sandwiches); // Calls your implementation
		System.out.println("countStudents is: " + countStudents);
	}
}
