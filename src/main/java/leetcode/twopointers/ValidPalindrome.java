package leetcode.twopointers;

public class ValidPalindrome {

	public static void main(String[] args) {
		check("A man, a plan, a canal: Panama", true);
		check("race a car", false);
	}

	public static boolean isPalindrome(String s) {
		int length = s.length();
		int i = -1;
		int j = length;
		char ch1, ch2;
		while (++i < --j) {
			while (((ch1 = s.charAt(i)) - '0' < 0 || ch1 - '0' > 9) 
					&& (ch1 - 'a' < 0 || ch1 - 'a' > 25)
					&& (ch1 - 'A' < 0 || ch1 - 'A' > 25) && i < j) {
				i++;
			}
			while (((ch2 = s.charAt(j)) - '0' < 0 || ch2 - '0' > 9) 
					&& (ch2 - 'a' < 0 || ch2 - 'a' > 25)
					&& (ch2 - 'A' < 0 || ch2 - 'A' > 25) && i < j) {
				j--;
			}
			if (i == j) {
				return true;
			}
			if (ch1 - 'A' <= 25) {
				ch1 = (char) (ch1 + 'a' - 'A');
			}
			if (ch2 - 'A' <= 25) {
				ch2 = (char) (ch2 + 'a' - 'A');
			}
			if (ch1 != ch2) {
				return false;
			}
		}
		return true;
	}

	private static void check(String s, boolean expectedIsPalindrome) {
		System.out.println("s is: " + s);
		System.out.println("expectedIsPalindrome is: " + expectedIsPalindrome);
		boolean isPalindrome = isPalindrome(s); // Calls your implementation
		System.out.println("isPalindrome is: " + isPalindrome);
	}
}
