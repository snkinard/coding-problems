package com.snkinard.coding;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class QuicksortTest {

    @DataProvider
    public Object[][] testCases() {
        int[] random = new int[100];
        for(int index = 0; index < 100; index++) {
            random[index] = (int) Math.random() * 100;
        }
        return new Object[][]{
                {new int[] {1, 9, 1, 5, 9, 1, 9}},
                {random}
        };
    }

    @Test(dataProvider = "testCases")
    public void testQuicksort(int[] data)
            throws Exception {
        Quicksort.quicksort(data, 0, data.length - 1);
        validateSort(data);
    }

    @Test(dataProvider = "testCases")
    public void testGoodQuicksort(int[] data)
            throws Exception {
        Quicksort.goodQuicksort(data, 0, data.length - 1);
        validateSort(data);
    }

    private void validateSort(int[] data) {
        if (data.length > 1) {
            for (int index = 1; index < data.length; index++) {
                if (data[index - 1] > data[index]) {
                    AssertJUnit.fail("List was not sorted");
                }
            }
        }
    }
}
