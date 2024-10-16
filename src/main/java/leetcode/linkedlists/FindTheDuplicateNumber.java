package leetcode.linkedlists;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindTheDuplicateNumber {

	public static void main(String[] args) {
		check(new int[] { 1, 3, 4, 2, 2 }, 2);
		check(new int[] { 3, 1, 3, 4, 2 }, 3);
		check(new int[] { 3, 3, 3, 3, 3 }, 3);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-the-duplicate-number.
	 * This solution treats the array values as indexes and the array as a linked
	 * list and tries to detect the cycle. Time complexity is O(n) where n is the
	 * length of the nums array and space complexity is O(1).
	 * 
	 * @param nums
	 * @return
	 */
	public static int findDuplicate(int[] nums) {
		int slow = 0;
		int fast = 0;
		// detect cycle
		do {
			slow = nums[slow];
			fast = nums[nums[fast]];
		} while (slow != fast);
		// the slow pointer moved a positions and the fast pointer moved 2 * a positions
		// a should be a multiple of the circumference of the cycle
		// therefore if the distance from start until the first node which belongs to
		// the cycle is x then the distance from where the slow and fast pointers met
		// until the same node should also be x
		// reset slow pointer
		slow = 0;
		// iterate slow and fast pointers until they are the same
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
	}

	/**
	 * Simple approach using hashset. Time complexity is O(n) where n is the length
	 * of the array and space complexity is O(n).
	 * 
	 * @param nums
	 * @return
	 */
	public static int findDuplicate2(int[] nums) {
		int n = nums.length;
		Set<Integer> numSet = new HashSet<>();
		for (int i = 0; i < n; i++) {
			if (!numSet.add(nums[i])) {
				return nums[i];
			}
		}
		return -1;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("gas is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int findDuplicate = findDuplicate(nums); // Calls your implementation
		System.out.println("findDuplicate is: " + findDuplicate);
	}
}
