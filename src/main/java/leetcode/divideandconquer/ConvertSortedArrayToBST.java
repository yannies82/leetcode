package leetcode.divideandconquer;

import java.util.Arrays;

public class ConvertSortedArrayToBST {

	public static void main(String[] args) {
		int[] nums0 = { -10, -3, 0, 5, 9 };
		TreeNode expected0 = new TreeNode(0, new TreeNode(-10, null, new TreeNode(-3)),
				new TreeNode(5, null, new TreeNode(9)));
		check(nums0, expected0);
		int[] nums1 = { 1, 3 };
		TreeNode expected1 = new TreeNode(1, null, new TreeNode(3));
		check(nums1, expected1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree.
	 * This solution uses divide and conquer strategy. It partitions the problem
	 * into two sub problems, one for the left subtree and one for the right
	 * subtree. It then solves these problems recursively. Time complexity is O(N)
	 * where N is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static TreeNode sortedArrayToBST(int[] nums) {
		return solve(nums, 0, nums.length - 1);
	}

	private static TreeNode solve(int[] nums, int start, int end) {
		if (start > end) {
			return null;
		}
		// find the middle element
		int mid = (end + start) / 2;
		// solve left subtree
		TreeNode left = solve(nums, start, mid - 1);
		// solve right subtree
		TreeNode right = solve(nums, mid + 1, end);
		return new TreeNode(nums[mid], left, right);
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}

		String printAll() {
			TreeNode current = this;
			StringBuilder result = new StringBuilder();
			print(current, result);
			return result.toString();
		}

		void print(TreeNode node, StringBuilder builder) {
			if (!builder.isEmpty()) {
				builder.append(",");
			}
			builder.append(node.val);
			if (node.left == null) {
				builder.append(", null");
			} else {
				print(node.left, builder);
			}
			if (node.right == null) {
				builder.append(", null");
			} else {
				print(node.right, builder);
			}
		}
	}

	private static void check(int[] nums, TreeNode expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected.printAll());
		TreeNode sortedArrayToBST = sortedArrayToBST(nums); // Calls your implementation
		System.out.println("sortedArrayToBST is: " + sortedArrayToBST.printAll());
	}
}
