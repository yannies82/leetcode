package leetcode.string;

public class PushDominoes {

    public static void main(String[] args) {
        check("RR.L", "RR.L");
        check(".L.R...LR..L..", "LL.RR.LLRRLL..");
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/push-dominoes.
     * Time complexity is O(n) where n is the length of string dominoes.
     *
     * @param dominoes
     * @return
     */
    public static String pushDominoes(String dominoes) {
        char[] dominoChars = dominoes.toCharArray();
        int lastLeftIndex = -1;
        int lastRightIndex = -1;
        int lastIndex = -1;
        for (int i = 0; i < dominoChars.length; i++) {
            char current = dominoChars[i];
            if (current == 'L') {
                int leftStart;
                if (lastIndex == lastRightIndex && lastIndex >= 0) {
                    // current element is L and last element was R
                    // replace half of the elements in between with R
                    int start = lastRightIndex + 1;
                    int offset = start + i;
                    int mid = offset >>> 1;
                    for (int j = start; j < mid; j++) {
                        dominoChars[j] = 'R';
                    }
                    // replace the other half of the elements in between with L by shifting leftStart
                    leftStart = (offset + 1) >>> 1;
                } else {
                    leftStart = lastLeftIndex + 1;
                }
                // replace previous elements with L
                for (int j = leftStart; j < i; j++) {
                    dominoChars[j] = 'L';
                }
                lastIndex = lastLeftIndex = i;
            } else if (current == 'R') {
                if (lastIndex == lastRightIndex && lastRightIndex >= 0) {
                    for (int j = lastRightIndex + 1; j < i; j++) {
                        dominoChars[j] = 'R';
                    }
                }
                lastIndex = lastRightIndex = i;
            }
        }
        if (lastIndex == lastRightIndex && lastRightIndex >= 0) {
            for (int i = lastRightIndex + 1; i < dominoChars.length; i++) {
                dominoChars[i] = 'R';
            }
        }
        return new String(dominoChars);
    }

    private static void check(String dominoes, String expected) {
        System.out.println("dominoes is: " + dominoes);
        System.out.println("expected is: " + expected);
        String pushDominoes = pushDominoes(dominoes); // Calls your implementation
        System.out.println("pushDominoes is: " + pushDominoes);
    }
}
