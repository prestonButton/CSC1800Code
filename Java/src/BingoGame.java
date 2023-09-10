import java.util.*;

public class BingoGame {
    private int[][] bingoCard;
    private ArrayList<Integer> rowIndices;
    private ArrayList<Integer> columnIndices;
    private ArrayList<Integer> calledNumbers;
    private int bingoPattern;

    public BingoGame(Scanner scanner) {
        // Initialize variables and read inputs from scanner
        this.bingoCard = new int[5][5];
        this.rowIndices = new ArrayList<>();
        this.columnIndices = new ArrayList<>();
        this.calledNumbers = new ArrayList<>();
        this.bingoPattern = 0;

        int rowIndex = 0;
        while (rowIndex < 5) {
            String[] pattern = scanner.nextLine().split(" ");
            int columnIndex = 0;
            for (String  item: pattern) {
                int number = Integer.parseInt(item);
                if (number != 0) {
                    rowIndices.add(rowIndex);
                    columnIndices.add(columnIndex);
                    bingoPattern = number;
                }
                columnIndex++;
            }
            rowIndex++;
        }

        scanner.nextLine();

        String[] calledNumbers = scanner.nextLine().split(" ");
        for (String calledNumber : calledNumbers) {
            this.calledNumbers.add(Integer.parseInt(calledNumber));
        }

        scanner.nextLine();

        rowIndex = 0;
        while (rowIndex < 5) {
            String[] card = scanner.nextLine().split(" ");
            int columnIndex = 0;
            for (String item : card) {
                int number = Integer.parseInt(item);
                bingoCard[rowIndex][columnIndex] = number;
                columnIndex++;
            }
            rowIndex++;
        }
    }

    public void play() {
        // Check for Bingo and print result
        if (bingoPattern == 1) {
            if (BingoVerifier.verifyBingo(bingoCard, rowIndices, columnIndices, calledNumbers)) {
                System.out.println("VALID BINGO");
            } else {
                System.out.println("NO BINGO");
            }
        } else if (bingoPattern == 4) {
            boolean isValidCrazy = false;
            for (int i = 0; i < 4; i++) {
                if (BingoVerifier.verifyBingo(bingoCard, rowIndices, columnIndices, calledNumbers)) {
                    isValidCrazy = true;
                    break;
                } else {
                    BingoVerifier.rotatePattern(rowIndices, columnIndices);
                }
            }
            if (isValidCrazy) {
                System.out.println("VALID BINGO");
            } else {
                System.out.println("NO BINGO");
            }
        } else {
            System.out.println("NO BINGO");
        }
    }
}