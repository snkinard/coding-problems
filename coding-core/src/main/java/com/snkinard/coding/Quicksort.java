package com.snkinard.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * Last week's topic was quicksort. The problem had a few parts:
 *
 * 1. Write an answer to the common interview question: what makes quicksort different from merge sort?
 * 2. Implement recursive quicksort
 *      (a) choosing the leftmost element as a pivot
 *      (b) choosing the middle element as a pivot
 *      (c) choosing a random element as a pivot. Don't worry about storage.
 * 3. (Bonus) Measure how many comparisons your quicksorts do on different arrays: already sorted, all the same element, randomized.
 */
public class Quicksort {

    /**
     * Quicksorts the given data between the startIdx and endIdx.
     *
     * @param data array containing data to be sorted
     * @param startIdx start index of data to be sorted
     * @param endIdx ending index of data to be sorted
     * @return the number of comparisons made
     */
    public static int quicksort(int[] data, int startIdx, int endIdx) {
        if (startIdx == endIdx) {
            return 0;
        }
        if (startIdx < 0 || endIdx >= data.length || startIdx > endIdx) {
            throw new ArrayIndexOutOfBoundsException("You sent me a shitty index, dummy! startIdx: " + startIdx + ", endIdx: " + endIdx);
        }


        // find pivot (middle element for now)
        int pivotIndex = startIdx + (endIdx - startIdx) / 2;
        int pivotValue = data[pivotIndex];

        // try to swap as many as possible
        int leftIdx = startIdx;
        int rightIdx = pivotIndex + 1;
        while (leftIdx < pivotIndex) {
            int leftValue = data[leftIdx++];
            if (leftValue > pivotValue){
                while (rightIdx <= endIdx) {
                    int rightValue = data[rightIdx++];
                    if (rightValue < pivotValue) {
                        // swap
                        int swapIdxLeft = leftIdx - 1;
                        int swapIdxRight = rightIdx - 1;
                        int temp = data[swapIdxLeft];
                        data[swapIdxLeft] = data[swapIdxRight];
                        data[swapIdxRight] = temp;
                        break;
                    }
                }
            }
        }

        // get any leftovers to the left of the pivot
        leftIdx = startIdx;
        while (leftIdx < pivotIndex) {
            pivotValue = data[pivotIndex];
            int leftValue = data[leftIdx++];
            if (leftValue > pivotValue) {
                int shiftIdx = leftIdx - 1;
                while (shiftIdx < pivotIndex) {
                    data[shiftIdx] = data[shiftIdx + 1];
                    shiftIdx++;
                }
                pivotIndex--;
                data[shiftIdx] = leftValue;
            }
        }

        // get any leftovers to the right of the pivot
        rightIdx = pivotIndex + 1;
        while (rightIdx < data.length) {
            int rightValue = data[rightIdx++];
            pivotValue = data[pivotIndex];
            if (rightValue < pivotValue) {
                // iterate from pivot to place value was found, shifting values to the right
                int shiftIdx = rightIdx - 1;
                while (shiftIdx > pivotIndex) {
                    data[shiftIdx - 1] = data[shiftIdx];
                    shiftIdx--;
                }
                data[pivotIndex++] = rightValue;
            }
        }


        if (startIdx == pivotIndex || endIdx == pivotIndex) {
            return 0;
        }
        // recursion for sublists on either side of pivot
        quicksort(data, startIdx, pivotIndex - 1);
        quicksort(data, pivotIndex + 1, endIdx);

        return 0;
    }

    public static void goodQuicksort(int[] data, int startIdx, int endIdx) {
        if (startIdx == endIdx) {
            return;
        }
        if (startIdx < 0 || endIdx >= data.length || startIdx > endIdx) {
            throw new ArrayIndexOutOfBoundsException("You sent me a shitty index, dummy! startIdx: " + startIdx + ", endIdx: " + endIdx);
        }

        // find pivot (middle element for now)
        int pivotIndex = startIdx + (endIdx - startIdx) / 2;
        int pivotValue = data[pivotIndex];

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int index = startIdx; index < endIdx; index++) {
            int value = data[index];
            if (value < pivotValue) {
                left.add(value);
            } else {
                right.add(value);
            }
        }

        int index = 0;
        for (int num : left) {
            data[index++] = num;
        }
        data[index++] = pivotValue;
        for (int num : right) {
            data[index++] = num;
        }

        if (startIdx == pivotIndex || endIdx == pivotIndex) {
            return;
        }
        // recursion for sublists on either side of pivot
        quicksort(data, startIdx, pivotIndex - 1);
        quicksort(data, pivotIndex + 1, endIdx);
    }
}
