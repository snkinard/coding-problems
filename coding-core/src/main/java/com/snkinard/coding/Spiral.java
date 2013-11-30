package com.snkinard.coding;

import java.util.Stack;

/**
 * https://github.com/blakeembrey/code-problems/tree/master/problems/spiral
 *
 * Write a function that accepts four arguments. The first two arguments are the size of the grid (h x w), filled with ascending integers from left to right, top to bottom, starting from 1. The next two arguments are the starting positions, the row (r) and column (c).
 *
 * Return an array of integers obtained by spiralling outward anti-clockwise from the r and c, starting upward.
 *
 * f(5, 5, 3, 3) // [ 13, 8, 7, 12, 17, 18, 19, 14, 9, 4, 3, 2, 1, 6, 11, 16, 21, 22, 23, 24, 25, 20, 15, 10, 5 ]
 *
 * f(2, 4, 1, 2) // [ 2, 1, 5, 6, 7, 3, 8, 4 ]
 */
public class Spiral {

    private int currentRow;
    private int currentColumn;
    private int lineSegmentLength;
    private Direction direction;

    public int[] spiralQuadratic(int numRows, int numColumns, int x, int y) {
        int totalResults = numRows * numColumns;
        int result[] = new int[totalResults];
        int resultIndex = 0;
        currentRow = x;
        currentColumn = y;
        // length of the current line segment being walked
        lineSegmentLength = 1;
        // distance travelled so far on the current line segment
        int distanceTraveled = 0;
        direction = Direction.NORTH;
        while (resultIndex < totalResults) {
            // if the current coord is inbounds
            if (isInbounds(numRows, numColumns, currentRow, currentColumn)) {
                // record value
                result[resultIndex++] = calculateValue(numColumns, currentRow, currentColumn);
            }


            // switch line segment direction if we are at the end of a line segment
            // update lineSegmentLength if appropriate and reset distanceTraveled
            if (distanceTraveled == lineSegmentLength) {
                distanceTraveled = 0;
                switchDirection();
            }

            int magnitude = 1;
            adjustPosition(magnitude);
            distanceTraveled++;
        }
        return result;
    }

    /**
     * ABOMINATION, but I think this is O(N) with worst case input
     *
     * @param numRows
     * @param numColumns
     * @param x
     * @param y
     * @return
     */
    public int[] spiralLinear(int numRows, int numColumns, int x, int y) {
        int totalResults = numRows * numColumns;
        int result[] = new int[totalResults];
        int resultIndex = 0;
        currentRow = x;
        currentColumn = y;
        // length of the current line segment being walked
        lineSegmentLength = 1;
        // distance travelled so far on the current line segment
        int distanceTraveled = 0;
        direction = Direction.NORTH;
        int lastRecordedRow = currentRow;
        int lastRecordedColumn = currentColumn;
        while (resultIndex < totalResults) {
            // if the current coord is inbounds
            if (isInbounds(numRows, numColumns, currentRow, currentColumn)) {
                // record value
                result[resultIndex++] = calculateValue(numColumns, currentRow, currentColumn);
                lastRecordedRow = currentRow;
                lastRecordedColumn = currentColumn;
            }
            else { // checking corners instead of each individual coordinates
                Direction startingDirection = direction;
                // find distance to corner and then change position to that corner
                if (distanceTraveled < lineSegmentLength) {
                    int distanceToCorner = lineSegmentLength - distanceTraveled;
                    adjustPosition(distanceToCorner);
                    distanceTraveled = lineSegmentLength;
                }
                boolean doStackOperation = true;
                // if out of bounds, find the next corner
                while(!isInbounds(numRows, numColumns, currentRow, currentColumn)) {
                    switchDirection();
                    // in this case we won't find a corner that is in bounds so we have to cheat and calculate the next point that will be in bounds
                    if(direction == startingDirection) {
                        doStackOperation = false;
                        switch(direction) {
                            case NORTH:
                                distanceTraveled = currentRow - numRows;
                                currentRow = numRows;
                                currentColumn = lastRecordedColumn + 1;
                                break;
                            case EAST:
                                distanceTraveled = 1 - currentColumn;
                                currentRow = lastRecordedRow + 1;
                                currentColumn = 1;
                                break;
                            case SOUTH:
                                distanceTraveled = 1 - currentRow;
                                currentRow = 1;
                                currentColumn = lastRecordedColumn - 1;
                                break;
                            case WEST:
                                distanceTraveled = currentColumn - numColumns;
                                currentRow = lastRecordedRow -1;
                                currentColumn = numColumns;
                                break;
                        }
                        // record value
                        result[resultIndex++] = calculateValue(numColumns, currentRow, currentColumn);
                        lastRecordedRow = currentRow;
                        lastRecordedColumn = currentColumn;
                        break;
                    }
                    adjustPosition(lineSegmentLength);
                    distanceTraveled = lineSegmentLength;
                }
                if (doStackOperation) {
                    // when back in bounds walk backwards until out of bounds, push each inbound value onto a stack
                    Stack<Integer> stack = new Stack<>();
                    int cornerRow = currentRow;
                    int cornerColumn = currentColumn;
                    while(isInbounds(numRows, numColumns, currentRow, currentColumn)) {
                        stack.push(calculateValue(numColumns, currentRow, currentColumn));
                        adjustPosition(-1);
                    }

                    // set current row and column back to corner we started walking backwards at
                    currentRow = cornerRow;
                    currentColumn = cornerColumn;
                    // pop stack until empty, adding values to result array
                    while (!stack.isEmpty()) {
                        result[resultIndex++] = stack.pop();
                        lastRecordedRow = currentRow;
                        lastRecordedColumn = currentColumn;
                    }
                }
            }


            // switch line segment direction if we are at the end of a line segment
            // update lineSegmentLength if appropriate and reset distanceTraveled
            if (distanceTraveled == lineSegmentLength) {
                distanceTraveled = 0;
                switchDirection();
            }

            adjustPosition(1);
            distanceTraveled++;
        }
        return result;
    }

    private void adjustPosition(int magnitude) {
        switch (direction) {
            case NORTH:
                currentRow -= magnitude;
                break;
            case EAST:
                currentColumn += magnitude;
                break;
            case SOUTH:
                currentRow += magnitude;
                break;
            case WEST:
                currentColumn -= magnitude;
                break;
        }
    }

    private void switchDirection() {
        switch (direction) {
            case NORTH:
                direction = Direction.WEST;
                break;
            case EAST:
                direction = Direction.NORTH;
                lineSegmentLength++;
                break;
            case SOUTH:
                direction = Direction.EAST;
                break;
            case WEST:
                direction = Direction.SOUTH;
                lineSegmentLength++;
                break;
        }
    }

    private static boolean isInbounds(int numRows, int numColumns, int currentRow, int currentColumn) {
        return currentRow > 0 && currentRow <= numRows && currentColumn > 0 && currentColumn <= numColumns;
    }

    public static int calculateValue(int numColumns, int row, int column) {
        return numColumns * (row - 1) + column;
    }

    enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
}
