package com.snkinard.coding;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MaxConsecutiveSumTest {

    @DataProvider
    public Object[][] maxSumCases() {
        return new Object[][] {
                {new int[]{1}, 1},
                {new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 8},
                {new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1}, 8},
                {new int[]{-1, -1, -1, -1, -1, -1, -1, -1}, -1},
                {new int[]{-1, 5, 6, -2, 20, -50, 4}, 29},
                {new int[]{-1, 5}, 5},
                {new int[]{-2, -4, -3, -1}, -1},
                {new int[]{-1, -4, -3, -2}, -1},
                {new int[]{-1, -4, 0, -3, -2}, 0},
                {new int[]{100, 5, -3, 2, -7, 4, 10}, 111},
                {new int[]{5, -3, 2, -7, 4, 10, 100}, 114}
        };
    }

    @Test(dataProvider = "maxSumCases")
    public void testMaxSumCubic(int[] data, int expectedMaxSum)
            throws Exception {
        AssertJUnit.assertEquals("Actual maxSum did not match expected Max Sum.", expectedMaxSum, MaxConsecutiveSum.maxSumCubic(data));
    }

    @Test(dataProvider = "maxSumCases")
    public void testMaxSumLinearSam(int[] data, int expectedMaxSum)
            throws Exception {
        AssertJUnit.assertEquals("Actual maxSum did not match expected Max Sum.", expectedMaxSum, MaxConsecutiveSum.maxSumLinearSam(data));
    }

    @Test(dataProvider = "maxSumCases")
    public void testMaxSumLinearAkshay(int[] data, int expectedMaxSum)
            throws Exception {
        AssertJUnit.assertEquals("Actual maxSum did not match expected Max Sum.", expectedMaxSum, MaxConsecutiveSum.maxSumLinearAkshay(data));
    }
}
