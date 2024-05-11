package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumCostToHireKWorkers {

	public static void main(String[] args) {
		check(new int[] { 10, 20, 5 }, new int[] { 70, 50, 30 }, 2, 105);
		check(new int[] { 3, 1, 10, 10, 1 }, new int[] { 4, 8, 2, 2, 7 }, 3, 30.66667);
	}

	/**
	 * 
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-cost-to-hire-k-workers. This solution
	 * calculates the ratio of wage to quality for each worker, then keeps this
	 * information in a sorted array. It then maintains a priority queue to keep the
	 * minimum wage for k workers. Time complexity is O(n*logn) where n is the
	 * length of the quality and wage arrays, (i.e. the number of workers).
	 * 
	 * @param quality
	 * @param wage
	 * @param k
	 * @return
	 */
	public static double mincostToHireWorkers(int[] quality, int[] wage, int k) {
		int length = quality.length;
		// generate array which keeps the ratio of wage / quality along with the wage
		// and quality
		double[][] ratio = new double[length][3];
		for (int i = 0; i < length; i++) {
			ratio[i] = new double[] { (double) wage[i] / quality[i], wage[i], quality[i] };
		}
		// sort the generated array by ratio ascending
		Arrays.sort(ratio, (a, b) -> Double.compare((double) a[0], (double) b[0]));
		// maintain a priority queue for keeping the minimum k qualities
		Queue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);
		int qualitySum = 0;
		double minWageCost = Double.MAX_VALUE;
		// iterate the sorted ratios array
		for (int i = 0; i < length; i++) {
			// add the quality of the ratio to the quality sum and to the priority queue
			qualitySum += (int) ratio[i][2];
			priorityQueue.offer((int) ratio[i][2]);
			// if k elements have been added to the queue, check if picking the current
			// worker
			// leads to a lower minimum wage cost
			if (priorityQueue.size() == k) {
				// the ratio of the current worker is higher, therefore all previous qualities
				// will be multiplied with the new ratio to calculate the new min cost
				// then we compare the new cost (which means picking the current worker) to
				// the previous one and we choose the min of the two
				minWageCost = Math.min((qualitySum * ratio[i][0]), minWageCost);
				// remove the max quality from the queue and subtract it from the quality sum
				// so that the cost is kept to a minimum after the qualitySum is multiplied
				// with the ratio of the next worker
				qualitySum -= priorityQueue.poll();
			}
		}
		return minWageCost;
	}

	private static void check(int[] quality, int[] wage, int k, double expected) {
		System.out.println("quality is: " + Arrays.toString(quality));
		System.out.println("wage is: " + Arrays.toString(wage));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		double mincostToHireWorkers = mincostToHireWorkers(quality, wage, k); // Calls your implementation
		System.out.println("mincostToHireWorkers is: " + mincostToHireWorkers);
	}
}
