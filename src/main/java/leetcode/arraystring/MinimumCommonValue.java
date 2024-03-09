package leetcode.arraystring;

public class MinimumCommonValue {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3 }, new int[] { 2, 4 }, 2);
		check(new int[] { 1, 2, 3, 6 }, new int[] { 2, 3, 4, 5 }, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/minimum-common-value. Time
	 * complexity is O(n) where n is the min length between nums1 and nums2.
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int getCommon(int[] nums1, int[] nums2) {
		int index1 = 0;
		int index2 = 0;
		while (index1 < nums1.length && index2 < nums2.length) {
			if (nums1[index1] == nums2[index2]) {
				return nums1[index1];
			} else if (nums1[index1] > nums2[index2]) {
				index2++;
			} else {
				index1++;
			}
		}
		return -1;
	}

	private static void check(int[] nums1, int[] nums2, int expected) {
		System.out.println("nums1 is: " + nums1);
		System.out.println("nums2 is: " + nums2);
		System.out.println("expected is: " + expected);
		int getCommon = getCommon(nums1, nums2); // Calls your implementation
		System.out.println("getCommon is: " + getCommon);
	}
}
