package leetcode.graphgeneral;

import java.util.*;

public class FindAllPossibleRecipesFromGivenSupplies {

    public static void main(String[] args) {
        check(new String[]{"bread"}, List.of(List.of("yeast", "flour")), new String[]{"yeast", "flour", "corn"},
                List.of("bread"));
        check(new String[]{"bread", "sandwich"}, List.of(List.of("yeast", "flour"), List.of("bread", "meat")),
                new String[]{"yeast", "flour", "meat"}, List.of("bread", "sandwich"));
        check(new String[]{"bread", "sandwich", "burger"}, List.of(List.of("yeast", "flour"), List.of("bread", "meat"),
                        List.of("sandwich", "meat", "bread")), new String[]{"yeast", "flour", "meat"},
                List.of("bread", "sandwich", "burger"));
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies.
     * This solution uses DFS to traverse the ingredients and determine the recipes which can be created.
     * Time complexity is O(m+n+o) where m is the length of the recipes array, n is the number of distinct ingredients
     * and o is the length of the supplies array.
     *
     * @param recipes
     * @param ingredients
     * @param supplies
     * @return
     */
    public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Set<String>> prerequisitesMap = new HashMap<>();
        Map<String, Integer> recipeIndexes = new HashMap<>();
        int[] ingredientCounts = new int[recipes.length];
        for (int i = 0; i < recipes.length; i++) {
            for (String prerequisite : ingredients.get(i)) {
                prerequisitesMap.computeIfAbsent(prerequisite, key -> new HashSet<>()).add(recipes[i]);
            }
            recipeIndexes.put(recipes[i], i);
            ingredientCounts[i] = ingredients.get(i).size();
        }
        Set<String> suppliesSet = new HashSet<>();
        for (int i = 0; i < supplies.length; i++) {
            suppliesSet.add(supplies[i]);
        }
        Set<String> visitedSet = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < supplies.length; i++) {
            traverse(prerequisitesMap, recipeIndexes, ingredientCounts, suppliesSet, visitedSet, result, supplies[i]);
        }
        return result;
    }

    private static void traverse(Map<String, Set<String>> prerequisitesMap, Map<String, Integer> recipeIndexes,
                                 int[] ingredientsCount, Set<String> suppliesSet, Set<String> visitedSet,
                                 List<String> result, String ingredient) {
        if (!suppliesSet.contains(ingredient) || !visitedSet.add(ingredient)) {
            return;
        }
        Set<String> recipes = prerequisitesMap.get(ingredient);
        if (recipes != null) {
            for (String recipe : recipes) {
                Integer recipeIndex = recipeIndexes.get(recipe);
                if (recipeIndex != null && --ingredientsCount[recipeIndex] == 0) {
                    result.add(recipe);
                    suppliesSet.add(recipe);
                }
                traverse(prerequisitesMap, recipeIndexes, ingredientsCount, suppliesSet, visitedSet, result, recipe);
            }
        }
    }

    private static void check(String[] recipes, List<List<String>> ingredients, String[] supplies, List<String> expected) {
        System.out.println("recipes is: " + Arrays.toString(recipes));
        System.out.println("ingredients is: " + ingredients);
        System.out.println("supplies is: " + Arrays.toString(supplies));
        System.out.println("expected is: " + expected);
        List<String> findAllRecipes = findAllRecipes(recipes, ingredients, supplies); // Calls your implementation
        System.out.println("findAllRecipes is: " + findAllRecipes);
    }
}
