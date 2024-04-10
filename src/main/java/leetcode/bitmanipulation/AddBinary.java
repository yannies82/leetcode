package leetcode.bitmanipulation;

public class AddBinary {

	public static void main(String[] args) {
		check("1010", "1011", "10101");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/add-binary. Time complexity
	 * is O(max(A,B)) where A is the length of the a array and B is the length of
	 * the B array.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String addBinary(String a, String b) {
		int aLength = a.length();
		int bLength = b.length();
		int carry = 0;
		int indexA = aLength - 1;
		int indexB = bLength - 1;
		StringBuilder builder = new StringBuilder();
		while (indexA >= 0 || indexB >= 0) {
			int sum = carry;
			if (indexA >= 0) {
				sum += a.charAt(indexA--) - '0';
			}
			if (indexB >= 0) {
				sum += b.charAt(indexB--) - '0';
			}
			builder.insert(0, (char) (sum % 2 + '0'));
			carry = sum / 2;
		}
		if (carry > 0) {
			builder.insert(0, carry);
		}
		return builder.toString();
	}

	private static void check(String a, String b, String expected) {
		System.out.println("a is: " + a);
		System.out.println("b is: " + b);
		System.out.println("expected is: " + expected);
		String addBinary = addBinary(a, b); // Calls your implementation
		System.out.println("addBinary is: " + addBinary);
	}
}
