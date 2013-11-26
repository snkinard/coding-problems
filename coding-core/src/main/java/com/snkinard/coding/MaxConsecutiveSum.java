package com.snkinard.coding;

/**
 * The problem: Find the maximum sum possible from picking a contiguous subsequence of an array.
 *
 * [-1, 5, 6, -2, 20, -50, 4]
 * What is the largest sum of contiguous elements available in this list?
 *
 * In the example above, the maximum sum would be 29: [-1, 5, 6, -2, 20, -50, 4], because (5 + 6 - 2 + 20 = 29). End one element later and you'll go negative. Start one element earlier and you'll subtract one.
 *
 * There are cubic-time, quadratic-time, and linear-time solutions to this problem.
 *
 * Can you write a linear-time function?
 */
public class MaxConsecutiveSum {

    public static int maxSumCubic(int[] input) {

        // special case if all numbers are negative
        boolean allNegative = true;
        int maxNum = Integer.MIN_VALUE;
        for (int index = 0; index < input.length; index++) {
            maxNum = Math.max(maxNum, input[index]);
            if (input[index] > 0) {
                allNegative = false;
                break;
            }
        }
        if (allNegative) {
            return maxNum;
        }

        int maxSum = Integer.MIN_VALUE;
        for (int startIdx = 0; startIdx < input.length; startIdx++) {
            for (int endIdx = 0; endIdx < input.length; endIdx++) {
                int sumIndex = startIdx;
                int sum = 0;
                while(sumIndex <= endIdx) {
                    sum += input[sumIndex++];
                }
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    public static int maxSumLinearSam(int[] input) {

        // special case if all numbers are negative or all numbers are positive
        boolean allNegative = true;
        boolean allPositive = true;
        int maxNum = Integer.MIN_VALUE;
        int summation = 0;
        for (int index = 0; index < input.length; index++) {
            int currentValue = input[index];
            maxNum = Math.max(maxNum, currentValue);
            if (currentValue > 0) {
                allNegative = false;
            } else if (currentValue < 0) {
                allPositive = false;
            }
            summation += currentValue;
        }
        if (allNegative) {
            return maxNum;
        } else if (allPositive) {
            return summation;
        }


        int maxSum = Integer.MIN_VALUE;
        int maxSumIndex = -1;
        int sum = 0;
        for (int index = 0; index < input.length; index++) {
            sum += input[index];
            if (sum > maxSum) {
                maxSumIndex = index;
                maxSum = sum;
            }
        }

        int minSum = Integer.MAX_VALUE;
        sum = 0;
        for (int index = 0; index <= maxSumIndex; index++) {
            sum += input[index];
            if (sum < minSum) {
                minSum = sum;
            }
        }

        return Math.max(maxSum, maxSum - minSum);
    }

    public static int maxSumLinearAkshay(int[] input) {

        // special case if all numbers are negative
        boolean allNegative = true;
        int maxNum = Integer.MIN_VALUE;
        for (int index = 0; index < input.length; index++) {
            int currentValue = input[index];
            maxNum = Math.max(maxNum, currentValue);
            if (currentValue > 0) {
                allNegative = false;
            }
        }
        if (allNegative) {
            return maxNum;
        }

        int currentSum = 0;
        int maxSum = 0;
        for (int index = 0; index < input.length; index++) {
            currentSum += input[index];
            if (currentSum < 0) {
                currentSum = 0;
            }
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

}
