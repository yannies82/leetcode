package leetcode.graphbfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MinimumGenericMutation {

	public static void main(String[] args) {
		String startGene = "AACCGGTT";
		String endGene = "AACCGGTA";
		String[] bank0 = { "AACCGGTA" };
		check(startGene, endGene, bank0, 1);
		startGene = "AACCGGTT";
		endGene = "AAACGGTA";
		String[] bank1 = { "AACCGGTA", "AACCGCTA", "AAACGGTA" };
		check(startGene, endGene, bank1, 2);
		startGene = "AACCGGTT";
		endGene = "AAACGGTA";
		String[] bank2 = { "AACCGATT", "AACCGATA", "AAACGATA", "AAACGGTA" };
		check(startGene, endGene, bank2, 4);
		startGene = "AAAAAAAA";
		endGene = "CCCCCCCC";
		String[] bank3 = { "AAAAAAAA", "AAAAAAAC", "AAAAAACC", "AAAAACCC", "AAAACCCC", "AACACCCC", "ACCACCCC",
				"ACCCCCCC", "CCCCCCCA", "CCCCCCCC" };
		check(startGene, endGene, bank3, 8);
	}

	/**
	 * This solution searches for gene strings in the bank which differ by exactly
	 * one char from the current mutation and puts them in the queue to perform BFS.
	 * Keeps a set to mark visited mutations. Time complexity is O(B * B * C) where
	 * b is the bank size and C is the length of the gene string (8).
	 * 
	 * @param startGene
	 * @param endGene
	 * @param bank
	 * @return
	 */
	public static int minMutation(String startGene, String endGene, String[] bank) {
		if (startGene.equals(endGene)) {
			// early exist if no mutations need to be performed
			return 0;
		} else if (bank.length == 0) {
			// early exit in case of empty bank i.e. if no valid mutations exist
			return -1;
		}
		// keeps visited mutations
		Set<String> visited = new HashSet<>();
		// is used for BFS traversal
		Queue<String> mutations = new ArrayDeque<>();
		mutations.offer(startGene);
		int mutationsCount = 0;
		while (!mutations.isEmpty()) {
			// increase mutations count for the nth possible mutation
			mutationsCount++;
			// remove all nth mutations and put in the queue all possible n + 1 level
			// mutations
			int levelLength = mutations.size();
			for (int i = 0; i < levelLength; i++) {
				// remove possible nth level mutation from the queue
				String currentMutation = mutations.poll();
				// mark mutation as visited
				visited.add(currentMutation);
				for (int j = 0; j < bank.length; j++) {
					if (!visited.contains(bank[j]) && !currentMutation.equals(bank[j])
							&& calculateDiff(currentMutation, bank[j]) == 1) {
						// only put in the queue non visited mutations from the bank which are different
						// from
						// the current mutation by exactly one character
						if (bank[j].equals(endGene)) {
							// after performing mutationsCount number of mutations we have reached the end
							// gene
							return mutationsCount;
						}
						mutations.offer(bank[j]);
					}
				}
			}
		}
		// we are out of mutations, the end gene cannot be achieved
		return -1;
	}

	private static int calculateDiff(String gene1, String gene2) {
		int diff = 0;
		for (int i = 0; i < gene1.length(); i++) {
			if (gene1.charAt(i) != gene2.charAt(i)) {
				diff++;
			}
		}
		return diff;
	}

	/**
	 * This solution keeps the bank genes in a map and uses BFS traversal to check
	 * all possible next mutations from the current gene. Time complexity is O(B * C
	 * * C * D) where B is the bank size, C is the gene string length (8) and D is
	 * the number of different chars in the string (4).
	 * 
	 * @param startGene
	 * @param endGene
	 * @param bank
	 * @return
	 */
	public static int minMutation2(String startGene, String endGene, String[] bank) {
		if (startGene.equals(endGene)) {
			// early exist if no mutations need to be performed
			return 0;
		} else if (bank.length == 0) {
			// early exit in case of empty bank i.e. if no valid mutations exist
			return -1;
		}
		// populate mutationsMap from the bank, use mutation string as key
		// and a boolean as value which will indicate if this mutation has been visited
		Map<String, Boolean> mutationsMap = new HashMap<>();
		for (int i = 0; i < bank.length; i++) {
			mutationsMap.put(bank[i], false);
		}
		// if the endGene is not included in the gene bank, the mutation cannot be
		// completed
		if (!mutationsMap.containsKey(endGene)) {
			return -1;
		}
		// perform BFS and return the result
		return performBFS(startGene, endGene, mutationsMap);
	}

	private static int performBFS(String startGene, String endGene, Map<String, Boolean> mutationsMap) {
		char[] geneChars = { 'A', 'T', 'G', 'C' };
		// initialize mutations queue and add the first mutation
		Queue<String> mutations = new ArrayDeque<>();
		mutations.offer(startGene);
		// initialize mutations count
		int mutationsCount = 0;
		while (!mutations.isEmpty()) {
			// increase mutations count for the nth possible mutation
			mutationsCount++;
			// remove all nth mutations and put in the queue all possible n + 1 level
			// mutations
			int levelLength = mutations.size();
			for (int i = 0; i < levelLength; i++) {
				// remove possible nth level mutation from the queue
				String currentMutation = mutations.poll();
				// update the map value to true, which will indicate that this mutation has been
				// visited
				mutationsMap.put(currentMutation, true);
				// examine the possible next mutations, for every gene string character
				for (int j = 0; j < endGene.length(); j++) {
					// try any of the four genes as a replacement, except for the current one
					for (int k = 0; k < geneChars.length; k++) {
						if (currentMutation.charAt(j) != geneChars[k]) {
							// generate mutation string by replacing the char at index j with the respective
							// replacement gene char
							String nextMutation = currentMutation.substring(0, j) + geneChars[k]
									+ currentMutation.substring(j + 1, endGene.length());
							if (nextMutation.equals(endGene)) {
								// after performing mutationsCount number of mutations we have reached the end
								// gene
								return mutationsCount;
							}
							// add the mutation to the queue if it exists in the map (and therefore in the
							// bank)
							// and has not been visited yet
							Boolean nextMutationIsVisited = mutationsMap.get(nextMutation);
							if (Boolean.FALSE.equals(nextMutationIsVisited)) {
								mutations.offer(nextMutation);
							}
						}
					}
				}
			}
		}
		// we are out of positions, the game cannot be ended
		return -1;
	}

	private static void check(String startGene, String endGene, String[] bank, int expected) {
		System.out.println("startGene is: " + startGene);
		System.out.println("endGene is: " + endGene);
		System.out.println("bank is: " + Arrays.toString(bank));
		System.out.println("expected is: " + expected);
		int minMutation = minMutation(startGene, endGene, bank); // Calls your implementation
		System.out.println("minMutation is: " + minMutation);
	}
}
