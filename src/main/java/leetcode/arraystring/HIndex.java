package leetcode.arraystring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HIndex {

	public static void main(String[] args) {
		check(new int[] { 3, 0, 6, 1, 5 }, 3);
		check(new int[] { 1, 3, 1 }, 1);
	}
	
	public static int hIndex(int[] citations) {
        int length = citations.length;
		short[] occurences = new short[1001];
        short maxCitations = 0;
        short currentCitations;
		for (short i = 0; i < length; i++) {
            currentCitations = (short)citations[i];
            if (currentCitations > maxCitations) {
                maxCitations = currentCitations;
            }
			occurences[currentCitations]++;
		}
        short totalOccurences = 0;
		for (short i = maxCitations; i >= 0; i--) {
            totalOccurences += occurences[i];
			if (i <= totalOccurences) {
				return i;
			}
		}
		return 0;
	}

	public static int hIndex2(int[] citations) {
		int length = citations.length;
		short[] occurences = new short[1001];
		for (short i = 0; i < length; i++) {
			occurences[citations[i]]++;
		}
		short totalOccurences = 0;
		for (short i = 1000; i >= 0; i--) {
			totalOccurences += occurences[i];
			if (i <= totalOccurences) {
				return i;
			}
		}
		return 0;
	}
	
	public static int hIndex3(int[] citations) {
        int length = citations.length;
        int count;
        for (int i = 1000; i >= 0; i--) {
        	count = 0;
        	for (int j = 0; j < length && count < i; j++) {
        		if (citations[j] >= i) {
        			count++;
        		}
    		}
        	if (count >= i) {
        		return i;
        	}
        }
		return 0;
	}
	
	public static int hIndex4(int[] citations) {
		Map<Integer, Integer> countMap = new HashMap<>();
        int length = citations.length;
        int maxCitations = 0;
        int currentCitations;
		for (int i = 0; i < length; i++) {
            currentCitations = citations[i];
            if (currentCitations > maxCitations) {
                maxCitations = currentCitations;
            }
            int value = countMap.computeIfAbsent(currentCitations, k -> 0).intValue();
            countMap.put(currentCitations, ++value);
		}
        int totalOccurences = 0;
		for (int i = maxCitations; i >= 0; i--) {
            totalOccurences += countMap.getOrDefault(i, 0).intValue();
			if (i <= totalOccurences) {
				return i;
			}
		}
		return 0;
	}

	private static void check(int[] citations, int expectedHIndex) {
		System.out.println("citations is: " + Arrays.toString(citations));
		System.out.println("expectedHIndex is: " + expectedHIndex);
		int hIndex = hIndex(citations); // Calls your implementation
		System.out.println("hIndex is: " + hIndex);
		hIndex = hIndex2(citations); // Calls your implementation
		System.out.println("hIndex2 is: " + hIndex);
		hIndex = hIndex3(citations); // Calls your implementation
		System.out.println("hIndex3 is: " + hIndex);
		hIndex = hIndex4(citations); // Calls your implementation
		System.out.println("hIndex4 is: " + hIndex);
	}
}
