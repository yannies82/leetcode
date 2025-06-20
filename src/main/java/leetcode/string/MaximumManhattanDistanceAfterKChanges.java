package leetcode.string;

public class MaximumManhattanDistanceAfterKChanges {

    public static void main(String[] args) {
        check("NWSE", 1, 3);
        check("NSWWEW", 3, 6);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes.
     * Time complexity is O(n) where n is the length of string s.
     *
     * @param s
     * @param k
     * @return
     */
    public static int maxDistance(String s, int k) {
        int north = 0;
        int south = 0;
        int east = 0;
        int west = 0;
        int result = 0;
        char[] sChars = s.toCharArray();
        int realK = k << 1;
        for (int i = 0; i < sChars.length; i++) {
            char current = sChars[i];
            if (current == 'N') {
                north++;
            } else if (current == 'S') {
                south++;
            } else if (current == 'E') {
                east++;
            } else {
                west++;
            }
            int currentDistance = Math.abs(east - west) + Math.abs(north - south);
            int currentMaxDistance = currentDistance + Math.min(i + 1 - currentDistance, realK);
            result = Math.max(result, currentMaxDistance);
        }
        return result;
    }

    public static int maxDistance2(String s, int k) {
        int north = 0;
        int south = 0;
        int east = 0;
        int west = 0;
        int result = 0;
        int sLength = s.length();
        int realK = k << 1;
        for (int i = 0; i < sLength; i++) {
            char current = s.charAt(i);
            if (current == 'N') {
                north++;
            } else if (current == 'S') {
                south++;
            } else if (current == 'E') {
                east++;
            } else {
                west++;
            }
            int currentDistance = Math.abs(east - west) + Math.abs(north - south);
            int currentMaxDistance = currentDistance + Math.min(i + 1 - currentDistance, realK);
            result = Math.max(result, currentMaxDistance);
        }
        return result;
    }

    public static int maxDistance3(String s, int k) {
        int[] position = {0, 0};
        int[] maxPosition = {0, 0};
        int result = 0;
        int sLength = s.length();
        int realK = k << 1;
        for (int i = 0; i < sLength; i++) {
            char current = s.charAt(i);
            if (current == 'N') {
                position[1]++;
                maxPosition[1]++;
            } else if (current == 'S') {
                position[1]--;
                maxPosition[1]++;
            } else if (current == 'E') {
                position[0]++;
                maxPosition[0]++;
            } else {
                position[0]--;
                maxPosition[0]++;
            }
            int absX = Math.abs(position[0]);
            int absY = Math.abs(position[1]);
            int currentDistance = absX + absY;
            int currentOffset = Math.abs(maxPosition[0] - absX) + Math.abs(maxPosition[1] - absY);
            int currentMaxDistance = currentDistance + Math.min(currentOffset, realK);
            result = Math.max(result, currentMaxDistance);
        }
        return result;
    }

    private static void check(String s, int k, int expected) {
        System.out.println("s is: " + s);
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int maxDistance = maxDistance(s, k); // Calls your implementation
        System.out.println("maxDistance is: " + maxDistance);
    }
}
