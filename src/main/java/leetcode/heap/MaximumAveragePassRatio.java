package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumAveragePassRatio {

	public static void main(String[] args) {
		check(new int[][] { { 1, 2 }, { 3, 5 }, { 2, 2 } }, 2, 0.78333d);
		check(new int[][] { { 2, 4 }, { 3, 9 }, { 4, 5 }, { 2, 10 } }, 4, 0.53485d);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/maximum-average-pass-ratio.
	 * This solution uses a priority queue to keep classes by descending diff
	 * between the new pass rate (after adding one student) and the current pass
	 * rate. Time complexity is O((n+k)logn) where n is the length of the classes
	 * array and k is the number of extra students.
	 * 
	 * @param classes
	 * @param extraStudents
	 * @return
	 */
	public static double maxAverageRatio(int[][] classes, int extraStudents) {
		Queue<EnhancedClass> queue = new PriorityQueue<>(
				(a, b) -> a.passRateChange > b.passRateChange ? -1 : b.passRateChange > a.passRateChange ? 1 : 0);
		for (int i = 0; i < classes.length; i++) {
			double currentPassRate = (double) classes[i][0] / classes[i][1];
			double newPassRate = (double) (classes[i][0] + 1) / (classes[i][1] + 1);
			queue.offer(new EnhancedClass(classes[i], newPassRate, newPassRate - currentPassRate));
		}
		while (extraStudents-- > 0) {
			EnhancedClass current = queue.poll();
			double currentPassRate = current.newPassRate;
			current.classInfo[0]++;
			current.classInfo[1]++;
			current.newPassRate = (double) (current.classInfo[0] + 1) / (current.classInfo[1] + 1);
			current.passRateChange = current.newPassRate - currentPassRate;
			queue.offer(current);
		}
		double sum = 0;
		for (int i = 0; i < classes.length; i++) {
			sum += (double) classes[i][0] / classes[i][1];
		}
		return sum / classes.length;
	}

	private static class EnhancedClass {
		int[] classInfo;
		double newPassRate;
		double passRateChange;

		EnhancedClass(int[] classInfo, double newPassRate, double passRateChange) {
			super();
			this.classInfo = classInfo;
			this.newPassRate = newPassRate;
			this.passRateChange = passRateChange;
		}
	};

	private static void check(int[][] classes, int extraStudents, double expected) {
		System.out.println("classes is: ");
		for (int[] aClass : classes) {
			System.out.println(Arrays.toString(aClass));
		}
		System.out.println("extraStudents is: " + extraStudents);
		System.out.println("expected is: " + expected);
		double maxAverageRatio = maxAverageRatio(classes, extraStudents); // Calls your implementation
		System.out.println("maxAverageRatio is: " + maxAverageRatio);
	}
}
