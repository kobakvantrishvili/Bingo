package com.bingo;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BingoVerifier {
    public static void main(String[] args) {
        // Read the Bingo.txt from resources
        InputStream inputStream = BingoVerifier.class.getClassLoader().getResourceAsStream("Bingo.txt");

        if (inputStream == null) {
            System.err.println("Bingo.txt file not found!");
            return;
        }

        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNext()) {
                BingoPattern pattern = readBingoPattern(scanner);
                Set<Integer> calledNumbers = readCalledNumbers(scanner);
                int lastNumber = getLastNumber(calledNumbers);
                int[][] card = readBingoCard(scanner);

                BingoGame game = new BingoGame(pattern, calledNumbers, lastNumber, card);
                boolean isBingo = game.checkBingo();

                System.out.println(isBingo ? "VALID BINGO" : "NO BINGO");
            }
        }
    }

    private static BingoPattern readBingoPattern(Scanner scanner) {
        int[][] patternArray = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                patternArray[i][j] = scanner.nextInt();
            }
        }
        scanner.nextLine(); // Move to the next line after reading the pattern
        return new BingoPattern(patternArray);
    }

    private static Set<Integer> readCalledNumbers(Scanner scanner) {
        // Skip any blank lines before reading the Bingo card
        while (scanner.hasNextLine() && scanner.nextLine().trim().isEmpty()) {
            scanner.nextLine();
        }
        String[] calledNumbersInput = scanner.nextLine().split(" ");
        Set<Integer> calledNumbers = new HashSet<>();
        for (String num : calledNumbersInput) {
            calledNumbers.add(Integer.parseInt(num));
        }
        return calledNumbers;
    }

    private static int[][] readBingoCard(Scanner scanner) {
        int[][] cardArray = new int[5][5];
        for (int i = 0; i < 5; i++) {
            // Skip any blank lines before reading the Bingo card
            while (scanner.hasNextLine() && scanner.nextLine().trim().isEmpty()) {
            }
            for (int j = 0; j < 5; j++) {
                cardArray[i][j] = scanner.nextInt();
            }
        }
        return cardArray;
    }

    private static int getLastNumber(Set<Integer> calledNumbers) {
        return calledNumbers.stream().reduce((first, second) -> second).orElse(-1);
    }
}
