package com.bingo;

public class BingoPattern {
    private int[][] pattern;

    public BingoPattern(int[][] pattern) {
        this.pattern = pattern;
    }

    public BingoPattern rotate90() {
        int[][] rotated = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                rotated[j][5 - 1 - i] = pattern[i][j];
            }
        }
        return new BingoPattern(rotated);
    }

    public boolean isCrazyPattern() {
        for (int[] row : pattern) {
            for (int value : row) {
                if (value == 1) {
                    return false;
                } else if (value == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[][] getPattern() {
        return pattern;
    }
}
