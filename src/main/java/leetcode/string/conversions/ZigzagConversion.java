package leetcode.string.conversions;

public class ZigzagConversion {

	public static void main(String[] args) {
		check("AB", 1, "AB");
		check("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR");
		check("PAYPALISHIRING", 4, "PINALSIGYAHRPI");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/zigzag-conversion. This
	 * solution calculates the offset between columns in the same row and traverses
	 * the string column to column. For intermediate lines where zigzag characters
	 * exist, their index is calculated and they are aded to the result. Time
	 * complexity is O(n) where n is the length of the string s.
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public static String convert(String s, int numRows) {
		int length = s.length();
		int offset = numRows == 1 ? 1 : 2 * numRows - 2;
		int currentRow = 0;
		int currentIndex = 0;
		StringBuilder result = new StringBuilder();
		// traverse all characters until the length of the result is the same as the
		// length of string s
		while (result.length() < length) {
			result.append(s.charAt(currentIndex));
			if (currentRow > 0 && currentRow < numRows - 1) {
				// append intermediate zigzag characters to the result
				int zigIndex = currentIndex + offset - 2 * currentRow;
				if (zigIndex < length) {
					result.append(s.charAt(zigIndex));
				}
			}
			// increase current index, go to next column
			currentIndex += offset;
			if (currentIndex >= length) {
				// index has exceeded length, increase current row and reset index
				currentRow++;
				currentIndex = currentRow;
			}
		}
		return result.toString();
	}

	private static void check(String s, int numRows, String expectedZigZag) {
		System.out.println("s is: " + s);
		System.out.println("expectedZigZag is: " + expectedZigZag);
		String zigZag = convert(s, numRows); // Calls your implementation
		System.out.println("zigZag is: " + zigZag);
	}
}
