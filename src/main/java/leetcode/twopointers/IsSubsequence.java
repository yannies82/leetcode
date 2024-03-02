package leetcode.twopointers;

public class IsSubsequence {

	public static void main(String[] args) {
		check("abc", "ahbgdc", true);
		check("axc", "ahbgdc", false);
	}

	public static boolean isSubsequence(String s, String t) {
		int sLength = s.length();
		int tLength = t.length();
		if (tLength < sLength) {
			return false;
		}
		int j = 0;
		for (int i = 0; i < sLength; i++) {
			while (j < tLength && t.charAt(j++) != s.charAt(i)) {
				if (sLength - i > tLength - j) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isSubsequence2(String s, String t) {
		int sLength = s.length();
		int tLength = t.length();
		int i = 0, j = 0;
		while (j < tLength && i < sLength && sLength - i <= tLength - j) {
			while (t.charAt(j++) != s.charAt(i)) {
				if (sLength - i > tLength - j) {
					return false;
				}
			}
			;
			i++;
		}
		if (i == sLength) {
			return true;
		}
		return false;
	}

	private static void check(String s, String t, boolean expectedIsSubsequence) {
		System.out.println("s is: " + s);
		System.out.println("t is: " + t);
		System.out.println("expectedIsSubsequence is: " + expectedIsSubsequence);
		boolean isSubsequence = isSubsequence(s, t); // Calls your implementation
		System.out.println("isSubsequence is: " + isSubsequence);
	}
}
