package leetcode.array;

import java.util.Arrays;

public class MaximumMatchingOfPlayersWithTrainers {

    public static void main(String[] args) {
        check(new int[]{4, 7, 9}, new int[]{8, 2, 5, 8}, 2);
        check(new int[]{1, 1, 1}, new int[]{10}, 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-matching-of-players-with-trainers.
     * Time complexity is O(nlogn + mlogm) where n is the length of the players array and m is the
     * length of the trainers array.
     *
     * @param players
     * @param trainers
     * @return
     */
    public static int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int result = 0;
        int playersIndex = 0;
        int trainersIndex = 0;
        while (playersIndex < players.length && trainersIndex < trainers.length) {
            if (players[playersIndex] <= trainers[trainersIndex]) {
                result++;
                playersIndex++;
                trainersIndex++;
            } else {
                trainersIndex++;
            }
        }
        return result;
    }

    public static int matchPlayersAndTrainers2(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int result = 0;
        int index = 0;
        for (int i = 0; i < players.length; i++) {
            while (index < trainers.length && trainers[index] < players[i]) {
                index++;
            }
            if (index == trainers.length) {
                break;
            }
            index++;
            result++;
        }
        return result;
    }

    private static void check(int[] players, int[] trainers, int expected) {
        System.out.println("players is: " + Arrays.toString(players));
        System.out.println("trainers is: " + Arrays.toString(trainers));
        System.out.println("expected is: " + expected);
        int matchPlayersAndTrainers = matchPlayersAndTrainers(players, trainers); // Calls your implementation
        System.out.println("matchPlayersAndTrainers is: " + matchPlayersAndTrainers);
    }
}
