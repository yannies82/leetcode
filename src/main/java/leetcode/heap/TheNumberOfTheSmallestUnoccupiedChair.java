package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

public class TheNumberOfTheSmallestUnoccupiedChair {

	public static void main(String[] args) {
		check(new int[][] { { 1, 4 }, { 2, 3 }, { 4, 6 } }, 1, 1);
		check(new int[][] { { 3, 10 }, { 1, 5 }, { 2, 6 } }, 0, 2);
		check(new int[][] { { 3, 5 }, { 1, 4 }, { 4, 6 }, { 2, 3 } }, 2, 0);
		check(new int[][] { { 33889, 98676 }, { 80071, 89737 }, { 44118, 52565 }, { 52992, 84310 }, { 78492, 88209 },
				{ 21695, 67063 }, { 84622, 95452 }, { 98048, 98856 }, { 98411, 99433 }, { 55333, 56548 },
				{ 65375, 88566 }, { 55011, 62821 }, { 48548, 48656 }, { 87396, 94825 }, { 55273, 81868 },
				{ 75629, 91467 } }, 6, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair.
	 * This solution iterates the times array in arrival date order and keeps a
	 * sorted set for the available chairs and a priority queue for the arriving
	 * friends by their leaving time. Time complexity is O(nlogn) where n is the
	 * length of the times array.
	 * 
	 * @param times
	 * @param targetFriend
	 * @return
	 */
	public static int smallestChair(int[][] times, int targetFriend) {
		// keeps indexes of times array sorted by arrival time
		Integer[] sortedIndexes = new Integer[times.length];
		for (int i = 0; i < times.length; i++) {
			sortedIndexes[i] = i;
		}
		Arrays.sort(sortedIndexes, (i, j) -> Integer.compare(times[i][0], times[j][0]));
		// keeps the arriving friends sorted by their leaving time
		Queue<int[]> leavingTimeQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
		SortedSet<Integer> vacatedChairs = new TreeSet<>();
		// iterate the times array sorted by arrival time, stop after processing the
		// targetFriend index
		int i = -1;
		int nextChair = -1;
		int chairIndex = 0;
		do {
			int index = sortedIndexes[++i];
			// return chairs of friends who have left by this arrival time to the available
			// chairs
			while (!leavingTimeQueue.isEmpty() && times[index][0] >= leavingTimeQueue.peek()[0]) {
				vacatedChairs.add(leavingTimeQueue.poll()[1]);
			}
			// assign the next available chair to the friend who arrived now
			// assign a vacated chair if there is one, otherwise assign a new chair
			if (!vacatedChairs.isEmpty()) {
				nextChair = vacatedChairs.first();
				vacatedChairs.remove(nextChair);
			} else {
				nextChair = chairIndex++;
			}
			leavingTimeQueue.offer(new int[] { times[index][1], nextChair });
		} while (sortedIndexes[i] != targetFriend);
		// since the loop stopped after the targetFriend index was processed, the value
		// of nextChair is
		// the index of the chair that was assigned to him
		return nextChair;
	}

	/**
	 * This solution iterates the times array in arrival date order and keeps two
	 * priority queues, one for the available chairs and one for the arriving
	 * friends by their leaving time. Time complexity is O(nlogn) where n is the
	 * length of the times array.
	 * 
	 * @param times
	 * @param targetFriend
	 * @return
	 */
	public static int smallestChair2(int[][] times, int targetFriend) {
		// keeps indexes of times array sorted by arrival time
		Integer[] sortedIndexes = new Integer[times.length];
		// initialize a queue with the available chairs
		Queue<Integer> chairsQueue = new PriorityQueue<>();
		for (int i = 0; i < times.length; i++) {
			sortedIndexes[i] = i;
			chairsQueue.offer(i);
		}
		Arrays.sort(sortedIndexes, (i, j) -> Integer.compare(times[i][0], times[j][0]));
		// keeps the arriving friends sorted by their leaving time
		Queue<int[]> leavingTimeQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
		// iterate the times array sorted by arrival time, stop after processing the
		// targetFriend index
		int i = -1;
		int nextChair = -1;
		do {
			int index = sortedIndexes[++i];
			// return chairs of friends who have left by this arrival time to the available
			// chairs
			while (!leavingTimeQueue.isEmpty() && times[index][0] >= leavingTimeQueue.peek()[0]) {
				chairsQueue.offer(leavingTimeQueue.poll()[1]);
			}
			// assign the next available chair to the friend who arrived now
			nextChair = chairsQueue.poll();
			leavingTimeQueue.offer(new int[] { times[index][1], nextChair });
		} while (sortedIndexes[i] != targetFriend);
		// since the loop stopped after the targetFriend index was processed, the value
		// of nextChair is
		// the index of the chair that was assigned to him
		return nextChair;
	}

	private static void check(int[][] times, int targetFriend, int expected) {
		System.out.println("times is: ");
		for (int[] time : times) {
			System.out.println(Arrays.toString(time));
		}
		System.out.println("expected is: " + expected);
		int smallestChair = smallestChair(times, targetFriend); // Calls your implementation
		System.out.println("smallestChair is: " + smallestChair);
	}
}
