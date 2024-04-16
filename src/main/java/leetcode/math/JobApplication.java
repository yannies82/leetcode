package leetcode.math;

import java.util.Comparator;
import java.util.PriorityQueue;

public class JobApplication {

	public static void main(String[] args) {
		int salaryOfferedPerMonth = 3500; // this will not do it, please adjust
		int workHoursPerDay = 12; // you can do better than that
		if (salaryIsAdequate(salaryOfferedPerMonth) && workLifeBalanceExists(workHoursPerDay)) {
			System.out.println("We can arrange a short call to discuss about this position");
			System.out.println("My phone number is: " + findKthLargest(new int[] { 255, 69, 36, 87 }, 3)
					+ trailingZeroes(1887) + romanToInt("LXII") + (climbStairs(10) + 1)
					+ majorityElement(new int[] { 1, 2, 3, 7, 7, 5, 7, 7, 7, 2 }));
		} else {
			System.out.println("I'm not interested, however it was nice to almost meet you!");
		}
	}

	/**
	 * Feeble attempt at required salary obfuscation.
	 * 
	 * @param salaryOffered
	 * @return
	 */
	private static boolean salaryIsAdequate(int salaryOffered) {
		return (salaryOffered - 0b1000001101000) >>> 31 == 0;
	}

	/**
	 * While it isn't obvious why, the answer to this one is obvious.
	 * 
	 * @param workHoursPerDay
	 * @return
	 */
	private static boolean workLifeBalanceExists(int workHoursPerDay) {
		return ((workHoursPerDay - 1) << 29 >>> 29) + 1 == workHoursPerDay;
	}

	/**
	 * Calculates the kth largest number of the ones in the provided array. This
	 * implementation uses a priority queue as a heap and has a time complexity of
	 * O(n*logn).
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	private static int findKthLargest(int[] nums, int k) {
		// initialize the priority queue with descending ordering
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
		// add all numbers to the queue
		for (int i = 0; i < nums.length; i++) {
			priorityQueue.add(nums[i]);
		}
		// poll first k-1 numbers from the queue
		for (int i = 1; i < k; i++) {
			priorityQueue.poll();
		}
		return priorityQueue.peek();
	}

	/**
	 * Calculates the number of trailing zeroes that exist in the factorial of n
	 * (n!). Time complexity is O(logn).
	 * 
	 * 
	 * @param n
	 * @return
	 */
	private static int trailingZeroes(int n) {
		int factor = 1;
		int count = 0;
		do {
			factor *= 5;
			count += n / factor;
		} while (factor < n);
		return count;
	}

	/**
	 * Converts a roman numeral to an integer number. Time complexity is O(n) where
	 * n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	private static int romanToInt(String s) {
		int length = s.length();
		int sum = 0;
		for (int i = 0; i < length; i++) {
			// calculate character value and add to the total sum
			// for I, X, C also check the next character
			sum += switch (s.charAt(i)) {
			case 'I' -> (i == length - 1 || (s.charAt(i + 1) != 'V' && s.charAt(i + 1) != 'X')) ? 1 : -1;
			case 'V' -> 5;
			case 'X' -> (i == length - 1 || (s.charAt(i + 1) != 'L' && s.charAt(i + 1) != 'C')) ? 10 : -10;
			case 'L' -> 50;
			case 'C' -> (i == length - 1 || (s.charAt(i + 1) != 'D' && s.charAt(i + 1) != 'M')) ? 100 : -100;
			case 'D' -> 500;
			case 'M' -> 1000;
			default -> throw new IllegalArgumentException("Unexpected value: " + s.charAt(i));
			};
		}
		return sum;
	}

	/**
	 * There is a staircase of n steps and we can climb one step or two steps at a
	 * time. This method uses bottom up dynamic programming to find the count of all
	 * the distinct ways to climb the staircase. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	private static int climbStairs(int n) {
		// early exit for n == 1 or n == 2
		if (n == 1 || n == 2) {
			return n;
		}
		// this array caches the intermediate results
		int[] dpArray = new int[n + 1];
		// set the known values for n = 1 and n = 2
		dpArray[1] = 1;
		dpArray[2] = 2;
		// calculate all dpArray values bottom up, up to n
		for (int i = 3; i <= n; i++) {
			dpArray[i] = dpArray[i - 1] + dpArray[i - 2];
		}
		return dpArray[n];
	}

	/**
	 * Calculates the element that appears most times in the array using the Moore's
	 * voting algorithm. Time complexity is O(n) where n is the length of the nums
	 * array.
	 * 
	 * @param nums
	 * @return
	 */
	private static int majorityElement(int[] nums) {
		int count = 1;
		int candidate = nums[0];
		int n = nums.length;
		for (int i = 1; i < n; i++) {
			if (candidate == nums[i]) {
				count++;
			} else if (count == 1) {
				candidate = nums[i];
			} else {
				count--;
			}
		}
		return candidate;
	}

}
