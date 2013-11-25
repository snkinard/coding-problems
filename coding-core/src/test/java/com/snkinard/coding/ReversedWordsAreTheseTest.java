package com.snkinard.coding;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class ReversedWordsAreTheseTest {

    @Test
    public void testReverseWords()
            throws Exception {
        StringBuffer input = new StringBuffer("I am Sam");
        AssertJUnit.assertEquals("Expected output to be reversed.", ReversedWordsAreThese.reverseWords(input), "Sam am I");
    }

    @Test
    public void testReverseStringBuffer() {
        StringBuffer input = new StringBuffer("maS ma I");
        ReversedWordsAreThese.reverseStringBuffer(input, 0, 2);
        AssertJUnit.assertEquals("Broke ass shit!",  "Sam ma I", input.toString());
        ReversedWordsAreThese.reverseStringBuffer(input, 4, 5);
        AssertJUnit.assertEquals("Broke ass shit!",  "Sam am I", input.toString());
        ReversedWordsAreThese.reverseStringBuffer(input, 7, 7);
        AssertJUnit.assertEquals("Broke ass shit!",  "Sam am I", input.toString());
    }
}
