package leetcode.multidimensionaldynamicprogramming;

public class StudentAttendanceRecord2 {

	public static void main(String[] args) {
		check(4, 43);
		check(3, 19);
		check(2, 8);
		check(1, 3);
		check(10101, 183236316);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/student-attendance-record-ii.
	 * This solution uses bottom up multidimensional dynamic programming to
	 * calculate all solutions for all subproblems up to n. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int checkRecord(int n) {
		int mod = 1000000007; // the mod number as defined in the problem
		// this array keeps the solutions for the subproblems for the ith day with j
		// absences and k trailing late arrivals
		long[][][] dpArray = new long[n + 1][2][3];

		// base cases
		dpArray[1][0][0] = 1; // solution after first day where the action was 'present'
		dpArray[1][1][0] = 1; // solution after first day where the action was 'absent'
		dpArray[1][0][1] = 1; // solution after first day where the action was 'late arrival'

		// populate the dpArray bottom up
		for (int i = 2; i <= n; i++) {
			// calculate solution to current subproblems from previous subproblem solutions

			// solutions for current subproblems when the current action is late arrival
			// same as previous solutions with 0 absences and 0 trailing late arrivals
			dpArray[i][0][1] = dpArray[i - 1][0][0];
			// same as previous solutions with 0 absences and 1 trailing late arrivals
			dpArray[i][0][2] = dpArray[i - 1][0][1];
			// same as previous solutions with 1 absences and 0 trailing late arrivals
			dpArray[i][1][1] = dpArray[i - 1][1][0];
			// same as previous solutions with 1 absences and 1 trailing late arrivals
			dpArray[i][1][2] = dpArray[i - 1][1][1];

			// solutions for current subproblems when the current action is absence
			// it is the sum of the solutions for the previous subproblem with 0 absences
			dpArray[i][1][0] = (dpArray[i - 1][0][0] + dpArray[i - 1][0][1] + dpArray[i - 1][0][2]) % mod;

			// solutions for current subproblems when the current action is presence
			// the solution for 0 absences is the same as the one where the action is
			// absence
			dpArray[i][0][0] = dpArray[i][1][0];
			// the solution for 1 absence and current action presence is the sum of the
			// current solution for 1 absence and the sum of the previous solutions for
			// 1 absences
			dpArray[i][1][0] = (dpArray[i][1][0] + dpArray[i - 1][1][0] + dpArray[i - 1][1][1] + dpArray[i - 1][1][2])
					% mod;
		}

		// add all current subproblem solutions
		int result = (int) ((dpArray[n][0][0] + dpArray[n][0][1] + dpArray[n][0][2] + dpArray[n][1][0]
				+ dpArray[n][1][1] + dpArray[n][1][2]) % mod);
		return (int) result;
	}

	/**
	 * Simple recursive solution. Time complexity is O(3^n).
	 * 
	 * @param n
	 * @return
	 */
	public static int checkRecord2(int n) {
		return checkRecordRecursive2(n, 0, 0, 0);
	}

	private static int checkRecordRecursive2(int n, int absentCount, int recentLateCount, int index) {
		if (index == n) {
			// we have reached the end of the attendance record, it is valid
			// increase the number of valid results
			return 1;
		}

		int currentResult = checkRecordRecursive2(n, absentCount, 0, index + 1);

		if (absentCount == 0) {
			currentResult += checkRecordRecursive2(n, 1, 0, index + 1);
		}

		if (recentLateCount < 2) {
			currentResult += checkRecordRecursive2(n, absentCount, recentLateCount + 1, index + 1);
		}
		return currentResult;
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int checkRecord = checkRecord(n); // Calls your implementation
		System.out.println("checkRecord is: " + checkRecord);
	}
}
