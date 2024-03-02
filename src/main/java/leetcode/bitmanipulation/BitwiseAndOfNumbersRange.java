package leetcode.bitmanipulation;

public class BitwiseAndOfNumbersRange {

	public static void main(String[] args) {
		check(2, 6, 0);
		check(5, 7, 4);
		check(2147483646, 2147483647, 2147483646);
		check(1073741824, 2147483647, 1073741824);
	}

	/**
	 * Performs the calculation by finding the most significant bit which is
	 * different between left and right.
	 * 
	 * @param n
	 * @return
	 */
	public static int rangeBitwiseAnd(int left, int right) {
		// early exit in case that left is zero
		if (left == 0) {
			return 0;
		}
		// early exit in case left == right
		if (left == right) {
			return left;
		}
		// find the different bits between left and right
		int differentPart = left ^ right;
		int index;
		// find the index of the most significant different bit
		for (index = 1; index < 32; index++) {
			if ((differentPart << index >>> 31) == 1) {
				break;
			}
		}
		// the bits which are more significant than the index are the common ones
		// between left and right, all others will be 0
		int commonPart = left >> 32 - index << 32 - index;
		return commonPart;
	}

	private static void check(int left, int right, int expected) {
		System.out.println("left is: " + left);
		System.out.println("right is: " + right);
		System.out.println("expected is: " + expected);
		int rangeBitwiseAnd = rangeBitwiseAnd(left, right); // Calls your implementation
		System.out.println("rangeBitwiseAnd is: " + rangeBitwiseAnd);
	}

}
