package com.snkinard.coding;


import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SuperMarioLevelTest {

    @DataProvider
    public Object[][] testCases() {
        return new Object[][] {
                // walk forwards and halfway backwards
                {new int[]{6, 2, 4, 3, 7, 2, 3, 5, 4, 2, 3, 1}, 15},
                {new int[]{6, 2, 4, 3, 7, 7, 7, 2, 3, 5, 4, 2, 3, 1}, 15},
                // walk all forwards and all backwards
                {new int[]{7, 2, 3, 5, 4, 2, 3, 1}, 6},
                // only walk forwards
                {new int[]{6, 2, 4, 3, 7}, 9},
                // triangle shape
                {new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1}, 0},
                {new int[]{1, 2, 3, 4, 5, 5, 5, 5, 4, 3, 2, 1}, 0},
                // flat
                {new int[]{5, 5, 5, 5, 5, 5, 5}, 0},
        };
    }

    @Test(dataProvider = "testCases")
    public void testFillLevel(int[] heights, int expectedArea)
            throws Exception {
        AssertJUnit.assertEquals("Actual height did not match expected height.", expectedArea, SuperMarioLevel.fillLevel(heights));
    }

}
