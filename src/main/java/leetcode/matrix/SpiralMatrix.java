package leetcode.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralMatrix {

	public static void main(String[] args) {
		check(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }, List.of(1, 2, 3, 6, 9, 8, 7, 4, 5));
		check(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } },
				List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7));
	}

	public static List<Integer> spiralOrder(int[][] matrix) {
		int hLength = matrix.length;
		int vLength = matrix[0].length;
		int hStart = 0;
		int hEnd = hLength - 1;
		int vStart = 0;
		int vEnd = vLength - 1;
		List<Integer> result = new ArrayList<>();
		while (hStart <= hEnd && vStart <= vEnd) {
			for (int i = vStart; i <= vEnd; i++) {
				result.add(matrix[hStart][i]);
			}
			for (int i = hStart + 1; i <= hEnd; i++) {
				result.add(matrix[i][vEnd]);
			}
			if (hStart < hEnd) {
				for (int i = vEnd - 1; i >= vStart; i--) {
					result.add(matrix[hEnd][i]);
				}
			}
			if (vStart < vEnd) {
				for (int i = hEnd - 1; i > hStart; i--) {
					result.add(matrix[i][vStart]);
				}
			}
			hStart++;
			hEnd--;
			vStart++;
			vEnd--;
		}
		return result;
	}

	private static void check(int[][] matrix, List<Integer> expectedSpiralOrder) {
		System.out.println("matrix is: ");
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		System.out.println("expectedSpiralOrder is: " + expectedSpiralOrder);
		List<Integer> spiralOrder = spiralOrder(matrix); // Calls your implementation
		System.out.println("spiralOrder is: " + spiralOrder);
	}
}
