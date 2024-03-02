package leetcode.binarytreegeneral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LowestCommonAncestor {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(3,
				new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
				new TreeNode(1, new TreeNode(0), new TreeNode(8)));
		check(tree1, new TreeNode(6), new TreeNode(4), new TreeNode(5));
		check(tree1, new TreeNode(5), new TreeNode(7), new TreeNode(5));
		check(tree1, new TreeNode(5), new TreeNode(1), new TreeNode(3));
	}

	/**
	 * Recursive implementation with single traversal.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		return findAncestors(root, p, q);
	}

	private static TreeNode findAncestors(TreeNode node, TreeNode p, TreeNode q) {
		// stop recursion at leaf nodes
		if (node == null) {
			return null;
		}
		// return the node if it has the same value as p or q
		if (node.val == p.val || node.val == q.val) {
			return node;
		}

		// search for p and q in the left and right subtrees
		TreeNode leftAncestor = findAncestors(node.left, p, q);
		TreeNode rightAncestor = findAncestors(node.right, p, q);

		// if both the leftAncestor and rightAncestor are present
		// the node is the LCA
		if (leftAncestor != null && rightAncestor != null) {
			return node;
		}

		// p and q were not found in both subtrees, therefore they exist
		// in either the left subtree or the right subtree, whichever
		// is not null
		return leftAncestor != null ? leftAncestor : rightAncestor;
	}

	/**
	 * Recursive implementation, constructs ancestor paths with list. Two separate
	 * traversals.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		List<TreeNode> pAncestors = new ArrayList<>();
		// traverse the tree recursively
		findAncestors2(root, p, pAncestors);
		List<TreeNode> qAncestors = new ArrayList<>();
		// traverse the tree recursively
		findAncestors2(root, q, qAncestors);
		int index = 0;
		// compare ancestor paths, the lowest common ancestor is the
		// one with the highest index
		while (index < pAncestors.size() && index < qAncestors.size()
				&& pAncestors.get(index).val == qAncestors.get(index).val) {
			index++;
		}
		return pAncestors.get(index - 1);
	}

	private static boolean findAncestors2(TreeNode node, TreeNode nodeToFind, List<TreeNode> ancestors) {
		if (node == null) {
			return false;
		}
		ancestors.add(node);
		if (node.val == nodeToFind.val || findAncestors2(node.left, nodeToFind, ancestors)
				|| findAncestors2(node.right, nodeToFind, ancestors)) {
			return true;
		}
		ancestors.remove(ancestors.size() - 1);
		return false;
	}

	/**
	 * Recursive implementation, constructs ancestor paths with map. Two separate
	 * traversals.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
		Map<Integer, TreeNode> pAncestors = new HashMap<>();
		// traverse the tree recursively
		findAncestors3(root, p, 1, pAncestors);
		Map<Integer, TreeNode> qAncestors = new HashMap<>();
		// traverse the tree recursively
		findAncestors3(root, q, 1, qAncestors);
		int index = 1;
		// compare ancestor paths, the lowest common ancestor is the
		// one with the highest index
		while (pAncestors.containsKey(index) && qAncestors.containsKey(index)
				&& pAncestors.get(index).val == qAncestors.get(index).val) {
			index++;
		}
		return pAncestors.get(index - 1);
	}

	private static boolean findAncestors3(TreeNode node, TreeNode nodeToFind, int level,
			Map<Integer, TreeNode> levelsMap) {
		levelsMap.put(level, node);
		if (node.val == nodeToFind.val) {
			return true;
		}
		boolean result = false;
		if (node.left != null) {
			// traverse left node recursively
			result = findAncestors3(node.left, nodeToFind, level + 1, levelsMap);
		}
		if (node.right != null && !result) {
			// traverse right node recursively
			result = findAncestors3(node.right, nodeToFind, level + 1, levelsMap);
		}
		return result;
	}

	private static void check(TreeNode root, TreeNode p, TreeNode q, TreeNode expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("p is: " + p.val);
		System.out.println("q is: " + q.val);
		System.out.println("expected is: " + expected.val);
		TreeNode lowestCommonAncestor = lowestCommonAncestor(root, p, q);
		System.out.println("lowestCommonAncestor is: " + lowestCommonAncestor.val);
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
			if (node.left != null) {
				print(node.left, builder);
			}
			if (node.right != null) {
				print(node.right, builder);
			}
		}

	}

}
