package com.snkinard.coding;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SpiralTest {

    private Spiral unit;

    @BeforeTest
    public void setup() {
        unit = new Spiral();
    }

    @AfterTest
    public void tearDown() {
        unit = null;
    }

    @DataProvider
    public Object[][] calcValueCases() {
        int numRows = 16;
        int numColumns = 13;
        int[][] expectedValue = new int[numRows][numColumns];
        int value = 1;
        Object[][] returnVal = new Object[numRows * numColumns][4];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                returnVal[value - 1] = new Object[]{numColumns, row + 1, col + 1, expectedValue};
                expectedValue[row][col] = value++;
            }
        }
        return returnVal;
    }

    @Test(dataProvider = "calcValueCases")
    public void testCalculateValue(int numColumns, int row, int column, int[][] expectedValues)
            throws Exception {
        AssertJUnit.assertEquals("Broke-ass shit!!", expectedValues[row - 1][column - 1], Spiral.calculateValue(numColumns, row, column));
    }

    @DataProvider
    public Object[][] spiralCases() {
        int[] edgeCaseExpected = new int[100];
        for (int index = 0; index < 100; index++) {
            edgeCaseExpected[index] = index + 1;
        }
        return new Object[][] {
                {5, 5, 3, 3, new int[]{13, 8, 7, 12, 17, 18, 19, 14, 9, 4, 3, 2, 1, 6, 11, 16, 21, 22, 23, 24, 25, 20, 15, 10, 5}},
                {2, 4, 1, 2, new int[]{2, 1, 5, 6, 7, 3, 8, 4}},
                {2, 6, 1, 2, new int[]{2, 1, 7, 8, 9, 3, 10, 4, 11, 5, 12, 6}},
                {1, 100, 1, 1, edgeCaseExpected}
        };
    }

    @Test(dataProvider = "spiralCases")
    public void testSpiralQuadratic(int numRows, int numColumns, int x, int y, int[] expected) {
        int[] actual = unit.spiralQuadratic(numRows, numColumns, x, y);
        AssertJUnit.assertEquals("Actual and expected results were different sizes.", expected.length, actual.length);
        for (int index = 0; index < expected.length; index++) {
            AssertJUnit.assertEquals("Broke-ass shit!", expected[index], actual[index]);
        }
    }

    @Test
    public void testSpiralQuadraticSpam() {
        int numRows = 10;
        int numColumns = 100;
        for (int row = 1; row <= numRows; row++) {
            for (int column = 1; column <= numColumns; column++) {
                unit.spiralQuadratic(numRows, numColumns, row, column);
            }
        }
    }

    @Test(dataProvider = "spiralCases")
    public void testSpiralLinear(int numRows, int numColumns, int x, int y, int[] expected) {
        int[] actual = unit.spiralLinear(numRows, numColumns, x, y);
        AssertJUnit.assertEquals("Actual and expected results were different sizes.", expected.length, actual.length);
        for (int index = 0; index < expected.length; index++) {
            AssertJUnit.assertEquals("Broke-ass shit!", expected[index], actual[index]);
        }
    }

    @Test
    public void testSpiralLinearSpam() {
        int numRows = 10;
        int numColumns = 100;
        for (int row = 1; row <= numRows; row++) {
            for (int column = 1; column <= numColumns; column++) {
                unit.spiralLinear(numRows, numColumns, row, column);
            }
        }
    }
}
