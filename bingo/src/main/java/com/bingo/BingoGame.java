package com.bingo;

import java.util.Set;

public class BingoGame {
    private int[][] card; // The Bingo card
    private BingoPattern pattern; // The Bingo pattern
    private Set<Integer> calledNumbers; // Called numbers
    private int lastNumber; // Last called number

    // Constructor
    public BingoGame(BingoPattern pattern, Set<Integer> calledNumbers, int lastNumber, int[][] card) {
        this.pattern = pattern;
        this.calledNumbers = calledNumbers;
        this.lastNumber = lastNumber;
        this.card = card;
    }

    // Validates if the Bingo card satisfies the given pattern
    public boolean isValidBingo() {
        boolean lastNumberInPattern = false;
        int[][] patternArray = pattern.getPattern();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (patternArray[i][j] == 1 || patternArray[i][j] == 4) {
                    if (card[i][j] == lastNumber) {
                        lastNumberInPattern = true; // Track if last number is in the pattern
                    }
                    if (card[i][j] != 0 && !calledNumbers.contains(card[i][j])) {
                        return false; // Invalid if called number is not on the card
                    }
                }
            }
        }
        return lastNumberInPattern; // Return if the last number was part of the winning pattern
    }

    // Checks if there is a valid Bingo
    public boolean checkBingo() {
        if (pattern.isCrazyPattern()) {
            return checkAllRotations(); // Check all rotations for Crazy patterns
        } else {
            return isValidBingo(); // Validate for Straight patterns
        }
    }

    // Checks Bingo for all rotations of the Crazy pattern
    private boolean checkAllRotations() {
        BingoPattern rotatedPattern = pattern;
        for (int i = 0; i < 4; i++) {
            if (isValidBingo()) {
                return true;
            }
            rotatedPattern = rotatedPattern.rotate90(); // Rotate pattern for next check
        }
        return false; // No valid Bingo found after checking all rotations
    }
}
