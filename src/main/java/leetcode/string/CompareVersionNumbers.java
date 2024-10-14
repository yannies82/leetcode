package leetcode.string;

public class CompareVersionNumbers {

	public static void main(String[] args) {
		check("1.101", "1.100001", -1);
		check("1.1", "1.000000000000000000000010", -1);
		check("1.01", "1.001", 0);
		check("1.0", "1.0.0", 0);
		check("0.1", "1.1", -1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/compare-version-numbers. This
	 * solution traverses the version strings and calculates each token value up to
	 * the dot, then compares them. Time complexity is O(n) where n is the max
	 * length of strings version1 and version2.
	 * 
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static int compareVersion(String version1, String version2) {
		int index1 = 0;
		int index2 = 0;
		while (index1 < version1.length() || index2 < version2.length()) {
			int revision1 = 0;
			int revision2 = 0;
			// skip leading zeroes of next revision
			while (index1 < version1.length() && version1.charAt(index1) == '0') {
				index1++;
			}
			while (index2 < version2.length() && version2.charAt(index2) == '0') {
				index2++;
			}
			char v1Char;
			while (index1 < version1.length() && (v1Char = version1.charAt(index1++)) != '.') {
				// append to first revision number
				revision1 *= 10;
				revision1 += v1Char - '0';
			}
			char v2Char;
			while (index2 < version2.length() && (v2Char = version2.charAt(index2++)) != '.') {
				// append to first revision number
				revision2 *= 10;
				revision2 += v2Char - '0';
			}
			int result = Integer.compare(revision1, revision2);
			if (result != 0) {
				return result;
			}
			// the revisions are equal, continue to the next one
		}
		return 0;
	}

	/**
	 * Simple solution using built in java functions. Time complexity is O(n) where
	 * n is the max length of strings version1 and version2.
	 * 
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static int compareVersion2(String version1, String version2) {
		// split version strings into tokens using dot as delimiter
		String[] tokens1 = version1.split("\\.");
		String[] tokens2 = version2.split("\\.");
		int index = 0;
		while (index < tokens1.length || index < tokens2.length) {
			int num1 = 0;
			// parse value of first token
			if (index < tokens1.length) {
				num1 = Integer.parseInt(tokens1[index]);
			}
			// parse value of second token
			int num2 = 0;
			if (index < tokens2.length) {
				num2 = Integer.parseInt(tokens2[index]);
			}
			// compare tokens, return if non zero
			int result = Integer.compare(num1, num2);
			if (result != 0) {
				return result;
			}
			index++;
		}
		return 0;
	}

	private static void check(String version1, String version2, int expected) {
		System.out.println("version1 is: " + version1);
		System.out.println("version2 is: " + version2);
		System.out.println("expected is: " + expected);
		int compareVersion = compareVersion(version1, version2); // Calls your implementation
		System.out.println("compareVersion is: " + compareVersion);
	}
}
