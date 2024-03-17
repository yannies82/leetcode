package leetcode.intervals;

import java.util.Arrays;

public class InsertInterval {

	public static void main(String[] args) {
		check(new int[][] { { 1, 5 } }, new int[] { 0, 0 }, new int[][] { { 0, 0 }, { 1, 5 } });
		check(new int[][] { { 1, 5 } }, new int[] { 6, 8 }, new int[][] { { 1, 5 }, { 6, 8 } });
		check(new int[][] {}, new int[] { 2, 5 }, new int[][] { { 2, 5 } });
		check(new int[][] { { 1, 3 }, { 6, 9 } }, new int[] { 2, 5 }, new int[][] { { 1, 5 }, { 6, 9 } });
		check(new int[][] { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } }, new int[] { 4, 8 },
				new int[][] { { 1, 2 }, { 3, 10 }, { 12, 16 } });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/insert-interval. This
	 * solution performs binary search to find the position in the array where the
	 * new interval will be inserted. It then calculates the size of the new array
	 * and invokes System arraycopy to copy the intervals to the target array. Time
	 * complexity is O(n) where n is the length of the intervals array.
	 * 
	 * @param intervals
	 * @param newInterval
	 * @return
	 */
	public static int[][] insert(int[][] intervals, int[] newInterval) {
		int intervalsCount = intervals.length;
		if (intervalsCount == 0) {
			// early exit for the case
			return new int[][] { newInterval };
		}
		// perform binary search to find the insertion index for the new interval
		int low = 0;
		int high = intervalsCount - 1;
		int mid = 0;
		while ((mid = (low + high) / 2) > low) {
			if (newInterval[0] > intervals[mid][1]) {
				low = mid;
			} else {
				high = mid;
			}
		}
		// adjust the insertion index start according to the overlapping intervals
		// the insertion index start will be the index of the first interval whose end
		// is greater than
		// or equal to the new interval's start
		int insertionIndexStart = Math.max(low - 1, 0);
		while (insertionIndexStart < intervalsCount && newInterval[0] > intervals[insertionIndexStart][1]) {
			insertionIndexStart++;
		}
		// calculate the insertion index end according to the overlapping intervals
		// the insertion index end will be the index of the first interval whose start
		// is
		// greater than
		// the end of the new interval
		int insertionIndexEnd = insertionIndexStart;
		while (insertionIndexEnd < intervalsCount && newInterval[1] >= intervals[insertionIndexEnd][0]) {
			insertionIndexEnd++;
		}
		// here we allocate the result array
		// the length of the target array will depend on the number of merged intervals
		// if insertionIndexStart == insertionIndexEnd it means that there is no overlap
		// of intervals and the new array will have length of intervals.length + 1
		// if insertionIndexEnd > insertionIndexStart it means that the new interval
		// will be merged with insertionIndexEnd - insertionIndexStart existing
		// intervals and they will be subtracted from the result array length
		int[][] result = new int[intervalsCount - insertionIndexEnd + insertionIndexStart + 1][2];
		// copy all intervals from intervals array to the result array until
		// insertionIndexStart (exclusive)
		System.arraycopy(intervals, 0, result, 0, insertionIndexStart);
		// insert the (possibly) merged interval to the result at insertionIndexStart
		if (insertionIndexStart == intervalsCount) {
			// if insertionIndexStart is at the end of the array, just add the new interval
			// there
			result[insertionIndexStart][0] = newInterval[0];
		} else {
			// insert merged interval start
			result[insertionIndexStart][0] = Math.min(newInterval[0], intervals[insertionIndexStart][0]);
		}
		// if insertion index end is at the start of the intervals array just insert the
		// new interval there
		// otherwise insert merged interval end
		result[insertionIndexStart][1] = insertionIndexEnd == 0 ? newInterval[1]
				: Math.max(newInterval[1], intervals[insertionIndexEnd - 1][1]);
		// copy all intervals from intervals array starting from insertion index end
		// (inclusive) and until the end
		System.arraycopy(intervals, insertionIndexEnd, result, insertionIndexStart + 1,
				intervalsCount - insertionIndexEnd);

		return result;
	}

	public static int[][] insert2(int[][] intervals, int[] newInterval) {
		int intervalsCount = intervals.length;
		if (intervalsCount == 0) {
			return new int[][] { newInterval };
		}
		int insertionIndexStart = 0;
		while (insertionIndexStart < intervalsCount && newInterval[0] > intervals[insertionIndexStart][1]) {
			insertionIndexStart++;
		}
		int insertionIndexEnd = insertionIndexStart;
		while (insertionIndexEnd < intervalsCount && newInterval[1] >= intervals[insertionIndexEnd][0]) {
			insertionIndexEnd++;
		}
		int[][] result = new int[intervalsCount - insertionIndexEnd + insertionIndexStart + 1][2];
		System.arraycopy(intervals, 0, result, 0, insertionIndexStart);
		if (insertionIndexStart == intervalsCount) {
			result[insertionIndexStart][0] = newInterval[0];
		} else {
			result[insertionIndexStart][0] = Math.min(newInterval[0], intervals[insertionIndexStart][0]);
		}
		result[insertionIndexStart][1] = insertionIndexEnd == 0 ? newInterval[1]
				: Math.max(newInterval[1], intervals[insertionIndexEnd - 1][1]);
		System.arraycopy(intervals, insertionIndexEnd, result, insertionIndexStart + 1,
				intervalsCount - insertionIndexEnd);

		return result;
	}

	private static void check(int[][] intervals, int[] newInterval, int[][] expectedIntervals) {
		System.out.println("intervals is: ");
		for (int[] interval : intervals) {
			System.out.println(Arrays.toString(interval));
		}
		System.out.println("newInterval is: " + Arrays.toString(newInterval));
		System.out.println("expectedIntervals is: ");
		for (int[] interval : expectedIntervals) {
			System.out.println(Arrays.toString(interval));
		}
		int[][] insert = insert(intervals, newInterval); // Calls your implementation
		System.out.println("insert is: ");
		for (int[] interval : insert) {
			System.out.println(Arrays.toString(interval));
		}
	}
}
