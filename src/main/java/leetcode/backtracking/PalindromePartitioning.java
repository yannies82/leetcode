package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

	public static void main(String[] args) {
		check("aab", List.of(List.of("a", "a", "b"), List.of("aa", "b")));
		check("a", List.of(List.of("a")));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/palindrome-partitioning. This
	 * solution uses recursion and backtracking to try all partitioning combinations
	 * for the string and keep the ones which contain only palindrome strings. Worst
	 * case time complexity is O(2^n) where n is the length of the string s.
	 * 
	 * @param s
	 * @return
	 */
	public static List<List<String>> partition(String s) {
		char[] chars = s.toCharArray();
		List<List<String>> result = new ArrayList<>();
		partitionRecursive(chars, result, new ArrayList<>(), 0, 1);
		return result;
	}

	private static void partitionRecursive(char[] chars, List<List<String>> result, List<String> current, int start,
			int end) {
		if (end > chars.length) {
			// we have reached the end of the string, add current partitioning to the
			// results
			result.add(new ArrayList<>(current));
			return;
		}

		// try partitioning the substring starting at start and ending at end
		if (isPalindrome(chars, start, end)) {
			// only add the string to the current partitions if it is palindrome
			// otherwise skip this path since the final list will contain at least 1 non
			// palindrome string
			current.add(new String(chars, start, end - start));
			// since we added the substring to the current partitions, increase the start
			// and end indexes
			partitionRecursive(chars, result, current, end, end + 1);
			// backtrack by removing the string from the current partitions
			current.remove(current.size() - 1);
		}

		// if this is not the end of the string try to skip adding the substring
		// starting at start and ending at end
		// increase the end index instead
		if (end < chars.length) {
			partitionRecursive(chars, result, current, start, end + 1);
		}
	}

	private static boolean isPalindrome(char[] chars, int start, int end) {
		int mid = (end + start) >> 1;
		int lastIndexOffset = end + start - 1;
		for (int i = start; i < mid; i++) {
			if (chars[i] != chars[lastIndexOffset - i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Similar solution which does not convert the string to a char array. Worst
	 * case time complexity is O(2^n) where n is the length of the string s.
	 * 
	 * @param s
	 * @return
	 */
	public static List<List<String>> partition2(String s) {
		List<List<String>> result = new ArrayList<>();
		partitionRecursive2(s, result, new ArrayList<>(), 0, 1);
		return result;
	}

	private static void partitionRecursive2(String s, List<List<String>> result, List<String> current, int start,
			int end) {
		int length = s.length();
		if (end > length) {
			// we have reached the end of the string, add current partitioning to the
			// results
			result.add(new ArrayList<>(current));
			return;
		}

		// try partitioning the substring starting at start and ending at end
		String candidate = s.substring(start, end);
		if (isPalindrome2(candidate)) {
			// only add the string to the current partitions if it is palindrome
			// otherwise skip this path since the final list will contain at least 1 non
			// palindrome string
			current.add(candidate);
			// since we added the substring to the current partitions, increase the start
			// and end indexes
			partitionRecursive2(s, result, current, end, end + 1);
			// backtrack by removing the string from the current partitions
			current.remove(current.size() - 1);
		}

		// if this is not the end of the string try to skip adding the substring
		// starting at start and ending at end
		// increase the end index instead
		if (end < length) {
			partitionRecursive2(s, result, current, start, end + 1);
		}
	}

	private static boolean isPalindrome2(String candidate) {
		int mid = candidate.length() >> 1;
		int lastIndex = candidate.length() - 1;
		for (int i = 0; i < mid; i++) {
			if (candidate.charAt(i) != candidate.charAt(lastIndex - i)) {
				return false;
			}
		}
		return true;
	}

	private static void check(String s, List<List<String>> expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		List<List<String>> partition = partition(s); // Calls your implementation
		System.out.println("partition is: " + partition);
	}
}
