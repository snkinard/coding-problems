package com.snkinard.coding;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class ReverseSentenceTest {

    @Test
    public void testReverseSentence() {
        AssertJUnit.assertEquals("Expected output did not match actual output", "I ma maS", ReverseSentence.reverseSentence("I am Sam"));
    }

}
