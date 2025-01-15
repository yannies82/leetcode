package leetcode.bitmanipulation;

public class MinimizeXor {

	public static void main(String[] args) {
		check(3, 5, 3);
		check(1, 12, 3);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/minimize-xor. This solution
	 * finds the count of set bits in num2 and sets to the result as many higher
	 * order bits of num1 as possible (so that they are eliminated with XOR). If any
	 * set bits from num2 are left they are set to the lower order bits of result.
	 * Time complexity is O(1).
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static int minimizeXor(int num1, int num2) {
		// find the count of set bits in nums 2 (minus 1)
		int num2SetBits = -1;
		for (int i = 0; i < 31; i++) {
			num2SetBits += (num2 >>> i) & 1;
		}
		int result = 0;
		for (int i = 30; i >= 0 && num2SetBits >= 0; i--) {
			if (i == num2SetBits || ((num1 >>> i) & 1) == 1) {
				// set 1 to the result if the respective bit of num1 is set
				// or if the remaining number of bits in num1 is the same as
				// the remaining set bits in num2
				result += 1 << i;
				num2SetBits--;
			}
		}
		return result;
	}

	private static void check(int num1, int num2, int expected) {
		System.out.println("num1 is: " + num1);
		System.out.println("num2 is: " + num2);
		System.out.println("expected is: " + expected);
		int minimizeXor = minimizeXor(num1, num2); // Calls your implementation
		System.out.println("minimizeXor is: " + minimizeXor);
	}

}
