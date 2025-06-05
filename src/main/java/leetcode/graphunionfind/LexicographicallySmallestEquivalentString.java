package leetcode.graphunionfind;

import java.util.*;

public class LexicographicallySmallestEquivalentString {

    public static void main(String[] args) {
        check("parker", "morris", "parser", "makkek");
        check("hello", "world", "hold", "hdld");
        check("leetcode", "programs", "sourcecode", "aauaaaaada");
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/lexicographically-smallest-equivalent-string.
     * This solution uses union find to find the parent of each letter and create the lexicographically smallest
     * string. Time complexity is O(n+m) where n is the length os string s1 and m is the length of string baseStr.
     *
     * @param s1
     * @param s2
     * @param baseStr
     * @return
     */
    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        // apply union find to find the parent for each letter
        for (int i = 0; i < s1.length(); i++) {
            union(parent, s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }
        // construct the result string using the parent of each letter
        char[] result = new char[baseStr.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (char) (find(parent, baseStr.charAt(i) - 'a') + 'a');
        }
        return new String(result);
    }

    public static int find(int[] parent, int val) {
        if (parent[val] != val) {
            parent[val] = find(parent, parent[val]);
        }
        return parent[val];
    }

    public static void union(int[] parent, int a, int b) {
        int parentA = find(parent, a);
        int parentB = find(parent, b);
        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }

    /**
     * This solution uses an adjacency list and performs DFS to find all connected sets of characters.
     *
     * @param s1
     * @param s2
     * @param baseStr
     * @return
     */
    public static String smallestEquivalentString2(String s1, String s2, String baseStr) {
        List<Integer>[] adjacencyList = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        int s1Length = s1.length();
        for (int i = 0; i < s1Length; i++) {
            int char1 = s1.charAt(i) - 'a';
            int char2 = s2.charAt(i) - 'a';
            adjacencyList[char1].add(char2);
            adjacencyList[char2].add(char1);
        }
        boolean[] visited = new boolean[26];
        int[] minRep = new int[26];
        for (int i = 0; i < 26; i++) {
            minRep[i] = i; // Initialize each character to point to itself
        }
        for (int i = 0; i < 26; i++) {
            if (!visited[i]) {
                Set<Integer> set = new HashSet<>();
                int min = dfs(adjacencyList, i, set, visited);
                for (int j : set) {
                    minRep[j] = min;
                }
            }
        }
        char[] result = baseStr.toCharArray();
        for (int i = 0; i < result.length; i++) {
            result[i] = (char) ('a' + minRep[result[i] - 'a']); // Replace each character with its representative
        }
        return new String(result);
    }

    private static int dfs(List<Integer>[] adjacencyList, int i, Set<Integer> set, boolean[] visited) {
        set.add(i);
        int min = i;
        for (int neighbor : adjacencyList[i]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                set.add(neighbor);
                min = Math.min(min, dfs(adjacencyList, neighbor, set, visited));
            }
        }
        return min;
    }

    private static void check(String s1, String s2, String baseStr, String expected) {
        System.out.println("s1 is: " + s1);
        System.out.println("s2 is: " + s2);
        System.out.println("baseStr is: " + baseStr);
        System.out.println("expected is: " + expected);
        String smallestEquivalentString = smallestEquivalentString(s1, s2, baseStr); // Calls your implementation
        System.out.println("smallestEquivalentString is: " + smallestEquivalentString);
    }
}
