package leetcode.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	public static void main(String[] args) {
		check(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" },
				List.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea")));
		check(new String[] { "" }, List.of(List.of("")));
		check(new String[] { "a" }, List.of(List.of("a")));
	}

	public static List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		Map<String, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {
			String word = strs[i];
			char[] chars = word.toCharArray();
			Arrays.sort(chars);
			String sortedWord = new String(chars);
			if (indexMap.containsKey(sortedWord)) {
				result.get(indexMap.get(sortedWord)).add(word);
			} else {
				List<String> newList = new ArrayList<>();
				newList.add(word);
				indexMap.put(sortedWord, result.size());
				result.add(newList);
			}
		}
		return result;
	}

	public static List<List<String>> groupAnagrams2(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		Map<String, Integer> indexMap = new HashMap<>();
		for (String word : strs) {
			int[] charsFrequency = new int[26];
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < word.length(); i++) {
				charsFrequency[word.charAt(i) - 'a']++;
			}
			for (int i = 0; i < charsFrequency.length; i++) {
				if (charsFrequency[i] > 0) {
					builder.append('a' + i);
					builder.append(charsFrequency[i]);
				}
			}
			String hash = builder.toString();
			if (indexMap.containsKey(hash)) {
				result.get(indexMap.get(hash)).add(word);
			} else {
				List<String> newList = new ArrayList<>();
				newList.add(word);
				indexMap.put(hash, result.size());
				result.add(newList);
			}
		}
		return result;
	}

	public static List<List<String>> groupAnagrams3(String[] strs) {
		Map<String, List<String>> tempMap = new HashMap<>();
		for (String word : strs) {
			int[] charsFrequency = new int[26];
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < word.length(); i++) {
				charsFrequency[word.charAt(i) - 'a']++;
			}
			for (int i = 0; i < charsFrequency.length; i++) {
				if (charsFrequency[i] > 0) {
					builder.append('a' + i);
					builder.append(charsFrequency[i]);
				}
			}
			String hash = builder.toString();
			if (tempMap.containsKey(hash)) {
				tempMap.get(hash).add(word);
			} else {
				tempMap.put(hash, new ArrayList<>());
				tempMap.get(hash).add(word);
			}
		}

		List<List<String>> result = new ArrayList<>();
		for (String tempHash : tempMap.keySet()) {
			result.add(tempMap.get(tempHash));
		}
		return result;
	}

	public static List<List<String>> groupAnagrams4(String[] strs) {
		Map<Map<Character, Integer>, List<String>> map = new HashMap<>();
		String str;
		for (int i = 0; i < strs.length; i++) {
			str = strs[i];
			Map<Character, Integer> tempMap = new HashMap<Character, Integer>();
			for (int j = 0; j < str.length(); j++) {
				if (tempMap.containsKey(str.charAt(j))) {
					tempMap.put(str.charAt(j), tempMap.get(str.charAt(j)) + 1);
				} else {
					tempMap.put(str.charAt(j), 1);
				}
			}

			if (map.containsKey(tempMap))
				map.get(tempMap).add(str);
			else {
				List<String> tempList = new ArrayList<String>();
				tempList.add(str);
				map.put(tempMap, tempList);
			}
		}

		List<List<String>> result = new ArrayList<>();
		for (Map<Character, Integer> tempMap : map.keySet()) {
			result.add(map.get(tempMap));
		}
		return result;
	}

	public static List<List<String>> groupAnagrams5(String[] strs) {
		int wordsCount = strs.length;
		int[] charMap = new int[26];
		List<List<String>> result = new ArrayList<>();
		String word;
		String groupedWord;
		boolean isAnagram;
		outer: for (int j = 0; j < wordsCount; j++) {
			word = strs[j];
			for (int k = 0; k < result.size(); k++) {
				groupedWord = result.get(k).get(0);
				if (word.length() != groupedWord.length()) {
					continue;
				}
				charMap = new int[26];
				for (int i = 0; i < word.length(); i++) {
					charMap[word.charAt(i) - 'a']++;
					charMap[groupedWord.charAt(i) - 'a']--;
				}
				isAnagram = true;
				for (int i = 0; i < 26; i++) {
					if (charMap[i] != 0) {
						isAnagram = false;
						break;
					}
				}
				if (isAnagram) {
					result.get(k).add(word);
					continue outer;
				}
			}
			List<String> newList = new ArrayList<>();
			newList.add(word);
			result.add(newList);
		}
		return result;
	}

	private static void check(String[] strs, List<List<String>> expectedGroupAnagrams) {
		System.out.println("strs is: " + Arrays.toString(strs));
		System.out.println("expectedGroupAnagrams is: " + expectedGroupAnagrams);
		List<List<String>> groupAnagrams = groupAnagrams(strs); // Calls your implementation
		System.out.println("groupAnagrams is: " + groupAnagrams);
	}
}
