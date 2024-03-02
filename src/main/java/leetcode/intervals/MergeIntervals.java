package leetcode.intervals;

import java.util.Arrays;

public class MergeIntervals {

	public static void main(String[] args) {
		check(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } },
				new int[][] { { 1, 6 }, { 8, 10 }, { 15, 18 } });
		check(new int[][] { { 1, 4 }, { 0, 4 } }, new int[][] { { 0, 4 } });
		check(new int[][] { { 1, 4 }, { 4, 5 } }, new int[][] { { 1, 5 } });
	}

	public static int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
		int intervalsCount = intervals.length;
		int offset = 0;
		for (int i = 1; i < intervalsCount; i++) {
			if (intervals[i][0] <= intervals[offset][1]) {
				intervals[offset][1] = Math.max(intervals[offset][1], intervals[i][1]);
			} else {
				intervals[++offset] = intervals[i];
			}
		}
		int[][] result = new int[offset + 1][2];
		System.arraycopy(intervals, 0, result, 0, offset + 1);
		return result;
	}

	private static void check(int[][] intervals, int[][] expectedIntervals) {
		System.out.println("intervals is: ");
		for (int[] interval : intervals) {
			System.out.println(Arrays.toString(interval));
		}
		System.out.println("expectedIntervals is: ");
		for (int[] interval : expectedIntervals) {
			System.out.println(Arrays.toString(interval));
		}
		int[][] merge = merge(intervals); // Calls your implementation
		System.out.println("merge is: ");
		for (int[] interval : merge) {
			System.out.println(Arrays.toString(interval));
		}
	}
}
