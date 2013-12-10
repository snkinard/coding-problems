package com.snkinard.coding;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SumCoinsTest {

    @DataProvider
    public Object[][] getCoinsCases() {
        return new Object[][] {
                {new int[]{1, 3, 5}, 11, 3}
        };
    }

    @Test(dataProvider = "getCoinsCases")
    public void testSumCoins(int[] values, int targetSum, int expectedCount)
            throws Exception {
        AssertJUnit.assertEquals("Broke-ass shit!!", SumCoins.sumCoins(values, targetSum), expectedCount);
    }
}
