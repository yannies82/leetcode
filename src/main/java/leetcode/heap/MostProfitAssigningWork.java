package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MostProfitAssigningWork {

	public static void main(String[] args) {
		check(new int[] { 2, 4, 6, 8, 10 }, new int[] { 10, 20, 30, 40, 50 }, new int[] { 4, 5, 6, 7 }, 100);
		check(new int[] { 85, 47, 57 }, new int[] { 24, 66, 99 }, new int[] { 40, 25, 25 }, 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/most-profit-assigning-work.
	 * Time complexity is O(n + m) where n is the length of the difficulty array and
	 * m is the length of the worker array.
	 * 
	 * @param difficulty
	 * @param profit
	 * @param worker
	 * @return
	 */
	public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		// find the max ability of all workers
		int maxAbility = -1;
		for (int i = 0; i < worker.length; i++) {
			maxAbility = Math.max(worker[i], maxAbility);
		}
		// create an array to map difficulty of jobs to profit
		// in case the same difficulty exists twice in the array, keep the one
		// with the largest profit
		int[] jobs = new int[maxAbility + 1];
		for (int i = 0; i < difficulty.length; i++) {
			if (difficulty[i] <= maxAbility) {
				jobs[difficulty[i]] = Math.max(jobs[difficulty[i]], profit[i]);
			}
		}

		// iterate the jobs array and calculate the profit for all difficulties up to
		// max ability
		// make sure that a job with higher difficulty cannot have less profit
		// than a job with smaller difficulty
		for (int i = 1; i <= maxAbility; i++) {
			jobs[i] = Math.max(jobs[i], jobs[i - 1]);
		}

		// calculate the profit sum for all workers
		int sum = 0;
		for (int i = 0; i < worker.length; i++) {
			sum += jobs[worker[i]];
		}
		return sum;
	}

	/**
	 * Alternate solution which uses a priority queue. Time complexity is O(nlogn +
	 * mlogm) where n is the length of the difficulty array and m is the length of
	 * the worker array.
	 * 
	 * @param difficulty
	 * @param profit
	 * @param worker
	 * @return
	 */
	public static int maxProfitAssignment2(int[] difficulty, int[] profit, int[] worker) {
		// add difficulty and profit pairs to the priority queue, sort by
		// profit descending
		Queue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		for (int i = 0; i < difficulty.length; i++) {
			queue.offer(new int[] { difficulty[i], profit[i] });
		}
		// sort workers array by ability
		Arrays.sort(worker);
		int sum = 0;
		int i = worker.length - 1;
		// iterate both the queue and the worker array
		while (!queue.isEmpty() && i >= 0) {
			// take the element from the queue with the max profit
			// and add the profit as many times as the workers with
			// ability at least as much as the job difficulty
			int[] currentJob = queue.poll();
			int jobDifficulty = currentJob[0];
			while (i >= 0 && jobDifficulty <= worker[i]) {
				sum += currentJob[1];
				i--;
			}
		}
		return sum;
	}

	private static void check(int[] difficulty, int[] profit, int[] worker, int expected) {
		System.out.println("difficulty is: " + Arrays.toString(difficulty));
		System.out.println("profit is: " + Arrays.toString(profit));
		System.out.println("worker is: " + Arrays.toString(worker));
		System.out.println("expected is: " + expected);
		int maxProfitAssignment = maxProfitAssignment(difficulty, profit, worker); // Calls your implementation
		System.out.println("maxProfitAssignment is: " + maxProfitAssignment);
	}
}
