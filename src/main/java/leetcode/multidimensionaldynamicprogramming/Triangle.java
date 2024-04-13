package leetcode.multidimensionaldynamicprogramming;

import java.util.List;

public class Triangle {

	public static void main(String[] args) {
		check(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3)), 11);
		check(List.of(List.of(-10)), -10);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/triangle. This solution uses
	 * bottom up dynamic programming to calculate the values for all subproblems,
	 * then picks the minimum from the last row solutions. Time complexity is O(n^2)
	 * where n is the triangle length;
	 * 
	 * @param triangle
	 * @return
	 */
	public static int minimumTotal(List<List<Integer>> triangle) {
		int size = triangle.size();
		if (size == 1) {
			return triangle.get(0).get(0);
		}
		// this array stores the subproblem solutions
		// it should be 2D but we can make it 1D and optimize
		// the algorithm space wise if we overwrite the previous row solutions
		int[] dpArray = new int[size];
		// solution for the first row which contains a single element
		dpArray[0] = triangle.get(0).get(0);
		// iterate all other rows except for the last one and solve subproblems
		for (int i = 1; i < size - 1; i++) {
			List<Integer> list = triangle.get(i);
			// the subproblem for the last row element can only use
			// the solution for the previous row's last element
			dpArray[i] = dpArray[i - 1] + list.get(i);
			for (int j = i - 1; j > 0; j--) {
				// each element can use the solution of elements j or j-1 from the
				// previous row and the solution for element j overwrites the one
				// of the previous row
				dpArray[j] = Math.min(dpArray[j - 1], dpArray[j]) + list.get(j);
			}
			// the subproblem for the first row element can only use
			// the solution for the previous row's first element
			dpArray[0] = dpArray[0] + list.get(0);
		}
		// do not store solutions for the last row in dpArray, since we
		// only need the minimum one calculate it and return it
		List<Integer> list = triangle.get(size - 1);
		int min = dpArray[size - 2] + list.get(size - 1);
		for (int j = size - 2; j > 0; j--) {
			int nextMin = Math.min(dpArray[j - 1], dpArray[j]) + list.get(j);
			if (nextMin < min) {
				min = nextMin;
			}
		}
		return Math.min(min, dpArray[0] + list.get(0));
	}

	private static void check(List<List<Integer>> triangle, int expected) {
		System.out.println("triangle is: " + triangle);
		System.out.println("expected is: " + expected);
		int minimumTotal = minimumTotal(triangle); // Calls your implementation
		System.out.println("minimumTotal is: " + minimumTotal);
	}

}
