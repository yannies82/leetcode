package leetcode.binarysearchtree;

public class KthSmallestElementInBST {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(3, new TreeNode(1, null, new TreeNode(4)), new TreeNode(2));
		check(tree1, 1, 1);
		tree1 = new TreeNode(5, new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4)),
				new TreeNode(6));
		check(tree1, 1, 1);
	}

	/**
	 * Recursive solution, using inorder traversal.
	 * 
	 * @param root
	 * @return
	 */
	public static int kthSmallest(TreeNode root, int k) {
		// first element of the array is the counter
		// second element is the kth smallest
		int[] arr = { 0, 0 };
		// traverse the tree inorder
		traverse(root, arr, k);
		return arr[1];
	}

	private static void traverse(TreeNode node, int[] arr, int k) {
		if (arr[1] > 0) {
			return;
		}
		if (node.left != null) {
			traverse(node.left, arr, k);
		}
		// increment counter, if it is equal to k set
		// current element value to array and return
		if (++arr[0] == k) {
			arr[1] = node.val;
			return;
		}
		if (node.right != null) {
			traverse(node.right, arr, k);
		}
	}

	private static void check(TreeNode root, int k, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int kthSmallest = kthSmallest(root, k);
		System.out.println("kthSmallest is: " + kthSmallest);
	}

}
