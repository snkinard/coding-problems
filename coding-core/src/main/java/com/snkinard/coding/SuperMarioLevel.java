package com.snkinard.coding;

/**
 * Imagine a 2d mario level that consists of consecutive rectangles of width 1 and
 * heights defined by the input: an array of integers. Lets say that we want to fill
 * all of the "valleys" in the level up with water. Write an algorithm that calculates
 * the area of the water that is necessary to fill all valleys.
 *
 * For example, say this was the input {6, 2, 4, 3, 7, 2, 3, 5, 4, 2, 3, 1}. Then we could
 * visualize the level like this using numbers for the rectangles and '~' for water:
 *
 *         7
 * 6 ~ ~ ~ 7
 * 6 ~ ~ ~ 7 ~ ~ 5
 * 6 ~ 4 ~ 7 ~ ~ 5 4
 * 6 ~ 4 3 7 ~ 3 5 4 ~ 3
 * 6 2 4 3 7 2 3 5 4 2 3
 * 6 2 4 3 7 2 3 5 4 2 3 1
 *
 * The total area of the water would be 15.
 */
public class SuperMarioLevel {

    /**
     * O(N)
     *
     * @param heights
     * @return
     */
    public static int fillLevel(int[] heights) {

        // lots of crap to keep track of
        int waterArea = 0;
        int segmentArea = 0;
        int currentIndex = 0;
        int startIndex = 0;
        int startHeight = heights[startIndex];
        int currentHeight = heights[startIndex];

        // iterate through the entire level
        while (currentIndex < heights.length) {
            currentHeight = heights[currentIndex++];
            if (currentHeight < startHeight) {
                // add to the current area and keep moving
                segmentArea += startHeight - currentHeight;
            } else {
                // set new startHeight and add to total area
                startHeight = currentHeight;
                waterArea += segmentArea;
                // reset segment area and start index
                segmentArea = 0;
                startIndex = currentIndex - 1;
            }
        }

        // test for edge case
        if (currentHeight < startHeight) {

            // again a ton of variables to help track all the things
            segmentArea = 0;
            int endIndex = startIndex;
            startIndex = heights.length - 1;
            currentIndex = startIndex;
            startHeight = currentHeight;

            // iterate backwards until you hit the peak that was found earlier
            while (currentIndex > endIndex) {
                currentHeight = heights[currentIndex--];
                if (currentHeight < startHeight) {
                    // add current area and keep moving
                    segmentArea += startHeight - currentHeight;
                } else {
                    // set new startHeight and add to total area
                    startHeight = currentHeight;
                    waterArea += segmentArea;
                    segmentArea = 0;
                }
            }
            waterArea += segmentArea;
        }
        return waterArea;
    }
}
