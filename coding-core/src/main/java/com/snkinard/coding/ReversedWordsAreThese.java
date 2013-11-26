package com.snkinard.coding;

/**
 * This problem is a simpler one, so you can give it a shot right now. Write a function which, given a sentence like this:
 *
 *   Coding for Interviews contains too many gifs.
 * Returns the sentence with the order of the words reversed, like so:
 *
 *   gifs. many too contains Interviews for Coding
 * The catch is: your function should use O(1) space. What is your algorithm's time complexity?
 */
public class ReversedWordsAreThese {

    /**
     * Using stringbuffer since it needs to be O(1) space complexity.
     *
     * @param input
     */
    public static String reverseWords(StringBuffer input) {
        int length = input.length();
        reverseStringBuffer(input, 0, length - 1);
        int startIdx = 0;
        int endIdx = 0;
        while (endIdx < length) {
            if (input.charAt(endIdx) == ' ') {
                // reverse
                reverseStringBuffer(input, startIdx, endIdx - 1);
                startIdx = endIdx + 1;
            }
            endIdx++;
        }
        reverseStringBuffer(input, startIdx, length - 1);
        return input.toString();
    }

    /**
     * O(N) where N is endIdx - startIdx
     *
     * @param input
     * @param startIdx
     * @param endIdx
     */
    protected static void reverseStringBuffer(StringBuffer input, int startIdx, int endIdx) {
        for (int index = startIdx; index <= startIdx + (endIdx - startIdx) / 2; index++) {
            char temp = input.charAt(index);
            int oppositeIndex = endIdx - (index - startIdx);
            input.setCharAt(index, input.charAt(oppositeIndex));
            input.setCharAt(oppositeIndex, temp);
        }
    }
}
