package com.snkinard.coding;

/**
 * Given a list of N coins, their values (V1, V2, ... , VN), and the total sum S.
 * Find the minimum number of coins the sum of which is S (we can use as many coins
 * of one type as we want), or report that it's not possible to select coins in
 * such a way that they sum up to S.
 *
 */
public class SumCoins {

    public static int sumCoins(int[] values, int targetSum) {
        int[] min = new int[targetSum + 1];
        for (int index = 0; index < min.length; index++) {
            min[index] = Integer.MAX_VALUE;
        }
        min[0] = 0;
        for (int currentSum = 1; currentSum <= targetSum; currentSum++) {
            for (int coinIdx = 0; coinIdx < values.length; coinIdx++) {
                int curentValue = values[coinIdx];
                if (curentValue <= currentSum && currentSum - curentValue >= 0 && min[currentSum - curentValue] + 1 < min[currentSum]) {
                    min[currentSum] = min[currentSum - curentValue] + 1;
                }
            }
        }
        return min[targetSum];
    }
}
