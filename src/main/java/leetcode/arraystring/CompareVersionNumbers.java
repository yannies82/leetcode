package leetcode.arraystring;

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
		int revision1 = 0;
		int revision2 = 0;
		int index1 = -1;
		int index2 = -1;
		// skip leading zeroes for both version strings
		while (++index1 < version1.length() && version1.charAt(index1) == '0');
		while (++index2 < version2.length() && version2.charAt(index2) == '0');
		while (index1 < version1.length() || index2 < version2.length()) {
			if (index1 < version1.length()) {
				// append to first revision number
				char v1Char = version1.charAt(index1);
				if (v1Char != '.') {
					revision1 *= 10;
					revision1 += v1Char - '0';
					index1++;
				}
			}
			if (index2 < version2.length()) {
				// append to second revision number
				char v2Char = version2.charAt(index2);
				if (v2Char != '.') {
					revision2 *= 10;
					revision2 += v2Char - '0';
					index2++;
				}
			}
			if ((index1 >= version1.length() || version1.charAt(index1) == '.')
					&& (index2 >= version2.length() || version2.charAt(index2) == '.')) {
				// when both version strings have reached a dot or are over then compare the revisions
				if (revision1 < revision2) {
					return -1;
				} else if (revision1 > revision2) {
					return 1;
				}
				// the revisions are equal, reset them in order to calculate the next ones
				revision1 = 0;
				revision2 = 0;
				// skip leading zeroes of next revision
				while (++index1 < version1.length() && version1.charAt(index1) == '0');
				while (++index2 < version2.length() && version2.charAt(index2) == '0');
			}
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
		Integer zero = 0;
		while (index < tokens1.length || index < tokens2.length) {
			Integer num1 = zero;
			// parse value of first token
			if (index < tokens1.length) {
				num1 = Integer.valueOf(tokens1[index]);
			}
			// parse value of second token
			Integer num2 = zero;
			if (index < tokens2.length) {
				num2 = Integer.valueOf(tokens2[index]);
			}
			// compare tokens, return if non zero
			int result = num1.compareTo(num2);
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
