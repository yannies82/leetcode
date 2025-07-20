package leetcode.trie;

import java.util.*;

public class DeleteDuplicateFoldersInSystem {

    public static void main(String[] args) {
        check(List.of(List.of("a"), List.of("c"), List.of("d"), List.of("a", "b"), List.of("c", "b"), List.of("d", "a")),
                List.of(List.of("d"), List.of("d", "a")));
        check(List.of(List.of("a"), List.of("c"), List.of("a", "b"), List.of("c", "b"), List.of("a", "b", "x"), List.of("a", "b", "x", "y")),
                List.of(List.of("w"), List.of("w", "y")));
        check(List.of(List.of("a", "b"), List.of("c", "d"), List.of("c"), List.of("a")),
                List.of(List.of("a", "b"), List.of("c", "d"), List.of("c"), List.of("a")));
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/delete-duplicate-folders-in-system.
     * This solution constructs a trie in order to calculate a signature for each node.
     * Time complexity is O(m+n) where m is the paths length and n is the total number of nodes in the trie.
     *
     * @param paths
     * @return
     */
    public static List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Node root = new Node("");
        for (List<String> path : paths) {
            Node current = root;
            for (String folder : path) {
                Node currentChild = current.children.get(folder);
                if (currentChild == null) {
                    currentChild = new Node(folder);
                    current.children.put(folder, currentChild);
                }
                current = currentChild;
            }
        }

        Map<String, Integer> countMap = new HashMap<>();
        dfsCalculateSignatures(root, countMap);

        List<List<String>> result = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        for (Node child : root.children.values()) {
            dfsRemoveDuplicates(child, currentPath, result, countMap);
        }
        return result;
    }

    private static void dfsCalculateSignatures(Node node, Map<String, Integer> countMap) {
        if (node.children.isEmpty()) {
            node.signature = "";
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Node child : node.children.values()) {
            dfsCalculateSignatures(child, countMap);
            sb.append(child.name).append('(').append(child.signature).append(')');
        }
        node.signature = sb.toString();
        countMap.put(node.signature, countMap.getOrDefault(node.signature, 0) + 1);
    }

    private static void dfsRemoveDuplicates(Node node, List<String> currentPath, List<List<String>> result, Map<String, Integer> countMap) {
        if (!node.children.isEmpty() && countMap.getOrDefault(node.signature, 0) >= 2) {
            return;
        }
        currentPath.add(node.name);
        result.add(new ArrayList<>(currentPath));
        for (Node child : node.children.values()) {
            dfsRemoveDuplicates(child, currentPath, result, countMap);
        }
        currentPath.remove(currentPath.size() - 1);
    }

    private static class Node {
        String name;
        TreeMap<String, Node> children;
        String signature;

        public Node(String name) {
            this.name = name;
            this.children = new TreeMap<>();
        }
    }

    private static void check(List<List<String>> paths, List<List<String>> expected) {
        System.out.println("customers is: " + paths);
        System.out.println("expected is: " + expected);
        List<List<String>> deleteDuplicateFolder = deleteDuplicateFolder(paths); // Calls your implementation
        System.out.println("deleteDuplicateFolder is: " + deleteDuplicateFolder);
    }
}
