package leetcode.graphgeneral;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {

	public static void main(String[] args) {
		var equations1 = List.of(List.of("a", "b"), List.of("b", "c"));
		double[] values1 = { 2.0d, 3.0d };
		var queries1 = List.of(List.of("a", "c"), List.of("b", "a"), List.of("a", "e"), List.of("a", "a"),
				List.of("x", "x"));
		double[] expected1 = { 6.0d, 0.5d, -1.0d, 1.0d, -1.0d };
		check(equations1, values1, queries1, expected1);
		var equations2 = List.of(List.of("x1", "x2"), List.of("x2", "x3"), List.of("x3", "x4"), List.of("x4", "x5"));
		double[] values2 = { 3.0d, 4.0d, 5.0d, 6.0d };
		var queries2 = List.of(List.of("x1", "x5"), List.of("x5", "x2"), List.of("x2", "x4"), List.of("x2", "x2"),
				List.of("x2", "x9"), List.of("x9", "x9"));
		double[] expected2 = { 360.0d, 0.008333333333333333d, 20.0d, 1.0d, -1.0d, -1.0d };
		check(equations2, values2, queries2, expected2);
	}

	/**
	 * This solution uses a map of maps to store the given equation results. For
	 * each given query we recursively calculate the divisions until the equations
	 * map contains an entry for both the dividee and the divisor.
	 * 
	 * @param equations
	 * @param values
	 * @param queries
	 * @return
	 */
	public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		// populate the equationsMap with all available equations
		Map<String, Map<String, Double>> equationsMap = new HashMap<>();
		for (int i = 0; i < equations.size(); i++) {
			// store the result of equation[0] / equation[1]
			equationsMap.computeIfAbsent(equations.get(i).get(0), k -> new HashMap<>()).put(equations.get(i).get(1),
					values[i]);
			// also store the result of equation[1] / equation[0]
			equationsMap.computeIfAbsent(equations.get(i).get(1), k -> new HashMap<>()).put(equations.get(i).get(0),
					1 / values[i]);
		}
		// initialize the results array
		double[] results = new double[queries.size()];
		for (int i = 0; i < queries.size(); i++) {
			// keep the visited variables in this set in order to avoid infinite loops
			Set<String> visited = new HashSet<>();
			// calculate each query and set to the results array
			results[i] = calculate(queries.get(i), equationsMap, visited);
			// we could consider adding the result of the query to the equation map here
			// as an optimization which would trade more space for less execution time
			// for next queries
		}
		return results;
	}

	private static double calculate(List<String> query, Map<String, Map<String, Double>> equationsMap,
			Set<String> visited) {
		// add dividee to the set of visited variables
		visited.add(query.get(0));
		Map<String, Double> equation = equationsMap.get(query.get(0));
		// if both operands are the same return either 1 (if they are known)
		// or -1 (if they are unknown)
		if (query.get(0).equals(query.get(1))) {
			if (equation == null) {
				return -1;
			} else {
				return 1;
			}
		}
		if (equation == null || !equationsMap.containsKey(query.get(1))) {
			// if either one of the operands is unknown return -1
			return -1;
		} else if (equation.containsKey(query.get(1))) {
			// the equationsMap contains a result, return it
			return equation.get(query.get(1));
		}
		double res;
		for (String key : equation.keySet()) {
			// at this point the equations map does not contain a result for this query
			// for each non visited divisor of the equation map, perform the calculation
			// recursively
			// using it as the dividee and if it returns a result > 0 (valid) then multiply
			// with
			// the value that corresponds to the key and return
			if (!visited.contains(key) && (res = calculate(List.of(key, query.get(1)), equationsMap, visited)) >= 0) {
				return res * equation.get(key);
			}
		}
		return -1;
	}

	private static void check(List<List<String>> equations, double[] values, List<List<String>> queries,
			double[] expected) {
		System.out.println("equations is: " + equations);
		System.out.println("values is: " + Arrays.toString(values));
		System.out.println("queries is: " + queries);
		System.out.println("expected is: " + Arrays.toString(expected));
		double[] calcEquation = calcEquation(equations, values, queries); // Calls your implementation
		System.out.println("calcEquation is: " + Arrays.toString(calcEquation));
	}
}
