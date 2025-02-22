package leetcode.binarytreegeneral;

public class RecoverATreeFromPreorderTraversal {

	public static void main(String[] args) {
		check("1-2--3--4-5--6--7", new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)),
				new TreeNode(5, new TreeNode(6), new TreeNode(7))));
		check("1-2--3---4-5--6---7", new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null),
				new TreeNode(5, new TreeNode(6, new TreeNode(7), null), null)));
		check("1-401--349---90--88",
				new TreeNode(1, new TreeNode(401, new TreeNode(349, new TreeNode(90), null), new TreeNode(88)), null));
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/recover-a-tree-from-preorder-traversal. This
	 * solution iterates the string representation and uses a stack to recreate the
	 * tree. Time complexity is O(n) where n is the length of the traversal string.
	 * 
	 * 
	 * @param traversal
	 * @return
	 */
	public static TreeNode recoverFromPreorder(String traversal) {
		int length = traversal.length();
		int[] index = { 0 };
		int rootVal = findVal(traversal, length, index);
		TreeNode root = new TreeNode(rootVal);
		StackItem[] stack = new StackItem[1000];
		int stackIndex = -1;
		TreeNode parent = root;
		int prevDepth = 0;
		while (index[0] < length) {
			int initialIndex = index[0];
			while (traversal.charAt(++index[0]) == '-')
				;
			int depth = index[0] - initialIndex;
			int val = findVal(traversal, length, index);
			TreeNode newNode = new TreeNode(val);
			while (depth <= prevDepth) {
				StackItem prev = stack[stackIndex--];
				parent = prev.node;
				prevDepth = prev.depth;
			}
			if (parent.left == null) {
				parent.left = newNode;
				stack[++stackIndex] = new StackItem(parent, prevDepth);
			} else {
				parent.right = newNode;
			}
			parent = newNode;
			prevDepth = depth;
		}
		return root;
	}

	private record StackItem(TreeNode node, int depth) {
	}

	private static int findVal(String traversal, int length, int[] index) {
		int val = 0;
		char current;
		while (index[0] < length && (current = traversal.charAt(index[0])) != '-') {
			val = val * 10 + current - '0';
			index[0]++;
		}
		return val;
	}

	private static void check(String traversal, TreeNode expected) {
		System.out.println("traversal is: " + traversal);
		System.out.println("expected is: " + expected.printAll());
		TreeNode recoverFromPreorder = recoverFromPreorder(traversal);
		System.out.println("recoverFromPreorder is: " + recoverFromPreorder.printAll());
	}

}
